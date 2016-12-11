package com.softech.ls360.lms.proxy.dao;

import java.io.Serializable;

public interface GenericDao<T extends Serializable> {
	
	T findById(final long id);
	void create(final T entity);
	void update(final T entity);
	void save(final T entity);
	void delete(final T entity);
	void deleteById(final long entityId);

}
