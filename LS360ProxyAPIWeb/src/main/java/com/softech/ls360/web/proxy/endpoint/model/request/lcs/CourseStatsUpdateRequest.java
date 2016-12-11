package com.softech.ls360.web.proxy.endpoint.model.request.lcs;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.softech.ls360.lms.proxy.entities.CourseCompletionStatsUpdate;
import com.softech.ls360.lms.proxy.enums.CourseStatus;
import com.softech.ls360.util.SpringUtil;

@JsonDeserialize(using = CourseCompletionStatsUpdateJsonDeserializer.class)
public class CourseStatsUpdateRequest {

	@JsonSerialize(using = CourseCompletionStatsUpdateListJsonSerializer.class)
	private List<CourseCompletionStatsUpdate> courseStatsUpdate;
	private Map<CourseCompletionStatsUpdate, String> courseCompletionsStatsUpdateRequestErrorMap;
	private List<CourseCompletionStatsUpdate> courseCompletionStatsUpdateRequestErrorList;

	public List<CourseCompletionStatsUpdate> getCourseStatsUpdate() {
		return courseStatsUpdate;
	}

	public void setCourseStatsUpdate(List<CourseCompletionStatsUpdate> courseStatsUpdate) {
		this.courseStatsUpdate = courseStatsUpdate;
	}

	public Map<CourseCompletionStatsUpdate, String> getCourseCompletionsStatsUpdateRequestErrorMap() {
		return courseCompletionsStatsUpdateRequestErrorMap;
	}

	public void setCourseCompletionsStatsUpdateRequestErrorMap(Map<CourseCompletionStatsUpdate, String> courseCompletionsStatsUpdateRequestErrorMap) {
		this.courseCompletionsStatsUpdateRequestErrorMap = courseCompletionsStatsUpdateRequestErrorMap;
	}
	
	public List<CourseCompletionStatsUpdate> getCourseCompletionStatsUpdateRequestErrorList() {
		return courseCompletionStatsUpdateRequestErrorList;
	}

	public void setCourseCompletionStatsUpdateRequestErrorList(List<CourseCompletionStatsUpdate> courseCompletionStatsUpdateRequestErrorList) {
		this.courseCompletionStatsUpdateRequestErrorList = courseCompletionStatsUpdateRequestErrorList;
	}

	private static class CourseCompletionStatsUpdateListJsonSerializer extends JsonSerializer<List<CourseCompletionStatsUpdate>> {

		@Override
		public void serialize(List<CourseCompletionStatsUpdate> courseStatsUpdateList, JsonGenerator jGenerator, 
				SerializerProvider provider) throws IOException, JsonProcessingException {
		      
			int limit = 10;
			 
			if (CollectionUtils.isEmpty(courseStatsUpdateList)) {
				throw new IOException("Atleast one course stats provided");
				
			} else {
			    if (courseStatsUpdateList.size() > limit) {
					throw new IOException("Limit Exceed. Only " + limit + " course stats are allowed");
			    }
		    }
			
			jGenerator.writeStartArray();  // Create start of array tag  [
			for (CourseCompletionStatsUpdate courseStats : courseStatsUpdateList) {
				jGenerator.writeStartObject();  // {
				jGenerator.writeStringField("customerId", courseStats.getCustomerId());
				 jGenerator.writeNumberField("enrollmentId", courseStats.getEnrollmentId());
				 jGenerator.writeStringField("productId", courseStats.getProductId());
				 jGenerator.writeStringField("userName", courseStats.getUserName());
				 jGenerator.writeNumberField("courseStatus", courseStats.getCourseStatus().getId());
				 jGenerator.writeStringField("completionDate", courseStats.getCompletionDate().toString());
				 jGenerator.writeStringField("averagePostTestScore", courseStats.getAveragePostTestScore());
				 jGenerator.writeStringField("lowestPostTestScore", courseStats.getLowestPostTestScore());
				 jGenerator.writeStringField("highestPostTestScore", courseStats.getHighestPostTestScore());
				 jGenerator.writeNumberField("numberOfPostTestAttempts", courseStats.getNumberOfPostTestAttempts());
				 jGenerator.writeNumberField("totalTimeSpentInMins", courseStats.getTotalTimeSpentInMins());
				 jGenerator.writeStringField("firstPostTestDate", courseStats.getFirstPostTestDate().toString());
				 jGenerator.writeStringField("lastPostTestDate", courseStats.getLastPostTestDate().toString());
				jGenerator.writeEndObject();  // end of JSON Object tag }
			}
			jGenerator.writeEndArray();   // Create End of array tag  ]
	    }
	} //end of static inner class CourseCompletionStatsUpdateListJsonSerializer
	
} //end of class CourseStatsUpdateRequest

final class CourseCompletionStatsUpdateJsonDeserializer extends JsonDeserializer<CourseStatsUpdateRequest> {

    private static final String DATE_FORMAT = "(yyyy-MM-ddTHH:mm:ss)";
	 
	@Override
	public CourseStatsUpdateRequest deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
		
	    int limit = -1;
	    boolean isError = false;
	    Properties lfProperties = null;
	    final String ERROR_MESSAGE_SEPERATOR = ">";
	
	    try {
	    	lfProperties = SpringUtil.loadPropertiesFileFromClassPath("lf.properties");
	    } catch (Exception e) {
	    	limit = 10;
	    }
		
	    if (lfProperties != null) {
	    	String objectRequestLimit = lfProperties.getProperty("lf.course.stats.update.request.limit");
	    	limit = Integer.valueOf(objectRequestLimit);
	    }
		
	    CourseStatsUpdateRequest courseCompletionStatsUpdateRequest = new CourseStatsUpdateRequest();
	    List<CourseCompletionStatsUpdate> courseCompletionStatsUpdateList = new ArrayList<CourseCompletionStatsUpdate>();
	    List<CourseCompletionStatsUpdate> courseCompletionStatsUpdateRequestErrorList = new ArrayList<CourseCompletionStatsUpdate>();
		
		
	    ObjectCodec oc = jsonParser.getCodec();
	    JsonNode node = oc.readTree(jsonParser);
	    JsonNode courseStatsUpdateArray = node.get("courseStatsUpdate");
	    if (courseStatsUpdateArray == null) {
	    	throw new IOException("Invalid Json. Json should start with parameter \"courseStatsUpdate\" like --> {\"courseStatsUpdate\":[{..},{..}]}");
	    }
		
	    int courseStatsUpdateArraySize = courseStatsUpdateArray.size();
		
	    if (courseStatsUpdateArraySize < 1) {
	    	throw new IOException("Atleast one course stats should be present.");
	    }
		
	    if (courseStatsUpdateArraySize > limit) {
	    	throw new IOException("Limit Exceed. Only " + limit + " course stats are allowed.");
	    }
		
	    Iterator<JsonNode> jsonNodeIterator = node.iterator();
	    while(jsonNodeIterator.hasNext()) {
	    	JsonNode jsonArray = jsonNodeIterator.next();
	    	if (jsonArray.isArray()) {
	    		Iterator<JsonNode> jsonArrayIterator = jsonArray.iterator();
	    		while(jsonArrayIterator.hasNext()) {
	    			StringBuilder strBuilder = new StringBuilder();
	    			ObjectNode jsonObject = (ObjectNode)jsonArrayIterator.next();
	    			JsonNode customerIdFieldValue = jsonObject.get("customerId");
	    			JsonNode enrollmentIdFieldValue = jsonObject.get("enrollmentId");
	    			JsonNode productIdFieldValue = jsonObject.get("productId");
	    			JsonNode userNameFieldValue = jsonObject.get("userName");
	    			JsonNode courseStatusFieldValue = jsonObject.get("courseStatus");
	    			JsonNode completionDateFieldValue = jsonObject.get("completionDate");
	    			JsonNode averagePostTestScoreFieldValue = jsonObject.get("averagePostTestScore");
	    			JsonNode lowestPostTestScoreFieldValue = jsonObject.get("lowestPostTestScore");
	    			JsonNode highestPostTestScoreFieldValue = jsonObject.get("highestPostTestScore");
	    			JsonNode numberOfPostTestAttemptsFieldValue = jsonObject.get("numberOfPostTestAttempts");
	    			JsonNode totalTimeSpentInMinsFieldValue = jsonObject.get("totalTimeSpentInMins");
	    			JsonNode firstPostTestDateFieldValue = jsonObject.get("firstPostTestDate");
	    			JsonNode lastPostTestDateFieldValue = jsonObject.get("lastPostTestDate");
					
	    			String customerGuid = null;
	    			String enrollmentId = null;
	    			String courseGuid = null;
	    			String userName = null;
	    			String courseStatus = null;
	    			String completionDate = null;
	    			String averagePostTestScore = null;
	    			String lowestPostTestScore = null;
	    			String highestPostTestScore = null;
	    			String numberOfPostTestAttempts = null;
	    			String totalTimeSpentInMins = null;
	    			String firstPostTestDate = null;
	    			String lastPostTestDate = null;
					
	    			Long reqEnrollmentId = null;
	    			Integer reqTotalTimeSpentInMins = null;
	    			Integer courseStatusId = null;
	    			Integer reqNumberOfPostTestAttempts = null;
					
	    			LocalDateTime completionDateTime = null;
	    			LocalDateTime firstPostTestDateTime = null;
	    			LocalDateTime lastPostTestDateTime = null;
					
	    			if (customerIdFieldValue != null && !customerIdFieldValue.isNull()) {
	    				customerGuid = customerIdFieldValue.asText();
	    			}
					
	    			if (enrollmentIdFieldValue != null && !enrollmentIdFieldValue.isNull()) {
	    				enrollmentId = enrollmentIdFieldValue.asText();
	    			} 
					
    				if (productIdFieldValue != null && !productIdFieldValue.isNull()) {
    					courseGuid = productIdFieldValue.asText();
    				}
					
    				if (userNameFieldValue != null && !userNameFieldValue.isNull()) {
    					userName = userNameFieldValue.asText();
    				}
				
    				if (courseStatusFieldValue != null && !courseStatusFieldValue.isNull()) {
    					courseStatus = courseStatusFieldValue.asText();
    				}
				
    				if (completionDateFieldValue != null && !completionDateFieldValue.isNull()) {
    					completionDate = completionDateFieldValue.asText();
    				}
				
    				if (averagePostTestScoreFieldValue != null && !averagePostTestScoreFieldValue.isNull()) {
    					averagePostTestScore = averagePostTestScoreFieldValue.asText();
    				}
				
    				if (lowestPostTestScoreFieldValue != null && !lowestPostTestScoreFieldValue.isNull()) {
    					lowestPostTestScore = lowestPostTestScoreFieldValue.asText();
    				}
				
    				if (highestPostTestScoreFieldValue != null && !highestPostTestScoreFieldValue.isNull()) {
    					highestPostTestScore = highestPostTestScoreFieldValue.asText();
    				}
				
    				if (numberOfPostTestAttemptsFieldValue != null && !numberOfPostTestAttemptsFieldValue.isNull()) {
    					numberOfPostTestAttempts = numberOfPostTestAttemptsFieldValue.asText();
    				}
				
    				if (totalTimeSpentInMinsFieldValue != null && !totalTimeSpentInMinsFieldValue.isNull()) {
    					totalTimeSpentInMins = totalTimeSpentInMinsFieldValue.asText();
    				}
				
    				if (firstPostTestDateFieldValue != null && !firstPostTestDateFieldValue.isNull()) {
    					firstPostTestDate = firstPostTestDateFieldValue.asText();
    				}
				
    				if (lastPostTestDateFieldValue != null && !lastPostTestDateFieldValue.isNull()) {
    					lastPostTestDate = lastPostTestDateFieldValue.asText();
    				}
				
    				if (StringUtils.isBlank(customerGuid)) {
    					strBuilder.append(getMandatoryErrorMessage("customerId") + ERROR_MESSAGE_SEPERATOR);
    					isError = true;
    				}
					 
    				if (StringUtils.isBlank(enrollmentId)) {
    					strBuilder.append(getMandatoryErrorMessage("enrollmentId") + ERROR_MESSAGE_SEPERATOR);
    					isError = true;
    				} else {
    					try {
    						reqEnrollmentId = Long.valueOf(enrollmentId);
    						if (reqEnrollmentId < 1) {
    							strBuilder.append("enrollmentId should be greater than 0" + ERROR_MESSAGE_SEPERATOR);
    							isError = true;
    						}
    					} catch (NumberFormatException e) {
    						strBuilder.append(getIntegerErrorMessage("enrollmentId") + ERROR_MESSAGE_SEPERATOR);
    						isError = true;
    					}
    				}
					 
    				if (StringUtils.isBlank(courseGuid)) {
    					strBuilder.append(getMandatoryErrorMessage("productId") + ERROR_MESSAGE_SEPERATOR);
    					isError = true;
    				}
				 
    				if (StringUtils.isBlank(userName)) {
    					strBuilder.append(getMandatoryErrorMessage("userName") + ERROR_MESSAGE_SEPERATOR);
    					isError = true;
    				}
					 
    				if (StringUtils.isBlank(courseStatus)) {
    					strBuilder.append("courseStatus is mandotory and should be numeric in range(0-5)" + ERROR_MESSAGE_SEPERATOR);
    					isError = true;
    				} else {
    					try {
    						courseStatusId = Integer.valueOf(courseStatus);
    						if (courseStatusId < 0 || courseStatusId > 5) {
    							strBuilder.append("courseStatus should be numeric in range(0-5)" + ERROR_MESSAGE_SEPERATOR);
    							isError = true;
    						}
    					} catch (NumberFormatException e) {
    						strBuilder.append("courseStatus should be numeric in range(0-5)" + ERROR_MESSAGE_SEPERATOR);
    						isError = true;
    					}
    				}
					 
    				if (StringUtils.isBlank(completionDate)) {
    					strBuilder.append(getMandatoryErrorMessage("completionDate") + ERROR_MESSAGE_SEPERATOR);
    					isError = true;
    				} else {
    					try {
    						completionDateTime = LocalDateTime.parse(completionDate);
    					} catch (Exception e) {
    						strBuilder.append(getDateFormatErrorMessage("completionDate", DATE_FORMAT) + ERROR_MESSAGE_SEPERATOR);
    						isError = true;
    					}
    				}
				
    				if (StringUtils.isBlank(highestPostTestScore)) {
    					strBuilder.append(getMandatoryErrorMessage("highestPostTestScore") + ERROR_MESSAGE_SEPERATOR);
    					isError = true;
    				} else {
    					try {
    						Double.valueOf(highestPostTestScore);
    					} catch (NumberFormatException e) {
    						strBuilder.append(getIntegerOrDecimalErrorMessage("highestPostTestScore") + ERROR_MESSAGE_SEPERATOR);
    						isError = true;
    					}
    				}
					
    				if (StringUtils.isNotBlank(totalTimeSpentInMins)) {
    					try {
    						reqTotalTimeSpentInMins = Integer.valueOf(totalTimeSpentInMins);
    					} catch (NumberFormatException e) {
    						strBuilder.append(getIntegerErrorMessage("totalTimeSpentInMins") + ERROR_MESSAGE_SEPERATOR);
    						isError = true;
    					}
    				}
				
    				if (StringUtils.isNotBlank(numberOfPostTestAttempts)) {
    					try {
    						reqNumberOfPostTestAttempts = Integer.valueOf(numberOfPostTestAttempts);
    					} catch (Exception e) {
    						strBuilder.append(getIntegerErrorMessage("numberOfPostTestAttempts") + ERROR_MESSAGE_SEPERATOR);
    						isError = true;
    					}
    				}
					
    				if (StringUtils.isNotBlank(averagePostTestScore)) {
    					try {
    						Double.valueOf(averagePostTestScore);
    					} catch (NumberFormatException e) {
    						strBuilder.append(getIntegerOrDecimalErrorMessage("averagePostTestScore") + ERROR_MESSAGE_SEPERATOR);
    						isError = true;
    					}
    				}
				
    				if (StringUtils.isNotBlank(lowestPostTestScore)) {
    					try {
    						Double.valueOf(lowestPostTestScore);
    					} catch (NumberFormatException e) {
    						strBuilder.append(getIntegerOrDecimalErrorMessage("lowestPostTestScore") + ERROR_MESSAGE_SEPERATOR);
    						isError = true;
    					}
    				}
					
    				if (StringUtils.isNotBlank(firstPostTestDate)) {
    					try {
    						firstPostTestDateTime = LocalDateTime.parse(firstPostTestDate);
    					} catch (Exception e) {
    						strBuilder.append(getDateFormatErrorMessage("firstPostTestDate", DATE_FORMAT) + ERROR_MESSAGE_SEPERATOR);
    						isError = true;
    					}
    				}
				
    				if (StringUtils.isNotBlank(lastPostTestDate)) {
    					try {
    						lastPostTestDateTime = LocalDateTime.parse(lastPostTestDate);
    					} catch (Exception e) {
    						strBuilder.append(getDateFormatErrorMessage("lastPostTestDate", DATE_FORMAT) + ERROR_MESSAGE_SEPERATOR);
    						isError = true;
    					}
    				}
				
    				CourseCompletionStatsUpdate courseCompletionStatsUpdate = new CourseCompletionStatsUpdate();
    				try {
    					courseCompletionStatsUpdate.setCustomerId(customerGuid);
    					courseCompletionStatsUpdate.setEnrollmentId(reqEnrollmentId);
    					courseCompletionStatsUpdate.setProductId(courseGuid);
    					courseCompletionStatsUpdate.setUserName(userName);
    					courseCompletionStatsUpdate.setAveragePostTestScore(averagePostTestScore);
    					courseCompletionStatsUpdate.setLowestPostTestScore(lowestPostTestScore);
    					courseCompletionStatsUpdate.setHighestPostTestScore(highestPostTestScore);
    					courseCompletionStatsUpdate.setNumberOfPostTestAttempts(reqNumberOfPostTestAttempts);
    					courseCompletionStatsUpdate.setTotalTimeSpentInMins(reqTotalTimeSpentInMins);
    					courseCompletionStatsUpdate.setCompletionDate(completionDateTime);
    					courseCompletionStatsUpdate.setFirstPostTestDate(firstPostTestDateTime);
    					courseCompletionStatsUpdate.setLastPostTestDate(lastPostTestDateTime);
					
    					if (courseStatusId != null) {
    						switch(Integer.valueOf(courseStatus)) {
    							case 0:
    								courseCompletionStatsUpdate.setCourseStatus(CourseStatus.NOT_STARTED);
    								break;
    							case 1:
    								courseCompletionStatsUpdate.setCourseStatus(CourseStatus.COMPLETD);
    								break;
    							case 2:
    								courseCompletionStatsUpdate.setCourseStatus(CourseStatus.INPROGRESS);
    								break;
    							case 3:
    								courseCompletionStatsUpdate.setCourseStatus(CourseStatus.REPORTED);
    								break;
    							case 4:
    								courseCompletionStatsUpdate.setCourseStatus(CourseStatus.AFFIDAVIT_PENDING);
    								break;
    							case 5:
    								courseCompletionStatsUpdate.setCourseStatus(CourseStatus.AFFIDAVIT_RECEIVED);
    								break;
    							default:
						       //strBuilder.append("courseStatus should be numeric in range(0-5)>");
						       //isError = true;
    						}
    					}
					
    					if (isError) {
    						//int lastIndexOfSeperator = strBuilder.lastIndexOf(ERROR_MESSAGE_SEPERATOR);
    						int errorMessageLength = strBuilder.length();
    						if (errorMessageLength > 0) {
    							strBuilder.deleteCharAt(errorMessageLength - 1);
    						}
    						//strBuilder.replace(lastIndexOfSeperator, lastIndexOfSeperator + 1, ".");
    						courseCompletionStatsUpdate.setErrorCode("1");
    						courseCompletionStatsUpdate.setErrorMessage(strBuilder.toString());
    						courseCompletionStatsUpdateRequestErrorList.add(courseCompletionStatsUpdate);
    						continue;
    					}
    					courseCompletionStatsUpdateList.add(courseCompletionStatsUpdate);
    				} catch (Exception e) {
    					String errorMessage = e.getMessage() + ". " ;
    					if (strBuilder != null) {
    						errorMessage = errorMessage + strBuilder.toString() + "."; 
    					}
    					courseCompletionStatsUpdate.setErrorCode("1");
    					courseCompletionStatsUpdate.setErrorMessage(errorMessage);
    					courseCompletionStatsUpdateRequestErrorList.add(courseCompletionStatsUpdate);
    				}
    				isError = false;
    			}
    		}
    	} // end of while 
	    courseCompletionStatsUpdateRequest.setCourseStatsUpdate(courseCompletionStatsUpdateList);
	    courseCompletionStatsUpdateRequest.setCourseCompletionStatsUpdateRequestErrorList(courseCompletionStatsUpdateRequestErrorList);
	    return courseCompletionStatsUpdateRequest;
    } //end of deserialize()
	
    private String getMandatoryErrorMessage(String argument) {
    	return argument + " is mandatory";
    }

    private String getIntegerErrorMessage(String argument) {
    	return argument + " should be integer";
    }

    private String getDateFormatErrorMessage(String argument, String format) {
    	return  "Invalid " + argument + " , date should be in format " + format;
    }
    
    private String getIntegerOrDecimalErrorMessage(String argument) {
    	return argument + " should be integer or decimal";
    }
		
} //end of final class CourseCompletionStatsUpdateJsonDeserializer
