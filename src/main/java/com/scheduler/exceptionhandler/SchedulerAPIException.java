package com.scheduler.exceptionhandler;

/**
 * The SchedulerAPIException is thrown when container
 * {@link com.scheduler.model.Container} is not scheduled to a Node
 * {@link com.scheduler.mode.Node}
 *
 * @author Kishori Patil
 */
public class SchedulerAPIException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new container not found exception.
	 *
	 * @param errorMessage
	 *            the error message
	 */
	public SchedulerAPIException(String errorMessage) {
		super(errorMessage);
	}
}
