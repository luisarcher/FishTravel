package com.isec.fishtravel.controllers.client;

import com.isec.fishtravel.controllers.util.JsfUtil;
import com.isec.fishtravel.controllers.util.JsfUtil.PersistAction;
import com.isec.fishtravel.facade.adm.TUserFacade;
import dto.DTOUser;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("ftSessionController")
@SessionScoped
public class FTSessionController implements Serializable {

    @EJB
    private TUserFacade ejbFacade;
        
    private DTOUser selected;
    
    // Login data
    private DTOUser loggedInUser;
    private String userLogin;
    private String userPasswd;

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

    public DTOUser getSelected() {
        return selected;
    }

    public void setSelected(DTOUser selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TUserFacade getFacade() {
        return ejbFacade;
    }    

    public DTOUser prepareCreate() {
        selected = new DTOUser();
        initializeEmbeddableKey();
        return selected;
    }
    
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TUserCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TUserUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TUserDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DTOUser> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction == PersistAction.CREATE) {
                    getFacade().create(selected);
                } else
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public DTOUser getUser(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<TUser> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TUser> getItemsAvailableSelectOne() {
        return getFacade().findAll();
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

    @FacesConverter(forClass = DTOUser.class)
    public static class TUserControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FTSessionController controller = (FTSessionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tUserController");
            return controller.getTUser(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof DTOUser) {
                DTOUser o = (DTOUser) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DTOUser.class.getName()});
                return null;
            }
        }

    }

}