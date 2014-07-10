package com.adwareresearch.jsf.controller;

import com.adwareresearch.domain.AuthUser;
import com.adwareresearch.jsf.annotation.SpringViewScoped;
import com.adwareresearch.jsf.util.ColumnModel;
import com.adwareresearch.jsf.util.JsfMessageUtil;
import com.adwareresearch.service.AuthUserService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SpringViewScoped
public class UsersController implements Serializable {
    
    @Autowired
    private AuthUserService userDataService;
    @Autowired
    private SystemProperties properties;
    
    private List<AuthUser> users;
    private List<ColumnModel> columns;
    private AuthUser user;
    private String password;

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
//        if(!password.isEmpty() && !PasswordGenerator.checkMatching(password, getUser().getPassword())) {
//            getUser().setPassword(PasswordGenerator.getEncryptedPassword(password));
//            getUser().setPasswordExpiry(getPasswordExpiry());
//        }
        
       // if(
        		userDataService.save(getUser());
        		//) {
            JsfMessageUtil.addSuccessMessage("User saved");
            setUser(new AuthUser());
            setUsers(userDataService.list());
//        } else {
//            JsfMessageUtil.addErrorMessage("Error while saving user");
//            LoggerFactory.getLogger(UsersController.class).error("Error while saving user", getUser().getUserName());
//        }
    }
    
    public void register() {
        //getUser().getAuthUserRoleses().add(Role.EMPLOYEE);
        //getUser().setPassword(PasswordGenerator.getEncryptedPassword(getUser().getPassword()));
        getUser().setPasswordExpiry(getPasswordExpiry());
//        if(
        		userDataService.save(getUser());
        		//) {
            JsfMessageUtil.addSuccessMessage("User saved");
            setUser(new AuthUser());
            setUsers(userDataService.list());
//        } else {
//            JsfMessageUtil.addErrorMessage("Error while saving user");
//            LoggerFactory.getLogger(UsersController.class).error("Error while registering user", getUser().getUserName());
//        }
    }
    
    public void delete() {
        //if(
        		userDataService.delete(getUser());
        		//) {
            setUser(new AuthUser());
            setUsers(userDataService.list());
            JsfMessageUtil.addSuccessMessage("User deleted");
//        } else {
//            JsfMessageUtil.addErrorMessage("Error while deleting user");
//            LoggerFactory.getLogger(UsersController.class).error("Error while deleting user", getUser().getUserName());
//        }
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
