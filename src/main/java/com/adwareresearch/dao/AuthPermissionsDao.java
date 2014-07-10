package com.adwareresearch.dao;

import com.adwareresearch.dao.common.GenericDao;
import com.adwareresearch.domain.AuthPermissions;

import java.util.List;

public interface AuthPermissionsDao extends GenericDao<AuthPermissions, String> {
    public void save(AuthPermissions permission);
    public void delete(AuthPermissions permission);
    public List<AuthPermissions> list();
    public List<AuthPermissions> findAvailablePermissions();
}
