package com.scheduler.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The CapacityDTO is the Data transfer Object used to for transfer for Node
 * capacity {@link com.scheduler.model.Capacity} data.
 *
 * @author Kishori Patil
 */
public class CapacityDTO {

	/** The total. */
	@JsonProperty("total")
	@NotNull
	private int total;

	/** The used. */
	@JsonProperty("used")
	@NotNull
	private int used;

	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * Gets the used.
	 *
	 * @return the used
	 */
	public int getUsed() {
		return used;
	}

	/**
	 * Sets the total.
	 *
	 * @param total
	 *            the new total
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * Sets the used.
	 *
	 * @param used
	 *            the new used
	 */
	public void setUsed(int used) {
		this.used = used;
	}

}