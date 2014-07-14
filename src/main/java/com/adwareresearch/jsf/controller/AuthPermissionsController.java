package com.adwareresearch.jsf.controller;

import com.adwareresearch.domain.AuthPermissions;
import com.adwareresearch.jsf.annotation.SpringViewScoped;
import com.adwareresearch.jsf.util.ColumnModel;
import com.adwareresearch.jsf.util.JsfMessageUtil;
import com.adwareresearch.service.AuthPermissionsService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.event.CloseEvent;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Component
@SpringViewScoped
public class AuthPermissionsController implements Serializable {
    
	private static final long serialVersionUID = 3643778230973222039L;
	
	@Autowired
    private transient AuthPermissionsService authPermissionsService;
    private List<AuthPermissions> list;
    private AuthPermissions permission;
    private List<ColumnModel> columns;

    public AuthPermissionsController() {
        this.permission = new AuthPermissions();
        this.columns = new ArrayList<>();
    }
    
    @PostConstruct
    public void init() {
        this.list = authPermissionsService.list();
        createDynamicColumns();
    }
    
    public void createDynamicColumns() {          
        columns.add(new ColumnModel("Permission name", "permissionName"));
        columns.add(new ColumnModel("Description", "description")); 
    }
    
    public void saveOrUpdate() {
    	try {
    		authPermissionsService.save(getPermission());
    		JsfMessageUtil.addSuccessMessage("Permission saved!");
            setList(authPermissionsService.list());
            setPermission(new AuthPermissions());
    	} catch(DataAccessException e) {
    		JsfMessageUtil.addErrorMessage("Error while saving permission!");
            LoggerFactory.getLogger(AuthPermissionsController.class).error("Error while saving permission!");
    	}    
    }
    
    public void delete() {
        try {    	
        	authPermissionsService.delete(getPermission());
            JsfMessageUtil.addSuccessMessage("Permission deleted!");
            setList(authPermissionsService.list());
            setPermission(new AuthPermissions());
        }catch(DataAccessException e) {
            JsfMessageUtil.addErrorMessage("Error while deleting permission!");
            LoggerFactory.getLogger(AuthPermissionsController.class).error("Error while deleting permission!");
        }
    }
    
    public void handleClose(CloseEvent event) {  
    	setPermission(new AuthPermissions());
    }

    public List<AuthPermissions> getList() {
        return list;
    }

    public void setList(List<AuthPermissions> list) {
        this.list = list;
    }

    public AuthPermissions getPermission() {
        return permission;
    }

    public void setPermission(AuthPermissions permission) {
        this.permission = permission;
    }

    public List<ColumnModel> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnModel> columns) {
        this.columns = columns;
    }
}
