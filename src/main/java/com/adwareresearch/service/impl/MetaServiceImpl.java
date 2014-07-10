package com.adwareresearch.service.impl;

import com.adwareresearch.dao.MetaDao;
import com.adwareresearch.domain.Meta;
import com.adwareresearch.service.MetaService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MetaServiceImpl implements MetaService {

    @Autowired
    private MetaDao dao;
    
    @Override
    public void save(Meta meta) {
        dao.save(meta);
    }

    @Override
    public void delete(Meta meta) {
        dao.delete(meta);
    }

    @Override
    public List<Meta> list() {
        return dao.list();
    }
    
}
