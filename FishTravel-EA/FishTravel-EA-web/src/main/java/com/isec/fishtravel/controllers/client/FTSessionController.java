package com.isec.fishtravel.controllers.client;

import com.isec.fishtravel.controllers.util.JsfUtil;
import com.isec.fishtravel.dto.DTOFlight;
import com.isec.fishtravel.facade.client.FTUserFacade;
import com.isec.fishtravel.dto.DTOUser;
import com.isec.fishtravel.facade.client.FTFavoriteFacade;
import com.isec.fishtravel.facade.client.FTFlightFacade;
import java.io.IOException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@Named("ftSessionController")
@SessionScoped
public class FTSessionController implements Serializable {

    @EJB
    private FTUserFacade ejbFacade;
    
    @EJB
    private FTFavoriteFacade ejbFavoriteFacade;
    
    @EJB
    private FTFlightFacade ejbFlightFacade;
    
    // New user from Register Dialog
    private DTOUser selected;
    
    // Login data
    private DTOUser loggedInUser;
    
    // Save login credentials from login dialog
    private String userLogin;
    private String userPasswd;
    
    // Preparing flight to be bought
    private Integer selectedFlight;
    
    // Bying flights
    private List<Integer> shoppingCartIds;
    private float shoppingCartTotal;
    //private List<DTOFlight> shoppingCartFlights;

    public FTSessionController() {
        prepareCreate();
    }
    
    public void login(){
        
        loggedInUser = ejbFacade.getUserByCredentials(userLogin, userPasswd);
        if(loggedInUser != null){
            
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("loginSuccess"));
        }
        else {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("loginFailed")); 
        }
    }
    
    public void logout(){
        FacesContext.getCurrentInstance().getExternalContext()
        .invalidateSession();
        loggedInUser = null;
    }
    
    public String getCurrentUser(){
        if(loggedInUser != null){
            return loggedInUser.toString();
        }
        return "No login";
    }
    
    public List<DTOFlight> getShoppingCartFlights(){
        
        this.shoppingCartTotal = 0;
        List<DTOFlight> flights = getFlightFacade().getFlightsByIds(shoppingCartIds);
        
        for(DTOFlight f : flights){
            shoppingCartTotal += f.getPrice();
        }
        
        return flights;
    }
    
    /**
     * Called when clicking on star icon (Add to favorite)
     * @param flightId 
     */
    public void addToFavorites(String flightId){
        
        getFavoriteFacade().addToFavorites(loggedInUser.getId(), Integer.parseInt(flightId));
        
        // todo - Implement using a notifier
        JsfUtil.addSuccessMessage("Added to favorites");
        
        //Debug purposes
        JsfUtil.addSuccessMessage("user:" + loggedInUser.getId() + " f: " + flightId);
    }
    
    /**
     * Called when clicking on the shopping cart Icon
     * @param flightId 
     */
    public void actionSelectFlight(String flightId){
                
        this.selectedFlight = Integer.parseInt(flightId);
        JsfUtil.addSuccessMessage("selected flight: " + String.valueOf(selectedFlight));
    }
    
    /**
     * Called when clicking on "Buy Now" button, inside Buy flight dialog.
     * Adds selected flight to cart and redirects the user to Shopping cart page.
     */
    public void actionBuyNow(){
        try {
            
            this.actionAddToCart();
            
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/ft/shoppingcart.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(FTSessionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * By clicking on "Add to Cart" button inside Buy Flight dialog.
     * Adds the flight to the shopping cart and allows the user to continue shopping.
     */
    public void actionAddToCart(){
        
        if (shoppingCartIds == null){
            this.shoppingCartIds = new ArrayList<>();
        }
        this.shoppingCartIds.add(selectedFlight);
        JsfUtil.addSuccessMessage("Voo: " + String.valueOf(shoppingCartIds.get(shoppingCartIds.size() -1)));
    }
        
    protected void setEmbeddableKeys() {
    }

    private DTOUser prepareCreate() {
        selected = new DTOUser();
        return selected;
    }
       
    /*public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TUserUpdated"));
    }*/
    
    /* ======= Getters and Setters ======= */
    
        public DTOUser getSelected() {
        return selected;
    }

    public void setSelected(DTOUser selected) {
        this.selected = selected;
    }
    
    private FTUserFacade getFacade() {
        return ejbFacade;
    }
    
    private FTFavoriteFacade getFavoriteFacade() {
        return ejbFavoriteFacade;
    }
    
    private FTFlightFacade getFlightFacade(){
        return ejbFlightFacade;
    }
    
    public void register(){
        getFacade().register(selected);
    }

    public DTOUser getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(DTOUser loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }

    public Integer getSelectedFlight() {
        return selectedFlight;
    }

    public void setSelectedFlight(Integer selectedFlight) {
        this.selectedFlight = selectedFlight;
    }

    public List<Integer> getShoppingCart() {
        return shoppingCartIds;
    }

    public void setShoppingCart(List<Integer> shoppingCart) {
        this.shoppingCartIds = shoppingCart;
    }

    public float getShoppingCartTotal() {
        return shoppingCartTotal;
    }

    public void setShoppingCartTotal(float shoppingCartTotal) {
        this.shoppingCartTotal = shoppingCartTotal;
    }
    
    

}
