package com.scheduler.exceptionhandler;

/**
 * The ContainerNotScheduledException is thrown when container
 * {@link com.scheduler.model.Container} is not scheduled to a Node
 * {@link com.scheduler.mode.Node}
 *
 * @author Kishori Patil
 */
public class NoSuitableNodeFoundException extends SchedulerAPIException {

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
	public NoSuitableNodeFoundException(String errorMessage) {
		super(errorMessage);
	}
}
