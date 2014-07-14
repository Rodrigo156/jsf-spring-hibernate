package com.adwareresearch.service.impl;

import com.adwareresearch.dao.AuthRolesDao;
import com.adwareresearch.domain.AuthRoles;
import com.adwareresearch.service.AuthRolesService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AuthRolesServiceImpl implements AuthRolesService {

    @Autowired
    private AuthRolesDao dao;
    
    @Override
    public void save(AuthRoles role) {
        dao.save(role);
    }

    @Override
    public void delete(AuthRoles role) {
        dao.delete(role);
    }

    @Override
    public List<AuthRoles> list() {
        return dao.list();
    }

	@Override
	public List<AuthRoles> findByRoleName(String roleName) {
		return dao.findByRoleName(roleName);
	}
    
}
