package com.softech.ls360.lms.proxy.entities;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.softech.ls360.lms.proxy.json.serializer.DoubleJsonSerializer;

@Entity
@SqlResultSetMappings({ 
	@SqlResultSetMapping(
        name="LearnerCourseStatisticsMapping", 
	    classes = {
	        @ConstructorResult(targetClass = LearnerCourseStatistics1.class,
	    	    columns={
	    		    @ColumnResult(name = "CustomerId" ),
	    		    @ColumnResult(name = "EnrollmentId", type=Long.class),
	    		    @ColumnResult(name = "ProductId"),
	    		    @ColumnResult(name = "UserName"),
	    		    @ColumnResult(name = "Score", type=Double.class),
	    		    @ColumnResult(name = "CompletionDate")
	    		}
	    	)
	    }
	)
})
 
@JsonInclude(value=Include.NON_NULL)  // it will included in serialization only if its value is non null.
public class LearnerCourseStatistics1 implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String customerId;
	
	@Id
	private Long enrollmentId;
	private String productId;
	private String userName;

	@JsonSerialize(using = DoubleJsonSerializer.class)
	private Double score;
	
	@JsonSerialize(using=LocalDateTimeSerializer.class)
	private LocalDateTime completionDate = null;
	
	public LearnerCourseStatistics1() {
		super();
	}

	public LearnerCourseStatistics1(String customerId, Long enrollmentId, String productId, String userName, 
			Double score, Date date) {
		super();
		this.customerId = customerId;
		this.enrollmentId = enrollmentId;
		this.productId = productId;
		this.userName = userName;
		this.score = score;
		
		Instant instant = Instant.ofEpochMilli(date.getTime());
	    LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
		
		this.completionDate = ldt;
		//this.completionDate = date;
	}

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

	public LocalDateTime getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(LocalDateTime completionDate) {
		this.completionDate = completionDate;
	}
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String toString() {
		return "LearnerCourseStatistics - Customer Id: " + customerId 
				+ ", Enrollment Id: " + enrollmentId 
				+ ", Product Id: " + productId 
				+ ", User Name : " + userName 
				+ ", Score : " + score 
				+ ", completionDate : " + completionDate ;
	}
	
}
