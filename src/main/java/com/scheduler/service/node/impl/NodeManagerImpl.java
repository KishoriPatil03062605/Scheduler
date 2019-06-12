package com.scheduler.service.node.impl;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scheduler.dao.NodeDAO;
import com.scheduler.exceptionhandler.NodeNotFoundException;
import com.scheduler.model.Node;
import com.scheduler.service.node.NodeManager;
import com.scheduler.utility.Converter;

/**
 * The NodeManagerImpl is the service Implementation for Managing Node Node
 * {@link com.scheduler.model.Node} It implements NodeManager interface
 * {@link NodeManager}
 *
 * @author Kishori Patil
 */
@Component
public class NodeManagerImpl implements NodeManager {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(NodeManagerImpl.class);

	/** The node DAO impl. */
	@Autowired
	NodeDAO nodeDAOImpl;

	/** The converter. */
	@Autowired
	Converter converter;

	/*
	 * @InheritDocs
	 */
	@Override
	public Set<Node> getAllNodes() {
		return nodeDAOImpl.getNodes();
	}

	/*
	 * @InheritDocs
	 */
	@Override
	public Node getNodeById(String id) throws NodeNotFoundException {
		LOGGER.debug("Getting the node {}", id);
		Node node = nodeDAOImpl.getNodeById(UUID.fromString(id));

		if (node == null) {
			throw new NodeNotFoundException("Node not Found");
		}

		return node;
	}

	/*
	 * @InheritDocs
	 */
	@Override
	public void updateNodeCapacity(Node selectedNode, int reductionCount) {
		LOGGER.debug("Updating the node after a container is Scheduled.class..");

		Optional<Node> nodeToUpdate = nodeDAOImpl.getNodes().stream()
				.filter(nd -> nd.getId().toString().equalsIgnoreCase(selectedNode.getId().toString())).findFirst();
		if (nodeToUpdate.isPresent()) {
			int currentUsedCapacity = nodeToUpdate.get().getCapacity().getUsed();
			nodeToUpdate.get().getCapacity().setUsed(currentUsedCapacity + reductionCount);
			if (nodeToUpdate.get().getCapacity().getUsed() == nodeToUpdate.get().getCapacity().getTotal()) {
				nodeToUpdate.get().setThresholdReached(true);
			}

		}

	}

}
