package com.scheduler.controller;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.scheduler.dto.NodeDTO;
import com.scheduler.exceptionhandler.NodeNotFoundException;
import com.scheduler.model.Node;
import com.scheduler.service.node.NodeManager;
import com.scheduler.utility.Converter;

/**
 * The NodeController is the Rest Controller to for managing Node/s
 * {@link com.scheduler.model.Node}
 *
 * @author Kishori Patil
 */
@RestController
public class NodeController {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(NodeController.class);

	/** The node manager impl. */
	@Autowired
	NodeManager nodeManagerImpl;

	/** The converter. */
	@Autowired
	Converter converter;

	/**
	 * <p>
	 * Gets all Node/s {@link com.scheduler.model.Node} from the NodeStore
	 * {@link com.scheduler.datastore.NodeStore}
	 * </p>
	 * 
	 *
	 * @return {@link Set} the all nodes
	 */
	@GetMapping(path = "/nodes")
	public ResponseEntity<Set<NodeDTO>> getAllNodes() {
		LOGGER.debug("Get All Nodes ...");
		Set<Node> nodes = nodeManagerImpl.getAllNodes();
		Set<NodeDTO> nodeDtos = null;
		if (nodes != null) {
			nodeDtos = converter.convertNodeListToDTO(nodes);
		}
		return new ResponseEntity<>(nodeDtos, HttpStatus.OK);
	}

	/**
	 * <p>
	 * Get Node {@link com.scheduler.model.Node} By ID from NodeStore
	 * {@link com.scheduler.datastore.NodeStore}
	 * </p>
	 * 
	 * @param id
	 *            the id
	 * @return NodeResponseDTO {@link com.scheduler.dto.NodeResponseDTO}the node
	 *         by id
	 * @throws NodeNotFoundException
	 */
	@GetMapping(path = "/node/{nodeID}")
	public ResponseEntity<NodeDTO> getNodeById(@PathVariable("nodeID") String id) throws NodeNotFoundException {
		Node node = nodeManagerImpl.getNodeById(id);
		NodeDTO responseDTO = converter.convertToDTO(node);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);

	}
}
