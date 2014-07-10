package com.adwareresearch.jsf.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adwareresearch.domain.AuthUser;
import com.adwareresearch.jsf.annotation.SpringSessionScoped;
import com.adwareresearch.service.AuthUserService;

@Component
@SpringSessionScoped
public class LoginController implements Serializable {
    
    @Autowired
    private AuthUserService service;
    
    //Username field on login form (for Shiro)
    private String username;
    //Password field on login form (for Shiro)
    private String password;
    //Old user password (for changing password)
    private String oldPassword;
    //New user password (compare with old user password)
    private String newPassword;
    //Remember me field on login form (for Shiro)
    private boolean remember;
    //Logged in user
    private AuthUser user;
    
    public String login() {
    	List<AuthUser> u =  service.findByUsername(username);
    	if(!u.isEmpty()) {
    		  setUser(u.get(0));
    		  return "/pages/main.xhtml?faces-redirect=true";
    	} else {
    		return null;
    	}
//        try {
//            
//            Subject currentUser = SecurityUtils.getSubject();
//            AuthenticationToken token = new UsernamePasswordToken(username, password,  remember);         
//            currentUser.login(token);                
//            setUser(service.findByUsername(username).get(0));
//
//            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//	    SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
//	    return savedRequest != null ? savedRequest.getRequestUrl().replace(request.getContextPath(), "")+"?faces-redirect=true" : "/pages/main.xhtml?faces-redirect=true";
//
//        } catch (UnknownAccountException | IndexOutOfBoundsException ex ) {            
//            LoggerFactory.getLogger(LoginController.class).error("Unknow account!", username, ex);
//            JsfMessageUtil.addErrorMessage("Unknow account!");
//        } catch (IncorrectCredentialsException ex ) {
//            LoggerFactory.getLogger(LoginController.class).error("Incorrect credentials!", username, ex);
//            JsfMessageUtil.addErrorMessage("Incorrect credentials!");
//        } catch (LockedAccountException ex ) {
//            LoggerFactory.getLogger(LoginController.class).error("Locked account!", username, ex);
//            JsfMessageUtil.addErrorMessage("Locked account!");
//        } catch(AuthenticationException ex){
//            LoggerFactory.getLogger(LoginController.class).error("Authentication exception!", username, ex);
//            JsfMessageUtil.addErrorMessage("Authentication exception!");
//        }
//        return null;
    }
    
    public void update() {
//        if(service.save(getUser())) {
//            JsfMessageUtil.addSuccessMessage("Profile updated");
//        } else {
//            JsfMessageUtil.addErrorMessage("Update profile error");
//            LoggerFactory.getLogger(LoginController.class).error("Update profile error", username);        
//        }
    }
    
//    private boolean checkPassword(String oldPassword) {
//        return PasswordGenerator.checkMatching(oldPassword, getUser().getPassword());
//    }
    
    public void changePassword() {
//        if(!checkPassword(getOldPassword())) {
//            JsfMessageUtil.addErrorMessage("Wrong current password!");
//        } else {
//            getUser().setPassword(PasswordGenerator.getEncryptedPassword(getNewPassword()));
//            if(service.save(getUser())) {
//                setOldPassword("");
//                JsfMessageUtil.addSuccessMessage("Password changed");
//            } else {
//                JsfMessageUtil.addErrorMessage("Update password error!");
//                LoggerFactory.getLogger(LoginController.class).error("Update password error!", username); 
//            }
//        }
    }
    
    public void logout() {
        //SecurityUtils.getSubject().logout();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        try {
            ec.redirect(ec.getRequestContextPath() + "/index.xhtml");
        } catch (IOException ex) {
            LoggerFactory.getLogger(LoginController.class).error("Logout error!", username, ex); 
        }
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

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
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
