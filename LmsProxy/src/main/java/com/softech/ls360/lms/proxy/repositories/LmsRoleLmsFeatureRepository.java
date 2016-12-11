package com.softech.ls360.lms.proxy.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.softech.ls360.lms.proxy.entities.LmsRoleLmsFeature;

public interface LmsRoleLmsFeatureRepository extends CrudRepository<LmsRoleLmsFeature, Long> {

	List<LmsRoleLmsFeature> findByLmsRoleIdIn(Collection<Long> ids);
	
}
