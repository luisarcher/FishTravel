package com.isec.fishtravel.controllers.adm;

import com.isec.fishtravel.common.ExtendedPurchase;
import com.isec.fishtravel.jpa.TPurchase;
import com.isec.fishtravel.controllers.util.JsfUtil;
import com.isec.fishtravel.controllers.util.JsfUtil.PersistAction;
import com.isec.fishtravel.facade.adm.TPurchaseFacade;
import com.isec.fishtravel.jpa.TFlight;

import java.io.Serializable;
import java.util.ArrayList;
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

@Named("tPurchaseController")
@SessionScoped
public class TPurchaseController implements Serializable {

    @EJB
    private TPurchaseFacade ejbFacade;
    private List<TPurchase> items = null;
    private TPurchase selected;
   
    private List<ExtendedPurchase> extendedPurchase;
    
    public TPurchaseController() {
    }

    public TPurchase getSelected() {
        return selected;
    }

    public void setSelected(TPurchase selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TPurchaseFacade getFacade() {
        return ejbFacade;
    }

    public TPurchase prepareCreate() {
        selected = new TPurchase();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TPurchaseCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TPurchaseUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TPurchaseDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TPurchase> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
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

    public TPurchase getTPurchase(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<TPurchase> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TPurchase> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public List<ExtendedPurchase> getExtendedPurchase() {
        
        if (items == null){
            items = this.getItems();
        }
        
        extendedPurchase = new ArrayList<>();
        
        for (TPurchase p : items){
            
            for (TFlight f : p.getTFlightCollection()){
                
                ExtendedPurchase i = new ExtendedPurchase();
                
                i.setPurchaseId(p.getIdPurchase());
                i.setUserId(p.getIdUser().getIdUser());
                
                i.setFlightId(f.getIdFlight());
                i.setFlightName(f.getNameFlight());
                i.setFlightDetails(f.getFromAirport() + " To " + f.getToAirport());
                
                extendedPurchase.add(i);
            }
        }
        
        return extendedPurchase;
    }

    public void setExtendedPurchase(List<ExtendedPurchase> extendedPurchase) {
        this.extendedPurchase = extendedPurchase;
    }

    @FacesConverter(forClass = TPurchase.class)
    public static class TPurchaseControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TPurchaseController controller = (TPurchaseController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tPurchaseController");
            return controller.getTPurchase(getKey(value));
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
            if (object instanceof TPurchase) {
                TPurchase o = (TPurchase) object;
                return getStringKey(o.getIdPurchase());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TPurchase.class.getName()});
                return null;
            }
        }

    }

}
