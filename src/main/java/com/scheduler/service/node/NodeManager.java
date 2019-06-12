package com.scheduler.service.node;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.scheduler.exceptionhandler.NodeNotFoundException;
import com.scheduler.model.Node;

/**
 * The NodeManager is the service used for manage a Node
 * {@link com.scheduler.model.Node}
 *
 * @author Kishori Patil
 */

@Service
public interface NodeManager {

	/**
	 * <p>
	 * Get all Node {@link com.scheduler.model.Node} from NodeStore
	 * {@link com.scheduler.datastore.NodeStore}
	 * </p>
	 * 
	 * @return {@link Set} the all Nodes
	 */
	public Set<Node> getAllNodes();

	/**
	 * <p>
	 * Get Node {@link com.scheduler.model.Node} from NodeStore
	 * {@link com.scheduler.datastore.NodeStore} By ID
	 * </p>
	 *
	 * @param id
	 *            the id of the Node
	 * @return {@link Node} the Node
	 * @throws NodeNotFoundException
	 */
	public Node getNodeById(String id) throws NodeNotFoundException;

	/**
	 * <p>
	 * Update node {@link Node} using the Node ID by updating the used Capacity
	 * {@link com.scheduler.model.Capacity} and set the ThresholdReached
	 * {@link Boolean} to true if Total capacity == Used Capacity
	 * <p>
	 *
	 * @param selectedNode
	 *            {@link Node} the selected node
	 * @param reductionCount
	 *            the reduction count
	 */
	public void updateNodeCapacity(Node selectedNode, int reductionCount);

}
