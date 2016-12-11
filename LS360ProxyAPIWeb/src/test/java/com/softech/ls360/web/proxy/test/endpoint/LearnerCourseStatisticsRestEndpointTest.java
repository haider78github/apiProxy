package com.softech.ls360.web.proxy.test.endpoint;

import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.softech.ls360.lms.proxy.entities.CourseCompletionStatsUpdate;
import com.softech.ls360.lms.proxy.enums.CourseStatus;
import com.softech.ls360.proxy.api.util.json.JsonUtil;
import com.softech.ls360.web.proxy.endpoint.model.request.lcs.CourseStatsUpdateRequest;
import com.softech.ls360.web.proxy.test.TestUtil;
import com.softech.ls360.web.proxy.test.WebProxyAbstractTest;

public class LearnerCourseStatisticsRestEndpointTest extends WebProxyAbstractTest {

	@Test
	public void test1() {
		
	}
	
	//@Test
	public void updateLearnerCourseStatistics() {
		String lcsRestEndPoint = LOCAL_URL + "customer/enrollment/course/stats/update";
		List<CourseCompletionStatsUpdate> courseCompletionStatsUpdateList = getCourseCompletionUpdateList();
		CourseStatsUpdateRequest request = new CourseStatsUpdateRequest();
		request.setCourseStatsUpdate(courseCompletionStatsUpdateList);
		
		String courseCompletionStatsUpdateJson = JsonUtil.convertObjectToJson(request);
		System.out.println("courseCompletionUpdateJson: " + courseCompletionStatsUpdateJson);
		TestUtil.callwebService(DEV_USER_PASS, DEV_API_KEY, lcsRestEndPoint, "POST", 
				courseCompletionStatsUpdateJson);
	}
	
	//@Test
	public void getCourseCompletion() {
		String lcsRestEndPoint = LOCAL_URL + "reseller/coursecompletion";
		String fromDate = "2014-08-01T00:00:00";
		String toDate = "2014-11-18T23:59:59";
		String charset = "UTF-8";
		try {
			String requestParameters = String.format("?fromDate=%s&toDate=%s",
				     URLEncoder.encode(fromDate, charset),
				     URLEncoder.encode(toDate, charset));
			String courseControllerUrl = lcsRestEndPoint + requestParameters;
			TestUtil.callwebService(DEV_USER_PASS, DEV_API_KEY, courseControllerUrl, "GET", null);
			//TestUtil.callHttpsWebService(userPass, apiKey, courseControllerUrl, "GET", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private List<CourseCompletionStatsUpdate> getCourseCompletionUpdateList() {
		
		List<CourseCompletionStatsUpdate> courseCompletionUpdateList = new ArrayList();
		
		Long[] enrollmentIdArray = {180885L, 180886L, 180887L, 180888L, 180889L};
		CourseStatus[] courseStatusArray = {CourseStatus.NOT_STARTED, CourseStatus.COMPLETD, CourseStatus.INPROGRESS, CourseStatus.REPORTED, CourseStatus.AFFIDAVIT_PENDING};
		String[] averagePostTestScoreArray = {"99", "1", "51", "2", "3"};
		String[] lowestPostTestScoreArray = {"99", "0", "40", "xyz", "3"};
		String[] highestPostTestScoreArray = {"99", "1", "39", "1", "abc"};
		int[] numberOfPostTestAttemptsArray = {2, 2, 3, 2, 2};
		int[] totalTimeSpentInMinsArray = {12, 45, 120, 180, 34};
		LocalDateTime[] completionDateArray = getCompletionDateArray();
		LocalDateTime[] firstPostTestDateArray = getFirstPostTestDateArray();
		LocalDateTime[] lastPostTestDateArray = getLastPostTestDateArray();
		
		for (int i=0; i<5; i++) {
			CourseCompletionStatsUpdate courseCompletionStats = getCourseCompletionStatsUpdate("" + i, 
					enrollmentIdArray[i], "courseGuid-" + i, "userName-" + i, courseStatusArray[i], 
					completionDateArray[i], averagePostTestScoreArray[i], lowestPostTestScoreArray[i], 
					highestPostTestScoreArray[i], numberOfPostTestAttemptsArray[i], 
					totalTimeSpentInMinsArray[i], firstPostTestDateArray[i], lastPostTestDateArray[i]);
			courseCompletionUpdateList.add(courseCompletionStats);
		}
		return courseCompletionUpdateList;
	}
	
	private LocalDateTime[] getCompletionDateArray() {
		
		LocalDateTime completionDateTime1 = getLocalDateTime(2013, Month.DECEMBER, 1, 00, 00, 01);
		LocalDateTime completionDateTime2 = getLocalDateTime(2013, Month.NOVEMBER, 1, 01, 01, 01);
		LocalDateTime completionDateTime3 = getLocalDateTime(2013, Month.OCTOBER, 1, 02, 02, 02);
		LocalDateTime completionDateTime4 = getLocalDateTime(2013, Month.SEPTEMBER, 1, 03, 03, 03);
		LocalDateTime completionDateTime5 = getLocalDateTime(2013, Month.AUGUST, 1, 04, 04, 04);
		LocalDateTime[] completionDateArray ={completionDateTime1, completionDateTime2, completionDateTime3, completionDateTime4, completionDateTime5};
		return completionDateArray;
	}
	
	private LocalDateTime[] getFirstPostTestDateArray() {
		
		LocalDateTime firstPostTestDate1 = getLocalDateTime(2012, Month.JANUARY, 2, 00, 00, 01);
		LocalDateTime firstPostTestDate2 = getLocalDateTime(2012, Month.FEBRUARY, 2, 01, 01, 01);
		LocalDateTime firstPostTestDate3 = getLocalDateTime(2012, Month.MARCH, 2, 02, 02, 02);
		LocalDateTime firstPostTestDate4 = getLocalDateTime(2012, Month.APRIL, 2, 03, 03, 03);
		LocalDateTime firstPostTestDate5 = getLocalDateTime(2012, Month.MAY, 2, 04, 04, 04);
		LocalDateTime[] firstPostTestDateArray ={firstPostTestDate1, firstPostTestDate2, firstPostTestDate3, firstPostTestDate4, firstPostTestDate5};
		return firstPostTestDateArray;
	}
	
	private LocalDateTime[] getLastPostTestDateArray() {
		
		LocalDateTime lastPostTestDate1 = getLocalDateTime(2011, Month.DECEMBER, 1, 00, 00, 01);
		LocalDateTime lastPostTestDate2 = getLocalDateTime(2011, Month.NOVEMBER, 1, 01, 01, 01);
		LocalDateTime lastPostTestDate3 = getLocalDateTime(2011, Month.OCTOBER, 1, 02, 02, 02);
		LocalDateTime lastPostTestDate4 = getLocalDateTime(2011, Month.SEPTEMBER, 1, 03, 03, 03);
		LocalDateTime lastPostTestDate5 = getLocalDateTime(2011, Month.AUGUST, 1, 04, 04, 04);
		LocalDateTime[] lastPostTestDateArray ={lastPostTestDate1, lastPostTestDate2, lastPostTestDate3, lastPostTestDate4, lastPostTestDate5};
		return lastPostTestDateArray;
	}
	
	private CourseCompletionStatsUpdate getCourseCompletionStatsUpdate(String customerId, Long enrollmentId, 
			String productId, String userName, CourseStatus courseStatus, LocalDateTime completionDate, 
			String averagePostTestScore, String lowestPostTestScore, String highestPostTestScore, 
			int numberOfPostTestAttempts, int totalTimeSpentInMins, LocalDateTime firstPostTestDate, LocalDateTime lastPostTestDate) {
		
		CourseCompletionStatsUpdate courseCompletionUpdate = new CourseCompletionStatsUpdate();
		courseCompletionUpdate.setCustomerId(customerId);
		courseCompletionUpdate.setEnrollmentId(enrollmentId);
		courseCompletionUpdate.setProductId(productId);
		courseCompletionUpdate.setUserName(userName);
		courseCompletionUpdate.setCourseStatus(courseStatus);
		courseCompletionUpdate.setCompletionDate(completionDate);
		courseCompletionUpdate.setAveragePostTestScore(averagePostTestScore);
		courseCompletionUpdate.setLowestPostTestScore(lowestPostTestScore);
		courseCompletionUpdate.setHighestPostTestScore(highestPostTestScore);
		courseCompletionUpdate.setNumberOfPostTestAttempts(numberOfPostTestAttempts);
		courseCompletionUpdate.setTotalTimeSpentInMins(totalTimeSpentInMins);
		courseCompletionUpdate.setFirstPostTestDate(firstPostTestDate);
		courseCompletionUpdate.setLastPostTestDate(lastPostTestDate);
		return courseCompletionUpdate;
		
	}
	
	private LocalDateTime getLocalDateTime(int year, Month month, int dayOfMonth, int hour, int min, int sec) {
		
		/**
		 * LocalDate is an immutable class that represents Date with default format of yyyy-MM-dd. We can use 
		 * now() method to get the current date. We can also provide input arguments for year, month and date to
		 * create LocalDate instance. This class provides overloaded method for now() where we can pass ZoneId 
		 * for getting date in specific time zone. This class provides the same functionality as java.sql.Date.
		 */
		LocalDate date = LocalDate.of(year, month, dayOfMonth);
		
		/**
		 *  LocalTime is an immutable class whose instance represents a time in the human readable format. It’s 
		 *  default format is hh:mm:ss.zzz. Just like LocalDate, this class provides time zone support and 
		 *  creating instance by passing hour, minute and second as input arguments. 
		 */
		LocalTime time = LocalTime.of(hour,min,sec);
		
		/**
		 * LocalDateTime is an immutable date-time object that represents a date-time, with default format as 
		 * yyyy-MM-dd-HH-mm-ss.zzz. It provides a factory method that takes LocalDate and LocalTime input 
		 * arguments to create LocalDateTime instance. 
		 */
		LocalDateTime dateTime = LocalDateTime.of(date, time);
		return dateTime;
	}

}
