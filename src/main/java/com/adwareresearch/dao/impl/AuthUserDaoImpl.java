package com.adwareresearch.dao.impl;

import com.adwareresearch.dao.AuthUserDao;
import com.adwareresearch.dao.common.GenericDaoImpl;
import com.adwareresearch.domain.AuthUser;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class AuthUserDaoImpl extends GenericDaoImpl<AuthUser, String> implements AuthUserDao {

    protected AuthUserDaoImpl() {
        super(AuthUser.class);
    }

    @Override
    public void save(AuthUser user) {
        saveOrUpdate(user);
    }

    @Override
    public void delete(AuthUser user) {
        remove(user);
    }

    @Override
    public List<AuthUser> list() {
        return findAll();
    }

    @Override
    public List<AuthUser> findByUsernameAndPassword(String username, String password) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("username", username);
        parameters.put("password", password);
        return findByNamedQueryWithParameters("findByUsernameAndPassword", parameters);
    }

    @Override
    public List<AuthUser> findByUsername(String username) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("username", username);
        return findByNamedQueryWithParameters("findByUsername", parameters);
    }
    
}
