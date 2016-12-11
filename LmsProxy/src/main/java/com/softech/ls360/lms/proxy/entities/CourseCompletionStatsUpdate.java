package com.softech.ls360.lms.proxy.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.softech.ls360.lms.proxy.enums.CourseStatus;

@JsonInclude(Include.ALWAYS)
public class CourseCompletionStatsUpdate {

	private String customerId;   // customerGuid
	private Long enrollmentId;
	private String productId;    // courseGuid;
	private String userName;
	private CourseStatus courseStatus;
	
	@JsonDeserialize(using = LocalDateTimeDeserializer.class) 
	@JsonSerialize(using=LocalDateTimeSerializer.class)
	private LocalDateTime completionDate;
	private String averagePostTestScore;
	private String lowestPostTestScore;
	private String highestPostTestScore;
	private Integer numberOfPostTestAttempts;
	private Integer totalTimeSpentInMins;
	
	@JsonDeserialize(using = LocalDateTimeDeserializer.class) 
	@JsonSerialize(using=LocalDateTimeSerializer.class)
	private LocalDateTime firstPostTestDate;
	
	@JsonDeserialize(using = LocalDateTimeDeserializer.class) 
	@JsonSerialize(using=LocalDateTimeSerializer.class)
	private LocalDateTime lastPostTestDate;
	
	private String errorCode;
	private String errorMessage;
	
	public String getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public Long getEnrollmentId() {
		return enrollmentId;
	}
	
	public void setEnrollmentId(Long enrollmentId) {
		this.enrollmentId = enrollmentId;
	}
	
	public String getProductId() {
		return productId;
	}
	
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public CourseStatus getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(CourseStatus courseStatus) {
		this.courseStatus = courseStatus;
	}

	public String getAveragePostTestScore() {
		return averagePostTestScore;
	}
	
	public void setAveragePostTestScore(String averagePostTestScore) {
		this.averagePostTestScore = averagePostTestScore;
	}
	
	public String getLowestPostTestScore() {
		return lowestPostTestScore;
	}
	
	public void setLowestPostTestScore(String lowestPostTestScore) {
		this.lowestPostTestScore = lowestPostTestScore;
	}
	
	public String getHighestPostTestScore() {
		return highestPostTestScore;
	}
	
	public void setHighestPostTestScore(String highestPostTestScore) {
		this.highestPostTestScore = highestPostTestScore;
	}
	
	public Integer getNumberOfPostTestAttempts() {
		return numberOfPostTestAttempts;
	}
	
	public void setNumberOfPostTestAttempts(Integer numberOfPostTestAttempts) {
		this.numberOfPostTestAttempts = numberOfPostTestAttempts;
	}
	public Integer getTotalTimeSpentInMins() {
		return totalTimeSpentInMins;
	}
	
	public void setTotalTimeSpentInMins(Integer totalTimeSpentInMins) {
		this.totalTimeSpentInMins = totalTimeSpentInMins;
	}
	
	//@JsonSerialize(using=LocalDateTimeSerializer.class)
	public LocalDateTime getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(LocalDateTime completionDate) {
		this.completionDate = completionDate;
	}

	//@JsonSerialize(using=LocalDateTimeSerializer.class)
	public LocalDateTime getFirstPostTestDate() {
		return firstPostTestDate;
	}

	public void setFirstPostTestDate(LocalDateTime firstPostTestDate) {
		this.firstPostTestDate = firstPostTestDate;
	}

	
	//@JsonDeserialize(using = LocalDateTimeDeserializer.class) 
	//@JsonSerialize(using=LocalDateTimeSerializer.class)
	public LocalDateTime getLastPostTestDate() {
		return lastPostTestDate;
	}

	public void setLastPostTestDate(LocalDateTime lastPostTestDate) {
		this.lastPostTestDate = lastPostTestDate;
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		
		return "CourseCompletionStatsUpdate [customerId=" + customerId
				+ ", enrollmentId=" + enrollmentId + ", productId=" + productId
				+ ", userName=" + userName + ", courseStatus=" + courseStatus
				+ ", completionDate=" + completionDate
				+ ", averagePostTestScore=" + averagePostTestScore
				+ ", lowestPostTestScore=" + lowestPostTestScore
				+ ", highestPostTestScore=" + highestPostTestScore
				+ ", numberOfPostTestAttempts=" + numberOfPostTestAttempts
				+ ", totalTimeSpentInMins=" + totalTimeSpentInMins + ", firstPostTestDate="
				+ firstPostTestDate + ", lastPostTestDate=" + lastPostTestDate
				+ "]";
		
	}
	
}
