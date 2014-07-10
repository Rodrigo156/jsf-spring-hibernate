package com.adwareresearch.jsf.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adwareresearch.domain.AuthPermissions;
import com.adwareresearch.domain.AuthRolePermissionCategory;
import com.adwareresearch.jsf.annotation.SpringViewScoped;
import com.adwareresearch.jsf.converter.AuthPermissionsConverter;
import com.adwareresearch.service.AuthPermissionsService;
import com.adwareresearch.service.AuthRolePermissionCategoryService;

@Component
@SpringViewScoped
public class PermissionsAndCategoriesController implements Serializable {
    
    @Autowired
    private AuthRolePermissionCategoryService categoryService;
    @Autowired
    private AuthPermissionsService permissionCategoryService;
    private List<AuthPermissions> permissionList;
    private List<AuthRolePermissionCategory> categoryList;
    private List<AuthPermissions> selectedPermissions;
    private AuthRolePermissionCategory permissionCategory;
    private AuthPermissionsConverter permissionConverter;

    @PostConstruct
    public void init() {
        System.out.println("POST CONST");
        this.categoryList = categoryService.list();
        this.permissionList = permissionCategoryService.findAvailablePermissions();
        this.permissionConverter = new AuthPermissionsConverter(this.permissionList);
        this.selectedPermissions = new ArrayList<>();
    }
    
    public void addToCategory() {
        for(AuthPermissions p : selectedPermissions) {
            System.out.println(p.getPermissionName());
        }
    }

    public List<AuthRolePermissionCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<AuthRolePermissionCategory> categoryList) {
        this.categoryList = categoryList;
    }

    public AuthRolePermissionCategory getPermissionCategory() {
        return permissionCategory;
    }

    public void setPermissionCategory(AuthRolePermissionCategory permissionCategory) {
        this.permissionCategory = permissionCategory;
    }

    public List<AuthPermissions> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<AuthPermissions> permissionList) {
        this.permissionList = permissionList;
    }

    public List<AuthPermissions> getSelectedPermissions() {
        return selectedPermissions;
    }

    public void setSelectedPermissions(List<AuthPermissions> selectedPermissions) {
        System.err.println("SET sEL PER");
        this.selectedPermissions = selectedPermissions;
    }

    public AuthPermissionsConverter getPermissionConverter() {
        return permissionConverter;
    }

    public void setPermissionConverter(AuthPermissionsConverter permissionConverter) {
        this.permissionConverter = permissionConverter;
    }
}
