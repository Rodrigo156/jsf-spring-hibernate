package com.adwareresearch.dao;

import com.adwareresearch.dao.common.GenericDao;
import com.adwareresearch.domain.Meta;

import java.util.List;

public interface MetaDao extends GenericDao<Meta, String> {
    public void save(Meta meta);
    public void delete(Meta meta);
    public List<Meta> list();
}
