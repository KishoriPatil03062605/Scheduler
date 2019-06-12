package com.scheduler.enums;

/**
 * The Enum Status (PENDING, SCHEDULED, RUNNING) is used by 
 * {@link com.scheduler.model.Container} for maintaining its status
 * 
 * @author Kishori Patil
 */
public enum Status {

	/** The pending. */
	PENDING("pending"),
	/** The scheduled. */
	SCHEDULED("scheduled"),
	/** The running. */
	RUNNING("running");

	/** The status. */
	private String status;

	/**
	 * Instantiates a new status.
	 *
	 * @param status
	 *            the status
	 */
	Status(String status) {
		this.status = status;
	}

	/**
	 * Value of status.
	 *
	 * @param status
	 *            the status
	 * @return the status
	 */
	public static Status valueOfStatus(String status) {
		for (Status s : values()) {
			if (s.status.equals(status)) {
				return s;
			}
		}
		return null;
	}
}