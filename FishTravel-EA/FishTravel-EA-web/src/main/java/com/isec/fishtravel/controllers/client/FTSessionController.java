package com.isec.fishtravel.controllers.client;

import com.isec.fishtravel.controllers.util.JsfUtil;
import com.isec.fishtravel.dto.DTOFlight;
import com.isec.fishtravel.dto.DTOLuggage;
import com.isec.fishtravel.dto.DTOPurchase;
import com.isec.fishtravel.facade.client.FTUserFacade;
import com.isec.fishtravel.dto.DTOUser;
import com.isec.fishtravel.facade.client.FTFavoriteFacade;
import com.isec.fishtravel.facade.client.FTFlightFacade;
import com.isec.fishtravel.facade.client.FTPurchaseFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
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
    
    @EJB
    private FTPurchaseFacade ejbPurchaseFacade;
    
    private Boolean actionResult;
    private Integer actionResultCode;
    
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
    private Float shoppingCartTotal;
    private List<DTOFlight> shoppingCartFlights;
    
    private DTOLuggage selectedLuggage;
    private List<DTOLuggage> luggage;
    
    private DTOPurchase purchase;
    private Integer selectedPaymentMethodIndex;

    public FTSessionController() {
        prepareCreate();
        actionResultCode = 1;
    }
    
    public void register(){
        getFacade().register(selected);
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
    
    /**
     * 
     * @return 
     */
    public List<DTOFlight> getShoppingCartFlights(){
                
        if (this.shoppingCartFlights == null){
            this.shoppingCartFlights = getFlightFacade().getFlightsByIds(shoppingCartIds);

        }
        
        return this.shoppingCartFlights;
    }
    
    /**
     * Called when clicking on star icon (Add to favorite)
     * @param flightId 
     */
    public void addToFavorites(String flightId){
        
        this.actionResult = getFavoriteFacade().addToFavorites(loggedInUser.getId(), Integer.parseInt(flightId));
        
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
        
        this.actionAddToCart();
        JsfUtil.redirect("/ft/shoppingcart/shoppingcart.xhtml");
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
        
    protected void setEmbeddableKeys() {}

    private void prepareCreate() {
        selected = new DTOUser();
        
        luggage = new ArrayList<>();
        selectedLuggage = new DTOLuggage();
    }
           
    public void actionFinishOrder(){
        
        this.purchase = new DTOPurchase();
        
        this.purchase.setUserId(this.loggedInUser.getId());
        this.purchase.setFlights(shoppingCartIds);
        
        this.actionResultCode = getEjbPurchaseFacade().newOrder(purchase, luggage);
        
        JsfUtil.redirect("/ft/shoppingcart/confirmation.xhtml");
    }
    
    public void actionAddLuggage(){
        
        if (luggage == null)
            luggage = new ArrayList<>();
        
        selectedLuggage.setUserId(this.loggedInUser.getId());
        luggage.add(selectedLuggage);
        
        JsfUtil.addSuccessMessage("Luggage Added to flight: " + String.valueOf(selectedLuggage.getFlightId()) + 
                " Kg: " + String.valueOf(selectedLuggage.getKg()));
    }
    
    public void purchaseConfirmationMessage(){
        
        if (actionResultCode == 1){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Your flights were booked Successfuly!"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", JsfUtil.getErrorMsg(actionResultCode)));
        }
    }
       
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
    
    public FTPurchaseFacade getEjbPurchaseFacade() {
        return ejbPurchaseFacade;
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

    public float getShoppingCartTotal() {
        
        this.shoppingCartTotal = 0f;
        
        for(DTOFlight f : this.shoppingCartFlights){
            this.shoppingCartTotal += f.getPrice();
        }
        
        return this.shoppingCartTotal;
    }

    public void setShoppingCartTotal(float shoppingCartTotal) {
        this.shoppingCartTotal = shoppingCartTotal;
    }

    public List<Integer> getShoppingCartIds() {
        return shoppingCartIds;
    }

    public void setShoppingCartIds(List<Integer> shoppingCartIds) {
        this.shoppingCartIds = shoppingCartIds;
    }

    public List<DTOLuggage> getLuggage() {
        return luggage;
    }

    public void setLuggage(List<DTOLuggage> luggage) {
        this.luggage = luggage;
    }

    public DTOLuggage getSelectedLuggage() {
        return selectedLuggage;
    }

    public void setSelectedLuggage(DTOLuggage selectedLuggage) {
        this.selectedLuggage = selectedLuggage;
    }

    public DTOPurchase getPurchase() {
        return purchase;
    }

    public void setPurchase(DTOPurchase purchase) {
        this.purchase = purchase;
    }

    public Integer getSelectedPaymentMethodIndex() {
        return selectedPaymentMethodIndex;
    }

    public void setSelectedPaymentMethodIndex(Integer selectedPaymentMethodIndex) {
        this.selectedPaymentMethodIndex = selectedPaymentMethodIndex;
    }

    public Boolean getActionResult() {
        return actionResult;
    }

    public void setActionResult(Boolean actionResult) {
        this.actionResult = actionResult;
    }

    public Integer getActionResultCode() {
        return actionResultCode;
    }

    public void setActionResultCode(Integer actionResultCode) {
        this.actionResultCode = actionResultCode;
    }

    
}
