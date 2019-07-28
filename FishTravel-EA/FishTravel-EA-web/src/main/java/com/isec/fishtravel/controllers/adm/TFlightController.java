package com.isec.fishtravel.controllers.adm;

import com.isec.fishtravel.jpa.TFlight;
import com.isec.fishtravel.controllers.util.JsfUtil;
import com.isec.fishtravel.controllers.util.JsfUtil.PersistAction;
//import dto.DTOFlight;
import com.isec.fishtravel.facade.adm.TFlightFacade;

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

@Named("tFlightController")
@SessionScoped
public class TFlightController implements Serializable {

    @EJB
    private TFlightFacade ejbFacade;
    
    private List<TFlight> items = null; // to be updated
    /*private List<DTOFlight> items_dto = null;

    public List<DTOFlight> getItems_dto() {
        if (items_dto == null) {
            items_dto = getFacade().getAllFlights();
        }
        return items_dto;
    }*/

    /*public void setItems_dto(List<DTOFlight> items_dto) {
        this.items_dto = items_dto;
    }*/
    
    private TFlight selected;

    public TFlightController() {
    }

    public TFlight getSelected() {
        return selected;
    }

    public void setSelected(TFlight selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TFlightFacade getFacade() {
        return ejbFacade;
    }

    public TFlight prepareCreate() {
        selected = new TFlight();
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

    public List<TFlight> getItems() {
        //if (items == null) {
            items = getFacade().findAll();
        //}
        return items;
    }

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

    public TFlight getTFlight(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<TFlight> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TFlight> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TFlight.class)
    public static class TFlightControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TFlightController controller = (TFlightController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tFlightController");
            return controller.getTFlight(getKey(value));
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
            if (object instanceof TFlight) {
                TFlight o = (TFlight) object;
                return getStringKey(o.getIdFlight());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TFlight.class.getName()});
                return null;
            }
        }

    }

}
