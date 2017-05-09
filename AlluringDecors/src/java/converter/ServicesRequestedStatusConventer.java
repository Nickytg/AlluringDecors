/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import java.math.BigInteger;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import model_controller.ServicesOfferedBean;
import model_controller.ServicesRequestStatusBean;

/**
 *
 * @author USER
 */
@Named(value = "servicesRequestedStatusConventer")
@FacesConverter(value = "servicesRequestedStatusConventer")
public class ServicesRequestedStatusConventer implements Converter{

    private transient EntityManager em;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return em.find(ServicesRequestStatusBean.class, new BigInteger(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((ServicesRequestStatusBean) value).getId());
    }
    
}
