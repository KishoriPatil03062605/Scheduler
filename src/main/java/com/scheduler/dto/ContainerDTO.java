package com.scheduler.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scheduler.annotation.ContainerSchemaConstraint;

/**
 * The ContainerDTO is the Data transfer Object used to for transfer for
 * Container {@link com.scheduler.model.Container} data.
 *
 * @author Kishori Patil
 */
@ContainerSchemaConstraint
public class ContainerDTO {

	/** The id. */
	@JsonProperty("id")
	private String id;

	/** The name. */
	@JsonProperty("name")
	private String name;

	/** The image. */
	@JsonProperty("image")
	private String image;

	/** The status. */
	@JsonProperty("status")
	private String status;

	/** The scheduler hints. */
	@JsonProperty("scheduler-hints")
	private Map<String, String> schedulerHints;

	/** The node. */
	@JsonProperty("node")
	private NodeDTO node;

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
	 * Gets the image.
	 *
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Gets the scheduler hints.
	 *
	 * @return the scheduler hints
	 */
	public Map<String, String> getSchedulerHints() {
		return schedulerHints;
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
	 * Sets the image.
	 *
	 * @param image
	 *            the new image
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * Sets the status.
	 *
	 * @param status
	 *            the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Sets the scheduler hints.
	 *
	 * @param schedulerHints
	 *            the scheduler hints
	 */
	public void setSchedulerHints(Map<String, String> schedulerHints) {
		this.schedulerHints = schedulerHints;
	}

	/**
	 * Gets the node.
	 *
	 * @return the node
	 */
	public NodeDTO getNode() {
		return node;
	}

	/**
	 * Sets the node.
	 *
	 * @param node
	 *            the new node
	 */
	public void setNode(NodeDTO node) {
		this.node = node;
	}

}