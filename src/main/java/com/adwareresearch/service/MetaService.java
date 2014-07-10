package com.adwareresearch.service;

import com.adwareresearch.domain.Meta;
import java.util.List;

public interface MetaService {
    public void save(Meta meta);
    public void delete(Meta meta);
    public List<Meta> list();
}
