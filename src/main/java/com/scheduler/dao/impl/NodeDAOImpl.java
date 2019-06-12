package com.scheduler.dao.impl;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scheduler.dao.NodeDAO;
import com.scheduler.datastore.NodeStore;
import com.scheduler.model.Node;

/**
 * The NodeDAOImpl is the Data access layer implementation to access the
 * DataStore NodeStore {@link com.scheduler.datastore.NodeStore} data. It
 * implements NodeDAO interface {@link NodeDAO}
 *
 * @author Kishori Patil
 */
@Component
public class NodeDAOImpl implements NodeDAO {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(NodeDAOImpl.class);

	/** The node store. */
	@Autowired
	NodeStore nodeStore;

	/*
	 * @inheritDoc
	 */
	@Override
	public Set<Node> getNodes() {
		LOGGER.debug("Getting the List of all nodes....");
		return nodeStore.getNodes();
	}

	@Override
	public Node getNodeById(UUID id) {
		Optional<Node> node = getNodes().stream().filter(nd -> nd.getId().toString().equals(id.toString())).findFirst();
		return node.orElse(null);
	}

}
