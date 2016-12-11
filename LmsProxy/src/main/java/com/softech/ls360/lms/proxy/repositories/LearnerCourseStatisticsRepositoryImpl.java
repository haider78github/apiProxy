package com.softech.ls360.lms.proxy.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.softech.ls360.lms.proxy.entities.CourseCompletionStatsUpdate;
import com.softech.ls360.lms.proxy.entities.LearnerCourseStatistics1;
import com.softech.ls360.lms.proxy.enums.CourseStatus;
import com.softech.ls360.proxy.api.util.SpringUtil;

/**
 * The query method language is really quite powerful. There’s not a whole lot you can’t do with Spring Data interface methods. In 
 * almost all cases, you can simply create an interface, sit back, and let Spring Data do the work for you. Every once in a while, 
 * however, you’ll come across a situation that Spring Data can’t handle. One example of this is performing full-text searching.
 * 
 * Another example is user-generated dynamic queries. Usually you want to strictly structure the queries a user can perform on 
 * persisted data. Allowing a user to filter on virtually any property can have disastrous performance implications. But where you 
 * understand the risks and plan accordingly, such a tool can be extremely powerful and useful for your users. It is also not 
 * possible to achieve this using standard Spring Data query methods.
 * 
 * Although Spring Data does not provide a standard mechanism for performing dynamic queries, Spring Data JPA does provide two 
 * proprietary mechanisms for doing so using the JPA criteria API or Query's predicates. If you use JPA repositories, you can use one
 * of these mechanisms. If you use some other Spring Data repository type, you may still have to create your own mechanism for 
 * dynamic queries.
 * 
 * The first step in customizing an individual repository is to create an interface for that customization. This interface should be 
 * separate from the actual repository interface and should specify all methods (at least one) that have custom implementations for 
 * your repository. The repository interface should then extend the customization interface(LearnerCourseStatisticsRepositoryCustom).
 * 
 * You can name the repository and customization interfaces whatever you want. When Spring Data finds LearnerCourseStatisticsRepository, 
 * it first looks for a class in the same package named LearnerCourseStatisticsRepositoryImpl (or whatever you named the interface plus
 * Impl) and instantiates and wires that class as any ordinary Spring bean. This class should implement the 
 * LearnerCourseStatisticsRepositoryCustom interface and provide the behavior for its methods. Spring Data delegates to this 
 * implementation when the customized methods are invoked on the 
 * LearnerCourseStatisticsRepository. For all other methods, Spring Data provides the standard Spring Data implementations.
 * 
 * You added LearnerCourseStatisticsRepositoryCustom to the LearnerCourseStatisticsRepository interface, but Spring Data still doesn’t
 * know how to find the search method implementation. To solve this, you need a LearnerCourseStatisticsRepositoryImpl class. Notice that
 * this class implements only LearnerCourseStatisticsRepositoryCustom. It does not implement LearnerCourseStatisticsRepository because 
 * Spring Data JPA does that for you.
 * 
 * @author basit.ahmed
 *
 */
public class LearnerCourseStatisticsRepositoryImpl implements LearnerCourseStatisticsRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<LearnerCourseStatistics1> getLearnerCourseStatistics(String distributorId, String fromDate,String toDate) throws Exception {
		Properties coursePropertiesFile = SpringUtil.loadPropertiesFileFromClassPath("database/sql/course.properties");
		String queryString = coursePropertiesFile.getProperty("learner.course.statistics.sql");
		
		//String distributorId = 1;
		fromDate = fromDate.replace("T", " ");
		toDate = toDate.replace("T", " ");
		
		Query query = entityManager.createNativeQuery(queryString, "LearnerCourseStatisticsMapping");
		 
		 query.setParameter("distributorId", distributorId);
		 query.setParameter("fromDate", fromDate);
		 query.setParameter("toDate", toDate);
		 
		 List<LearnerCourseStatistics1> learnerCourseStatisticsList = query.getResultList();
		 return learnerCourseStatisticsList;
	}

	@Override
	public void updateLearnerCourseStatistics(CourseCompletionStatsUpdate courseStats) throws Exception {
		
		Long enrollmentId = courseStats.getEnrollmentId();
		CourseStatus courseStatus = courseStats.getCourseStatus();
		LocalDateTime completionDate = courseStats.getCompletionDate();
		String averagePostTestScore = courseStats.getAveragePostTestScore();
		String lowestPostTestScore = courseStats.getLowestPostTestScore();
		String highestPostTestScore = courseStats.getHighestPostTestScore();
		Integer numberOfPostTestAttempts = courseStats.getNumberOfPostTestAttempts();
		Integer totalTimeSpentInMins = courseStats.getTotalTimeSpentInMins();
		LocalDateTime firstPostTestDate = courseStats.getFirstPostTestDate();
		LocalDateTime lastPostTestDate = courseStats.getLastPostTestDate();
		
		Integer totalTimeSpentInSec = null;
		String completionDateAsStr = null;
		String firstPostTestDateAsStr = null;
		String lastPostTestDateAsStr = null;
		
		if (totalTimeSpentInMins != null) {
			totalTimeSpentInSec = totalTimeSpentInMins * 60;
		}
		
		if (completionDate != null) {
			completionDateAsStr = completionDate.toString();
		}
		
		if (firstPostTestDate != null) {
			firstPostTestDateAsStr = firstPostTestDate.toString();
		}
		
		if (lastPostTestDate != null) {
			lastPostTestDateAsStr = lastPostTestDate.toString();
		}
		
		Query query = entityManager.createNamedQuery("LearnerCourseStatistics.update");
		 
		switch(courseStatus) {
		    case COMPLETD:
		    case REPORTED:
		    case AFFIDAVIT_PENDING:
		    case AFFIDAVIT_RECEIVED:
		    	query.setParameter("status", courseStatus.getValue());
		    	query.setParameter("completed", true);
		    	break;
		    case NOT_STARTED:
		    case INPROGRESS:
		    	query.setParameter("status", courseStatus.getValue());
		    	query.setParameter("completed", false);
		    	break;
		}
		query.setParameter("averagePostTestScore", averagePostTestScore);
		query.setParameter("lowestPostTestScore", lowestPostTestScore);
		query.setParameter("highestPostTestScore", highestPostTestScore);
		query.setParameter("numberOfPostTestAttempts", numberOfPostTestAttempts);
		query.setParameter("totalTimeSpent", totalTimeSpentInSec);
		query.setParameter("firstPostTestDate", firstPostTestDateAsStr);
		query.setParameter("lastPostTestDate", lastPostTestDateAsStr);
		query.setParameter("completionDate", completionDateAsStr);
		query.setParameter("enrollmentId", enrollmentId);
		
		int updatedRecordCount = query.executeUpdate();
		System.out.println(updatedRecordCount + " record updated: " + courseStats);
		
		
	}

}
