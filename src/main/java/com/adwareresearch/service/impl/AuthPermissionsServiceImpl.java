package com.adwareresearch.service.impl;

import com.adwareresearch.dao.AuthPermissionsDao;
import com.adwareresearch.domain.AuthPermissions;
import com.adwareresearch.service.AuthPermissionsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AuthPermissionsServiceImpl implements AuthPermissionsService {

    @Autowired
    private AuthPermissionsDao dao;
    
    @Override
    public void save(AuthPermissions permission) {
        dao.save(permission);
    }

    @Override
    public void delete(AuthPermissions permission) {
        dao.delete(permission);
    }

    @Override
    public List<AuthPermissions> list() {
        return dao.list();
    }

    @Override
    public List<AuthPermissions> findAvailablePermissions() {
        return dao.findAvailablePermissions();
    }
    
}
