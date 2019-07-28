package com.isec.fishtravel.controllers.client.view;

import com.isec.fishtravel.controllers.client.FTFlightController;
import com.isec.fishtravel.dto.DTOFlight;
import com.isec.fishtravel.facade.client.FTFlightFacade;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@ManagedBean
@ViewScoped
public class FTFlightControllerMB implements Serializable {
    
    public static final long serialVersionUID = 1L;

    @EJB
    private FTFlightFacade ejbFlightFacade;
            
    // Collections
    private List<DTOFlight> items = null;
        
    private DTOFlight selected;

    public FTFlightControllerMB() {
    }
    
    public DTOFlight getSelected() {
        return selected;
    }

    public void setSelected(DTOFlight selected) {
        
        this.selected = selected;
    }
    
    public void updateItems(){
        
        items = getFlightFacade().getAllFlights();
    }
    
    public List<DTOFlight> getItems() {
        
        items = getFlightFacade().getAllFlights();
        return items;
    }

    private FTFlightFacade getFlightFacade() {
        return ejbFlightFacade;
    }

    public DTOFlight prepareCreate() {
        selected = new DTOFlight();
        initializeEmbeddableKey();
        return selected;
    }

    public DTOFlight getDTOFlight(java.lang.Integer id) {
        return getFlightFacade().getFlightById(id);
    }

    public List<DTOFlight> getItemsAvailableSelectMany() {
        return getFlightFacade().getAllFlights();
    }

    public List<DTOFlight> getItemsAvailableSelectOne() {
        return getFlightFacade().getAllFlights();
    }

    public FTFlightFacade getEjbFlightFacade() {
        return ejbFlightFacade;
    }

    public void setEjbFlightFacade(FTFlightFacade ejbFlightFacade) {
        this.ejbFlightFacade = ejbFlightFacade;
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
