package com.scheduler.exceptionhandler;


/**
 * The ContainerNotFoundException is thrown when Container {@link com.scheduler.model.Container} is not found in the ContainerStore
 *  {@link com.scheduler.datastore.ContainerStore} data.
 *
  * @author Kishori Patil
 */
public class ContainerNotFoundException extends SchedulerAPIException {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Instantiates a new container not found exception.
     *
     * @param errorMessage the error message
     */
    public ContainerNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}