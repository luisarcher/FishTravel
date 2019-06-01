package com.isec.fishtravel.controllers.client;

import com.isec.fishtravel.controllers.util.JsfUtil;
import com.isec.fishtravel.controllers.util.JsfUtil.PersistAction;
import com.isec.fishtravel.dto.DTOFlight;
import com.isec.fishtravel.facade.client.FTFlightFacade;

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

@Named("ftFlightController")
@SessionScoped
public class FTFlightController implements Serializable {

    @EJB
    private FTFlightFacade ejbClientFacade;
    
    private List<DTOFlight> items = null;
    private List<DTOFlight> cheapestFlightForEachDest = null;
        
    private DTOFlight selected;

    public FTFlightController() {
    }

    public DTOFlight getSelected() {
        return selected;
    }

    public void setSelected(DTOFlight selected) {
        this.selected = selected;
    }
    
    public List<DTOFlight> getItems() {
        if (items == null) {
            items = getFacade().getAllFlights();
        }
        return items;
    }
    
    public List<DTOFlight> getCheapestFlightForEachDest() {
        
        if (cheapestFlightForEachDest == null) {
            cheapestFlightForEachDest = getFacade().getCheapestFlightForDest();
        }        
        return cheapestFlightForEachDest;
    }

    private FTFlightFacade getFacade() {
        return ejbClientFacade;
    }

    public DTOFlight prepareCreate() {
        selected = new DTOFlight();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TFlightCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TFlightUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TFlightDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    //getFacade().edit(selected);
                } else {
                    //getFacade().remove(selected);
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

    public DTOFlight getDTOFlight(java.lang.Integer id) {
        return getFacade().getFlightById(id);
    }

    public List<DTOFlight> getItemsAvailableSelectMany() {
        return getFacade().getAllFlights();
    }

    public List<DTOFlight> getItemsAvailableSelectOne() {
        return getFacade().getAllFlights();
    }

    @FacesConverter(forClass = DTOFlight.class)
    public static class TFlightControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FTFlightController controller = (FTFlightController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ftFlightController");
            return controller.getDTOFlight(getKey(value));
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
            if (object instanceof DTOFlight) {
                DTOFlight o = (DTOFlight) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DTOFlight.class.getName()});
                return null;
            }
        }

    }
    
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

}
