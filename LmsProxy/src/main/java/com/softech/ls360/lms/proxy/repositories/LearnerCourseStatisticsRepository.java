package com.softech.ls360.lms.proxy.repositories;

import org.springframework.data.repository.CrudRepository;

import com.softech.ls360.lms.proxy.entities.LearnerCourseStatistics;

public interface LearnerCourseStatisticsRepository extends CrudRepository<LearnerCourseStatistics, Long>, LearnerCourseStatisticsRepositoryCustom {

	
}
