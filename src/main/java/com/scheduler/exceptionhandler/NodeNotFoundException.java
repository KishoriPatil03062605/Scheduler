package com.scheduler.exceptionhandler;


/**
 * The NodeNotFoundException is thrown when Node {@link com.scheduler.model.Node} is not found in the NodeStore
 *  {@link com.scheduler.datastore.NodeStore} data.
 *
  * @author Kishori Patil
 */
public class NodeNotFoundException extends SchedulerAPIException {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Instantiates a new container not found exception.
     *
     * @param errorMessage the error message
     */
    public NodeNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
