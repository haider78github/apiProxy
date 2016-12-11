package com.softech.ls360.lms.proxy.enums;

public enum EnrollmentStatus {

	/**
	 * You can specify values of enum constants at the creation time as shown in below:
			
			public enum CourseStatus {NOT_STARTED(0, "notstarted"), COMPLETD(1, "completed")..};

	 * But for this to work you need to define a member variables and a constructor because 
	 * NOT_STARTED(0, "notstarted") is actually calling a constructor which accepts int and string value
	 * 
	 * Enum constants are implicitly static and final and can not be changed once created.


	 */
	ACTIVE(0, "Active"), 
	EXPIRED(1, "Expired"), 
	SWAPPED(2, "Swapped"), 
	DROPPED(3, "Dropped"),
	INACTIVE(4, "Inactive"),
	UNLOCK(5, "Unlock");
	
	private Integer id;
	private String value;
	
	// Constructor of enum in java must be private any other access modifier will result in compilation error.
	private EnrollmentStatus(final Integer id, final String value) {
		this.id = id;
	    this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
