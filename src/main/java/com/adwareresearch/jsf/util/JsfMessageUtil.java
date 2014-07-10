package com.adwareresearch.jsf.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JsfMessageUtil {
    public static void addErrorMessage(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", msg));
    }
	
    public static void addSuccessMessage(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success", msg));
    }
}
