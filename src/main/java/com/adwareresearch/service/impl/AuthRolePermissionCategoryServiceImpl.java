package com.adwareresearch.service.impl;

import com.adwareresearch.dao.AuthRolePermissionCategoryDao;
import com.adwareresearch.domain.AuthRolePermissionCategory;
import com.adwareresearch.service.AuthRolePermissionCategoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AuthRolePermissionCategoryServiceImpl implements AuthRolePermissionCategoryService {

    @Autowired
    private AuthRolePermissionCategoryDao dao;
    
    @Override
    public void save(AuthRolePermissionCategory category) {
       dao.save(category);
    }

    @Override
    public void delete(AuthRolePermissionCategory category) {
       dao.delete(category);
    }

    @Override
    public List<AuthRolePermissionCategory> list() {
        return dao.list();
    }
    
}
