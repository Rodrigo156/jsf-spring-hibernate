package com.adwareresearch.service;

import com.adwareresearch.domain.AuthRolePermissionCategory;
import java.util.List;


public interface AuthRolePermissionCategoryService {
    public void save(AuthRolePermissionCategory category);
    public void delete(AuthRolePermissionCategory category);
    public List<AuthRolePermissionCategory> list();
}
