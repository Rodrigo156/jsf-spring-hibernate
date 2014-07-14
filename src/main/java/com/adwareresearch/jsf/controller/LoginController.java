package com.adwareresearch.jsf.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.adwareresearch.domain.AuthUser;
import com.adwareresearch.jsf.annotation.SpringSessionScoped;
import com.adwareresearch.jsf.util.JsfMessageUtil;
import com.adwareresearch.service.AuthUserService;

@Component
@SpringSessionScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = 6805510621201619373L;
	
	@Autowired
    private transient AuthUserService service;
    @Autowired
    private transient AuthenticationManager authenticationManager;
    @Autowired
    private SystemProperties properties;
    
    //Username field on login form (for Spring Security)
    private String username;
    //Password field on login form (for Spring Security)
    private String password;
    //Old user password (for changing password)
    private String oldPassword;
    //New user password (compare with old user password)
    private String newPassword;
    //Logged in user
    private AuthUser user;
    
    public String login() {
    	try {
    		Authentication request = new UsernamePasswordAuthenticationToken(getUsername(), getPassword());
    		Authentication result = authenticationManager.authenticate(request);
    		setUser((AuthUser) result.getPrincipal());
    		SecurityContextHolder.getContext().setAuthentication(result);
    		return "/pages/main.xhtml?faces-redirect=true";
    	} catch(LockedException ex) {
    		JsfMessageUtil.addErrorMessage("User is locked!");
    		LoggerFactory.getLogger(LoginController.class).error("User is locked!", ex); 
    	} catch(BadCredentialsException ex) {
    		JsfMessageUtil.addErrorMessage("Wrong login details!");
    		LoggerFactory.getLogger(LoginController.class).error("Wrong login details!", ex); 
    	} catch(AuthenticationException ex) {
    		JsfMessageUtil.addErrorMessage("Authentication error!");
    		LoggerFactory.getLogger(LoginController.class).error("Authentication error!", ex); 
    	}
    	return null;   	
    }
    
    public void update() {
    	try {
    		service.save(getUser());
    		JsfMessageUtil.addSuccessMessage("Profile updated");
    	} catch(DataAccessException ex) {
    		JsfMessageUtil.addErrorMessage("Update profile error");
            LoggerFactory.getLogger(LoginController.class).error("Update profile error", username);   
    	}
    }
    
    private boolean checkPassword(String oldPassword) {
    	return new BCryptPasswordEncoder().matches(oldPassword, getUser().getPassword());
    }
    
    public void changePassword() {
        if(!checkPassword(getOldPassword())) {
            JsfMessageUtil.addErrorMessage("Wrong current password!");
        } else {      	
        	try {
        		getUser().setPassword(new BCryptPasswordEncoder().encode(getNewPassword()));
        		getUser().setPasswordExpiry(getPasswordExpiry());
        		service.save(getUser());
        		setOldPassword("");
        		JsfMessageUtil.addSuccessMessage("Password changed");
        	} catch(DataAccessException ex) {
        		JsfMessageUtil.addErrorMessage("Update password error!");
                LoggerFactory.getLogger(LoginController.class).error("Update password error!", username); 
        	}    
        }
    }
    
    public void logout() {
        SecurityContextHolder.clearContext();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        try {
            ec.redirect(ec.getRequestContextPath() + "/index.xhtml");
        } catch (IOException ex) {
            LoggerFactory.getLogger(LoginController.class).error("Logout error!", username, ex); 
        }
    }
    
    public Date getPasswordExpiry() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, properties.getPasswordExpiry());
        return new Date(now.getTimeInMillis());
    }
    
    public boolean isAuthenticated() {
        return user != null ? true : false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthUser getUser() {
        return user;
    }

    public void setUser(AuthUser user) {
        this.user = user;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
