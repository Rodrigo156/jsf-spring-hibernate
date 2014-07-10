package com.adwareresearch.dao.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public interface GenericDao<E, I extends Serializable> {

    public E findById(int id);
	
    public void saveOrUpdate(E e);
    
    public void remove(E e);
    
    public List<E> findAll();
    
    /**
     * Find by a given named query with parameters
     * @param queryName named query
     * @param parameters query parameters in parameter - value format
     * @return result
     */
    public List<E> findByNamedQueryWithParameters(String queryName, HashMap<String, Object> parameters);

    /**
     * Find by a given named query without parameters
     * @param queryName named query
     * @return result
     */
    public List<E> findByNamedQueryWithoutParameter(String queryName);
}
