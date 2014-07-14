package com.adwareresearch.jsf.controller;

import com.adwareresearch.domain.AuthRolePermissionCategory;
import com.adwareresearch.jsf.annotation.SpringViewScoped;
import com.adwareresearch.jsf.util.ColumnModel;
import com.adwareresearch.jsf.util.JsfMessageUtil;
import com.adwareresearch.service.AuthRolePermissionCategoryService;

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
public class AuthRolePermissionCategoryController implements Serializable{
    
	private static final long serialVersionUID = -2538030228581347654L;
	
	@Autowired
    private transient AuthRolePermissionCategoryService permissionCategoryService;
    private List<AuthRolePermissionCategory> list;
    private List<ColumnModel> columns;
    private AuthRolePermissionCategory category;
    
    public AuthRolePermissionCategoryController() {
        this.columns = new ArrayList<>();
        this.category = new AuthRolePermissionCategory();
    }

    @PostConstruct
    public void init() {
        this.list = permissionCategoryService.list();
        createDynamicColumns();
    }
    
    public void createDynamicColumns() {        
        columns.add(new ColumnModel("Category name", "categoryName")); 
    }
    
    public void saveOrUpdate() {
       try { 
        	permissionCategoryService.save(getCategory());
            JsfMessageUtil.addSuccessMessage("Permission category saved!");
            setList(permissionCategoryService.list());
            setCategory(new AuthRolePermissionCategory());
       } catch(DataAccessException ex) {
            JsfMessageUtil.addErrorMessage("Error while saving permission category!");
            LoggerFactory.getLogger(AuthRolePermissionCategoryController.class).error("Error while saving permission category!");
        }
    }
    
    public void delete() {
        try {
        	permissionCategoryService.delete(getCategory());
            JsfMessageUtil.addSuccessMessage("Permission category deleted!");
            setList(permissionCategoryService.list());
            setCategory(new AuthRolePermissionCategory());
        } catch(DataAccessException ex) {
            JsfMessageUtil.addErrorMessage("Error while deleting permission category!");
            LoggerFactory.getLogger(AuthRolePermissionCategoryController.class).error("Error while deleting permission category!");
        }
    }
    
    public void handleClose(CloseEvent event) {  
    	setCategory(new AuthRolePermissionCategory());
    }
    
    public List<AuthRolePermissionCategory> getList() {
        return list;
    }

    public void setList(List<AuthRolePermissionCategory> list) {
        this.list = list;
    }

    public List<ColumnModel> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnModel> columns) {
        this.columns = columns;
    }

    public AuthRolePermissionCategory getCategory() {
        return category;
    }

    public void setCategory(AuthRolePermissionCategory category) {
        this.category = category;
    }
}
