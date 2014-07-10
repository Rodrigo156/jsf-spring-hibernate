package com.adwareresearch.dao.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericDaoImpl<E, I extends Serializable> implements GenericDao<E, I> {
    
	@Autowired
    private SessionFactory sessionFactory;
	
    /**
     * Given entity
     */
    private final Class<E> clazz;
    
    /**
     * Cosntructor to set entity
     * @param entityClass 
     */
    protected GenericDaoImpl(Class<E> clazz) {
        this.clazz = clazz;
    }
    
    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public E findById(int id) {
    	return (E) getCurrentSession().get(clazz, id);
    }
    
    @Override
    public void saveOrUpdate(E e) {
    	getCurrentSession().saveOrUpdate(e);
    }

    @Override
    public void remove(E e) {
    	getCurrentSession().delete(e);
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<E> findAll() {
    	return getCurrentSession().createQuery("from "+clazz.getName()).list();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<E> findByNamedQueryWithParameters(String queryName, HashMap<String, Object> parameters) {
    	Query query = getCurrentSession().getNamedQuery(queryName);
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query.list();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<E> findByNamedQueryWithoutParameter(String queryName) {
    	Query query = getCurrentSession().getNamedQuery(queryName);
		return query.list();
    }
}
