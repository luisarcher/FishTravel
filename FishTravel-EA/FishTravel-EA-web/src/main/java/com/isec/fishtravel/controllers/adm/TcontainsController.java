package com.isec.fishtravel.controllers.adm;

import com.isec.fishtravel.jpa.Tcontains;
import com.isec.fishtravel.controllers.util.JsfUtil;
import com.isec.fishtravel.controllers.util.JsfUtil.PersistAction;
import com.isec.fishtravel.facade.adm.TcontainsFacade;

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

@Named("tcontainsController")
@SessionScoped
public class TcontainsController implements Serializable {

    @EJB
    private com.isec.fishtravel.facade.adm.TcontainsFacade ejbFacade;
    private List<Tcontains> items = null;
    private Tcontains selected;

    public TcontainsController() {
    }

    public Tcontains getSelected() {
        return selected;
    }

    public void setSelected(Tcontains selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getTcontainsPK().setIdPurchase(selected.getTPurchase().getIdPurchase());
        selected.getTcontainsPK().setIdFlight(selected.getTFlight().getIdFlight());
    }

    protected void initializeEmbeddableKey() {
        selected.setTcontainsPK(new com.isec.fishtravel.jpa.TcontainsPK());
    }

    private TcontainsFacade getFacade() {
        return ejbFacade;
    }

    public Tcontains prepareCreate() {
        selected = new Tcontains();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TcontainsCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TcontainsUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TcontainsDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Tcontains> getItems() {
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

    public Tcontains getTcontains(com.isec.fishtravel.jpa.TcontainsPK id) {
        return getFacade().find(id);
    }

    public List<Tcontains> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Tcontains> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Tcontains.class)
    public static class TcontainsControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TcontainsController controller = (TcontainsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tcontainsController");
            return controller.getTcontains(getKey(value));
        }

        com.isec.fishtravel.jpa.TcontainsPK getKey(String value) {
            com.isec.fishtravel.jpa.TcontainsPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.isec.fishtravel.jpa.TcontainsPK();
            key.setIdFlight(Integer.parseInt(values[0]));
            key.setIdPurchase(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(com.isec.fishtravel.jpa.TcontainsPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdFlight());
            sb.append(SEPARATOR);
            sb.append(value.getIdPurchase());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Tcontains) {
                Tcontains o = (Tcontains) object;
                return getStringKey(o.getTcontainsPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Tcontains.class.getName()});
                return null;
            }
        }

    }

}
