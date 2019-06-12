package com.scheduler.model;
/**
* Capacity is the entity which is used by the Node entity
* Please see the {@link Node} class for true identity
* @author Kishori Patil
* 
*/
public class Capacity {
	
	/** The total Capacity*/
	private int total;

	/** The used Capacity*/
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
	 * @param total the new total
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * Sets the used.
	 *
	 * @param used the new used
	 */
	public void setUsed(int used) {
		this.used = used;
	}

}