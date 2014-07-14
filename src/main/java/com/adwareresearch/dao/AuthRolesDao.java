package com.adwareresearch.dao;

import com.adwareresearch.dao.common.GenericDao;
import com.adwareresearch.domain.AuthRoles;

import java.util.List;

public interface AuthRolesDao extends GenericDao<AuthRoles, String> {
    public void save(AuthRoles role);
    public void delete(AuthRoles role);
    public List<AuthRoles> list();
    public List<AuthRoles> findByRoleName(String roleName);
}
