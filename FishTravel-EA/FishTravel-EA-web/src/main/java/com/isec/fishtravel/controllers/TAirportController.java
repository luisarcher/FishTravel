package com.isec.fishtravel.controllers;

import com.isec.fishtravel.jpa.TAirport;
import com.isec.fishtravel.controllers.util.JsfUtil;
import com.isec.fishtravel.controllers.util.JsfUtil.PersistAction;
//import dto.DTOFlight;
import com.isec.fishtravel.facade.TAirportFacade;

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

@Named("tAirportController")
@SessionScoped
public class TAirportController implements Serializable {

    @EJB
    private TAirportFacade ejbFacade;
    
    private List<TAirport> items = null;    // To be modified if time permits
    //private List<DTOFlight> items_dto = null;
    
    private TAirport selected;

    public TAirportController() {
    }

    public TAirport getSelected() {
        return selected;
    }

    public void setSelected(TAirport selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TAirportFacade getFacade() {
        return ejbFacade;
    }

    public TAirport prepareCreate() {
        selected = new TAirport();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TAirportCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TAirportUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TAirportDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TAirport> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    /*public List<DTOFlight> getItems_dto(){
        if (items_dto == null) {
            //items_dto = getFacade().findAll();
        }
        return items_dto;
    }*/

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
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

    public TAirport getTAirport(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<TAirport> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TAirport> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TAirport.class)
    public static class TAirportControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TAirportController controller = (TAirportController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tAirportController");
            return controller.getTAirport(getKey(value));
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
            if (object instanceof TAirport) {
                TAirport o = (TAirport) object;
                return getStringKey(o.getIdAirport());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TAirport.class.getName()});
                return null;
            }
        }

    }

}
