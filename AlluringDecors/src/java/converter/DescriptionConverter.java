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
import model_controller.DescriptionBean;

/**
 *
 * @author USER
 */
@Named(value = "descriptionConverter")
@FacesConverter(value = "customerConverter")
public class DescriptionConverter implements Converter{

    private transient EntityManager em;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return em.find(DescriptionBean.class, new BigInteger(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        return String.valueOf(((DescriptionBean) value).getDescriptionID());
    }
    
}
