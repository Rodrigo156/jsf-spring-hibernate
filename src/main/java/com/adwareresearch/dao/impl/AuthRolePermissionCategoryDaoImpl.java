package com.adwareresearch.dao.impl;

import com.adwareresearch.dao.AuthRolePermissionCategoryDao;
import com.adwareresearch.dao.common.GenericDaoImpl;
import com.adwareresearch.domain.AuthRolePermissionCategory;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class AuthRolePermissionCategoryDaoImpl extends GenericDaoImpl<AuthRolePermissionCategory, String> implements AuthRolePermissionCategoryDao {

    public AuthRolePermissionCategoryDaoImpl() {
        super(AuthRolePermissionCategory.class);
    }

    @Override
    public void save(AuthRolePermissionCategory category) {
        saveOrUpdate(category);
    }

    @Override
    public void delete(AuthRolePermissionCategory category) {
        remove(category);
    }

    @Override
    public List<AuthRolePermissionCategory> list() {
        return findAll();
    }
    
}
