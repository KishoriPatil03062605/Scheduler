package com.scheduler.dao;

import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.scheduler.model.Node;

/**
 * The NodeDAO is the Data access layer interface to access the DataStore
 * NodeStore {@link com.scheduler.datastore.NodeStore} data
 *
 * @author Kishori Patil
 */
@Repository
public interface NodeDAO {

	/**
	 * <p>
	 * This method is used to get all the Nodes {@link Node} from NodeStore
	 * {@link com.scheduler.datastore.NodeStore}
	 * </p>
	 * 
	 * @return {@link Set} of Nodes {@link Node}
	 */
	public Set<Node> getNodes();

	/**
	 * <p>
	 * This method is used to get Node {@link Node} by Id from NodeStore
	 * {@link com.scheduler.datastore.NodeStore}
	 * </p>
	 * 
	 * @param id
	 * @return Node {@link Node}
	 */
	public Node getNodeById(UUID id);

}
