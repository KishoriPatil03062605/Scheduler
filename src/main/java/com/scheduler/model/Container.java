package com.scheduler.model;
import java.util.Map;
import java.util.UUID;

import com.scheduler.enums.Status;

/**
 * Container is the entity we use to store the scheduled containers.
 *
 * @author Kishori Patil
 */
public class Container {
	
	/** The id of the container */
	private UUID id;

	/** The name of the container */
	private String name;

	/** The image of the container */
	private String image;

	/** The status of the container */
	private Status status;

	/** The scheduler hints of the container */
	private Map<String, String> schedulerHints;

	/** The node of the container */
	private Node node;

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
	public Status getStatus() {
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
	 * Sets the image.
	 *
	 * @param image the new image
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(Status status) {
		this.status = status;
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
	 * Gets the node.
	 *
	 * @return the node
	 */
	public Node getNode() {
		return node;
	}

	/**
	 * Sets the node.
	 *
	 * @param node the new node
	 */
	public void setNode(Node node) {
		this.node = node;
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
		Container other = (Container) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}