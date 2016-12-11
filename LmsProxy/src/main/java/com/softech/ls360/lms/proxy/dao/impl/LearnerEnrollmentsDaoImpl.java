package com.softech.ls360.lms.proxy.dao.impl;

import java.util.List;
import java.util.Properties;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softech.ls360.lms.proxy.dao.AbstractJpaDao;
import com.softech.ls360.lms.proxy.dao.LearnerEnrollmentsDao;
import com.softech.ls360.lms.proxy.entities.Enrollments;
import com.softech.ls360.proxy.api.util.SpringUtil;

@Repository("lmsProxyEnrollmentsDao") 
@Transactional
public class LearnerEnrollmentsDaoImpl extends AbstractJpaDao<Enrollments> implements LearnerEnrollmentsDao {
	
	Properties enrollmentPropertiesFile = null;
	
	{
		enrollmentPropertiesFile = SpringUtil.loadPropertiesFileFromClassPath("database/sql/enrollments.properties");
	}

	public LearnerEnrollmentsDaoImpl() {
		super();
		setClazz(Enrollments.class);
	}
	
	@Override
	public List<Enrollments> findEnrollmentByDistributorId(Long distributorId, String enrollmentStartDate, String enrollmentEndDate) throws Exception {
		
		String queryString = enrollmentPropertiesFile.getProperty("enrollments.by.distributor.id.sql");
		
		//String distributorId = 1;
		enrollmentStartDate = enrollmentStartDate.replace("T", " ");
		enrollmentEndDate = enrollmentEndDate.replace("T", " ");
		
		Query query = entityManager.createNativeQuery(queryString, "Enrollments.findEnrollmentByDistributorIdMapping");
		 
		 query.setParameter("distributorId", distributorId);
		 query.setParameter("startDate", enrollmentStartDate);
		 query.setParameter("enDdate", enrollmentEndDate);
		 
		 List<Enrollments> enrollmentsList = query.getResultList();
		 return enrollmentsList;
	}
	
	@Override
	public List<Enrollments> findEnrollmentsByUserNameAndCoursesGuid(String userName, List<String> coursesGuid) throws Exception {
		
		String queryString = enrollmentPropertiesFile.getProperty("enrollments.id.by.username.and.coursesguid");
		
		Query query = entityManager.createNativeQuery(queryString, "Enrollments.findEnrollmentsIdByUserNameAndCourseGuidsMapping");
		 
		 query.setParameter("userName", userName);
		 query.setParameter("coursesGuid", coursesGuid);
		
		 List<Enrollments> enrollmentsList = query.getResultList();
		 return enrollmentsList;
	}

}
