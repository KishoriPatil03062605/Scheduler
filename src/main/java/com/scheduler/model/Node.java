package com.scheduler.model;

import java.util.Map;
import java.util.UUID;
/**
 * Node is the entity we use to all the nodes which are available.
 *
 * @author Kishori Patil
 */
public class Node {

	/** The id of the Node */
	private UUID id;

	/** The name of the Node */
	private String name;

	/** The scheduler hint of the Node */
	private Map<String, String> schedulerHints;

	/** The capacity of the Node */
	private Capacity capacity;
	
	/** The threshold reached of the Node  */
	private boolean thresholdReached = false;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the capacity.
	 *
	 * @return the capacity
	 */
	public Capacity getCapacity() {
		return capacity;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the capacity.
	 *
	 * @param capacity the new capacity
	 */
	public void setCapacity(Capacity capacity) {
		this.capacity = capacity;
	}

	/**
	 * Get the scheduler hints.
	 *
	 * @return the scheduler hints
	 */
	public Map<String, String> getSchedulerHints() {
		return schedulerHints;
	}

	/**
	 * Sets the scheduler hints.
	 *
	 * @param schedulerHints the scheduler hints
	 */
	public void setSchedulerHints(Map<String, String> schedulerHints) {
		this.schedulerHints = schedulerHints;
	}

	/**
	 * Checks if is threshold reached.
	 *
	 * @return true, if is threshold reached
	 */
	public boolean isThresholdReached() {
		return thresholdReached;
	}

	/**
	 * Sets the threshold reached.
	 *
	 * @param thresholdReached the new threshold reached
	 */
	public void setThresholdReached(boolean thresholdReached) {
		this.thresholdReached = thresholdReached;
	}

	/* 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return id.hashCode();
	}

	/* 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
