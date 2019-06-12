package com.isec.fishtravel.controllers.client;

import com.isec.fishtravel.controllers.util.JsfUtil;
import com.isec.fishtravel.dto.DTOFlight;
import com.isec.fishtravel.facade.client.FTUserFacade;
import com.isec.fishtravel.dto.DTOUser;
import com.isec.fishtravel.facade.client.FTFavoriteFacade;
import java.io.IOException;

import java.io.Serializable;
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
    
    // New user from Register Dialog
    private DTOUser selected;
    
    // Login data
    private DTOUser loggedInUser;
    
    // Save login credentials from login dialog
    private String userLogin;
    private String userPasswd;
    
    // Bying flights
    private List<Integer> selectedFlights;

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
    
    public void addToFavorites(String flightId){
        
        getFavoriteFacade().addToFavorites(loggedInUser.getId(), Integer.parseInt(flightId));
        
        JsfUtil.addSuccessMessage("Added to favorites");
        JsfUtil.addSuccessMessage("user:" + loggedInUser.getId() + " f:" + flightId);
    }
    
    public void actionBuyNow(){
        try {
            JsfUtil.addSuccessMessage("Voo: " + String.valueOf(selectedFlights.get(selectedFlights.size() -1)));
            
            // Chama o AddToCart e redireciona
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/ft/shoppingcart.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(FTSessionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void actionAddToCart(){
        JsfUtil.addSuccessMessage("Voo: " + String.valueOf(selectedFlights.get(selectedFlights.size() -1)));
    }
    
    public void actionSelectFlight(String flightId){
        this.selectedFlights.add(Integer.parseInt(flightId));
    }
    
    private FTFavoriteFacade getFavoriteFacade() {
        return ejbFavoriteFacade;
    }

    public DTOUser getSelected() {
        return selected;
    }

    public void setSelected(DTOUser selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    private FTUserFacade getFacade() {
        return ejbFacade;
    }    

    public DTOUser prepareCreate() {
        selected = new DTOUser();
        return selected;
    }
       
    /*public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TUserUpdated"));
    }*/
    
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

    public List<Integer> getSelectedFlights() {
        return selectedFlights;
    }

    public void setSelectedFlights(List<Integer> selectedFlights) {
        this.selectedFlights = selectedFlights;
    }



}
