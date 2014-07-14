package com.adwareresearch.jsf.controller;

import com.adwareresearch.domain.AuthRoles;
import com.adwareresearch.jsf.annotation.SpringViewScoped;
import com.adwareresearch.jsf.util.ColumnModel;
import com.adwareresearch.jsf.util.JsfMessageUtil;
import com.adwareresearch.service.AuthRolesService;

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
public class AuthRolesController implements Serializable {
    
	private static final long serialVersionUID = -6348094345993371230L;
	
	@Autowired
    private transient AuthRolesService authRolesService;
    private List<AuthRoles> list;
    private AuthRoles role;
    private List<ColumnModel> columns;
    
    public AuthRolesController() {
        this.role = new AuthRoles();
        this.columns = new ArrayList<>();
    }
    
    @PostConstruct
    public void init() {
        this.list = authRolesService.list();
        createDynamicColumns();
    }
    
    public void createDynamicColumns() {    
        columns.clear();        
        columns.add(new ColumnModel("Role name", "roleName")); 
    }
    
    public void saveOrUpdate() {
        try {
        	authRolesService.save(getRole());
            JsfMessageUtil.addSuccessMessage("Role saved!");
            setList(authRolesService.list());
            setRole(new AuthRoles());
        } catch(DataAccessException ex) {
            JsfMessageUtil.addErrorMessage("Error while saving role!");
            LoggerFactory.getLogger(AuthRolesController.class).error("Error while saving roles!");
        }
    }
    
    public void delete() {
       try {
    	   authRolesService.delete(getRole());
           JsfMessageUtil.addSuccessMessage("Role deleted!");
           setList(authRolesService.list());
           setRole(new AuthRoles()); 
        } catch(DataAccessException ex) {
            JsfMessageUtil.addErrorMessage("Error while deleting role!");
            LoggerFactory.getLogger(AuthRolesController.class).error("Error while deleting roles!");
        }
    }
    
    public void handleClose(CloseEvent event) {  
    	setRole(new AuthRoles());
    }

    public List<AuthRoles> getList() {
        return list;
    }

    public void setList(List<AuthRoles> list) {
        this.list = list;
    }

    public AuthRoles getRole() {
        return role;
    }

    public void setRole(AuthRoles role) {
        this.role = role;
    }

    public List<ColumnModel> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnModel> columns) {
        this.columns = columns;
    }
}
