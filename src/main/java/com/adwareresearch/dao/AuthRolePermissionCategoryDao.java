package com.adwareresearch.dao;

import com.adwareresearch.dao.common.GenericDao;
import com.adwareresearch.domain.AuthRolePermissionCategory;

import java.util.List;

public interface AuthRolePermissionCategoryDao extends GenericDao<AuthRolePermissionCategory, String> {
    public void save(AuthRolePermissionCategory category);
    public void delete(AuthRolePermissionCategory category);
    public List<AuthRolePermissionCategory> list();
}
