package com.adwareresearch.jsf.controller;

import com.adwareresearch.domain.AuthUser;
import com.adwareresearch.jsf.annotation.SpringRequestScoped;
import com.adwareresearch.jsf.util.JsfMessageUtil;
import com.adwareresearch.service.AuthUserService;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@SpringRequestScoped
public class RegistrationController implements Serializable{

	private static final long serialVersionUID = 5767271156078289525L;
	
	@Autowired
    private transient AuthUserService service;
    @Autowired
    private transient SystemProperties properties;
    
    private AuthUser data;
    
    public RegistrationController() {
        this.data = new AuthUser();
    }

    public void register() {
    	getData().setUserActive(true);
        if(service.findByUsername(getData().getUserName()).isEmpty()) {
            getData().setPasswordExpiry(getPasswordExpiry());
            //getData().getAuthUserRoleses().add(Role.EMPLOYEE);
            getData().setPassword(new BCryptPasswordEncoder().encode(getData().getPassword()));
            try {
            	service.save(getData());
            	//) {
                JsfMessageUtil.addSuccessMessage("Registration successful");
                setData(new AuthUser());
            } catch(DataAccessException ex) {
                LoggerFactory.getLogger(RegistrationController.class).error("Registration error", getData().getUserName());
                JsfMessageUtil.addErrorMessage("Registration error");
            }
        } else {
            JsfMessageUtil.addErrorMessage("This username is taken!");
        }
    }
    
    public Date getPasswordExpiry() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, properties.getPasswordExpiry());
        return new Date(now.getTimeInMillis());
    }
    
    public AuthUserService getService() {
        return service;
    }

    public void setService(AuthUserService service) {
        this.service = service;
    }

    public AuthUser getData() {
        return data;
    }

    public void setData(AuthUser data) {
        this.data = data;
    }
}
