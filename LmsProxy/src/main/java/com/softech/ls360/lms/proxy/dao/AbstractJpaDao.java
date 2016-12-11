package com.softech.ls360.lms.proxy.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.util.CollectionUtils;

public abstract class AbstractJpaDao<T extends Serializable> implements GenericDao<T> {
	
	private Class<T> clazz;
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	public final void setClazz( Class<T> clazzToSet ){
	      this.clazz = clazzToSet;
	}
	
	public T findById(final long id){
		return entityManager.find( clazz, id );
	}
	
	public void create(T entity){
		entityManager.persist( entity );
	}
	
	public void save(T entity){
	    entityManager.persist( entity );
	}
	 
	public void update(T entity){
		entityManager.merge( entity );
	}
	
	public void delete(T entity){
		entityManager.remove( entity );
	}
	   
	public void deleteById(final long entityId){
		T entity = findById(entityId);
	    delete( entity );
	}
	
	protected T getResult(TypedQuery<T> query) {
		
		List<T> list = query.getResultList();
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}
	
	protected List<T> getResultList(TypedQuery<T> query) {
		
		List<T> list = query.getResultList();
		return CollectionUtils.isEmpty(list) ? null : list;
	}
	  
}
