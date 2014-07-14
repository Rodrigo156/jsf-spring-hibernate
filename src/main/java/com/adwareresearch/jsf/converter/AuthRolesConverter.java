package com.adwareresearch.jsf.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.adwareresearch.domain.AuthRoles;

@FacesConverter(value = "authRolesConverter")
public class AuthRolesConverter implements Converter {

	private List<AuthRoles> list;
	    
    public AuthRolesConverter(List<AuthRoles> list) {
        this.list = list;
    }
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String submittedValue) {
		if(submittedValue == null || submittedValue.isEmpty()) {
	            return null;
        }

        for(AuthRoles object : this.list){
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
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if(!(value instanceof AuthRoles)){
            return null;
        }else {
        	AuthRoles a = (AuthRoles) value;
            return a.getId().toString();
        }
	}

	public List<AuthRoles> getList() {
		return list;
	}

	public void setList(List<AuthRoles> list) {
		this.list = list;
	}
}
