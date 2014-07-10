package com.adwareresearch.dao.impl;

import com.adwareresearch.dao.AuthPermissionsDao;
import com.adwareresearch.dao.common.GenericDaoImpl;
import com.adwareresearch.domain.AuthPermissions;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class AuthPermissionsDaoImpl extends GenericDaoImpl<AuthPermissions, String> implements AuthPermissionsDao {

    public AuthPermissionsDaoImpl() {
        super(AuthPermissions.class);
    }

    @Override
    public void save(AuthPermissions permission) {
       saveOrUpdate(permission);
    }

    @Override
    public void delete(AuthPermissions permission) {
        remove(permission);
    }

    @Override
    public List<AuthPermissions> list() {
        return findAll();
    }

    @Override
    public List<AuthPermissions> findAvailablePermissions() {
        return findByNamedQueryWithoutParameter("findAvailablePermissions");
    }
    
}
