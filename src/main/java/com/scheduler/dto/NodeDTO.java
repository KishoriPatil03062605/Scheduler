package com.scheduler.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The NodeDTO is the Data transfer Object used to for transfer request for Node
 * {@link com.scheduler.model.Node} data.
 *
 * @author Kishori Patil
 */
public class NodeDTO {

	/** The id. */
	@JsonProperty("id")
	private String id;

	/** The name. */
	@JsonProperty("name")
	private String name;

	/** The scheduler hint. */
	@JsonProperty("scheduler-hints")
	private Map<String, String> schedulerHint;

	/** The capacity. */
	@JsonProperty("capacity")
	private CapacityDTO capacity;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
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
	public CapacityDTO getCapacity() {
		return capacity;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the capacity.
	 *
	 * @param capacity
	 *            the new capacity
	 */
	public void setCapacity(CapacityDTO capacity) {
		this.capacity = capacity;
	}

	/**
	 * Gets the scheduler hint.
	 *
	 * @return the scheduler hint
	 */
	public Map<String, String> getSchedulerHint() {
		return schedulerHint;
	}

	/**
	 * Sets the scheduler hint.
	 *
	 * @param schedulerHint
	 *            the scheduler hint
	 */
	public void setSchedulerHint(Map<String, String> schedulerHint) {
		this.schedulerHint = schedulerHint;
	}
}