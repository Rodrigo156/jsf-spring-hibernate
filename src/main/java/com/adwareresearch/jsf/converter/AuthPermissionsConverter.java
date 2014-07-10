package com.adwareresearch.jsf.converter;

import com.adwareresearch.domain.AuthPermissions;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "authPermissionsConverter")
public class AuthPermissionsConverter implements Converter {

    private List<AuthPermissions> list;
    
    public AuthPermissionsConverter(List<AuthPermissions> list) {
        this.list = list;
    }
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String submittedValue) {
        if(submittedValue == null || submittedValue.isEmpty()) {
            return null;
        }

        for(AuthPermissions object : this.list){
            try{                            
                if(object.getId() == Integer.parseInt(submittedValue)){
                    return object;
                }
            } catch(NumberFormatException ex) {
                return ex;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if(!(value instanceof AuthPermissions)){
            return null;
        }else {
            AuthPermissions a = (AuthPermissions) value;
            return a.getId().toString();
        }
    }

    public List<AuthPermissions> getList() {
        return list;
    }

    public void setList(List<AuthPermissions> list) {
        this.list = list;
    }
}
