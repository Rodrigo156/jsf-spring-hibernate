package com.adwareresearch.jsf.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.event.CloseEvent;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.adwareresearch.domain.AuthUser;
import com.adwareresearch.jsf.annotation.SpringViewScoped;
import com.adwareresearch.jsf.util.ColumnModel;
import com.adwareresearch.jsf.util.JsfMessageUtil;
import com.adwareresearch.service.AuthUserService;

@Component
@SpringViewScoped
public class UsersController implements Serializable {
    
	private static final long serialVersionUID = 1060502601276141243L;
	
	@Autowired
    private transient AuthUserService userDataService;
    @Autowired
    private transient SystemProperties properties;
    
    private List<AuthUser> users;
    private List<ColumnModel> columns;
    private AuthUser user;

    public UsersController() {
        this.columns = new ArrayList<>();
        this.user = new AuthUser();
    }
    
    @PostConstruct
    public void init() {
        this.users = userDataService.list();
        createDynamicColumns();
    }
    
    public void createDynamicColumns() {    
        columns.clear();        
        columns.add(new ColumnModel("Username", "userName")); 
        columns.add(new ColumnModel("First Name", "firstName")); 
        columns.add(new ColumnModel("Last name", "lastName")); 
        columns.add(new ColumnModel("E-mail", "email"));
    }
    
    public void saveOrUpdate() {
       if(!getUser().getPassword().isEmpty()) {
           getUser().setPassword(new BCryptPasswordEncoder().encode(getUser().getPassword()));
           getUser().setPasswordExpiry(getPasswordExpiry());
       }
        
       try {
        	userDataService.save(getUser());
            JsfMessageUtil.addSuccessMessage("User saved");
            setUser(new AuthUser());
            setUsers(userDataService.list());
        } catch(DataAccessException ex) {
            JsfMessageUtil.addErrorMessage("Error while saving user");
            LoggerFactory.getLogger(UsersController.class).error("Error while saving user", getUser().getUserName());
        }
    }
    
    public void register() {
        //getUser().getAuthUserRoleses().add(Role.EMPLOYEE);
        getUser().setPassword(new BCryptPasswordEncoder().encode(getUser().getPassword()));
        getUser().setPasswordExpiry(getPasswordExpiry());
        getUser().setUserActive(true);
        try {
        	userDataService.save(getUser());
            JsfMessageUtil.addSuccessMessage("User saved");
            setUser(new AuthUser());
            setUsers(userDataService.list());
        } catch(DataAccessException ex) {
            JsfMessageUtil.addErrorMessage("Error while saving user");
            LoggerFactory.getLogger(UsersController.class).error("Error while registering user", getUser().getUserName());
        }
    }
    
    public void delete() {
        try {
        	userDataService.delete(getUser());
            setUser(new AuthUser());
            setUsers(userDataService.list());
            JsfMessageUtil.addSuccessMessage("User deleted");
       } catch(DataAccessException ex) {
            JsfMessageUtil.addErrorMessage("Error while deleting user");
            LoggerFactory.getLogger(UsersController.class).error("Error while deleting user", getUser().getUserName());
        }
    }
    
    public void handleClose(CloseEvent event) {  
    	setUser(new AuthUser());
    }
    
    public Date getPasswordExpiry() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, properties.getPasswordExpiry());
        return new Date(now.getTimeInMillis());
    }

    public AuthUser getUser() {
        return user;
    }

    public void setUser(AuthUser user) {
        this.user = user;
    }
    
    public List<AuthUser> getUsers() {
        return users;
    }

    public void setUsers(List<AuthUser> users) {
        this.users = users;
    }

    public List<ColumnModel> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnModel> columns) {
        this.columns = columns;
    }
}
