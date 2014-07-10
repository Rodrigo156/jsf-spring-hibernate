package com.adwareresearch.dao.impl;

import com.adwareresearch.dao.MetaDao;
import com.adwareresearch.dao.common.GenericDaoImpl;
import com.adwareresearch.domain.Meta;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class MetaDaoImpl extends GenericDaoImpl<Meta, String> implements MetaDao {

    public MetaDaoImpl() {
        super(Meta.class);
    }

    @Override
    public void save(Meta meta) {
        saveOrUpdate(meta);
    }

    @Override
    public void delete(Meta meta) {
        remove(meta);
    }

    @Override
    public List<Meta> list() {
        return findAll();
    }
    
}
