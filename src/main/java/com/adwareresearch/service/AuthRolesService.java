package com.adwareresearch.service;

import com.adwareresearch.domain.AuthRoles;
import java.util.List;

public interface AuthRolesService {
    public void save(AuthRoles role);
    public void delete(AuthRoles role);
    public List<AuthRoles> list();
}
