package com.scheduler.utility;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.scheduler.model.Capacity;
import com.scheduler.model.Node;

/**
 * This is a utility class for {@link Node}.
 *
 * @author Kishori Patil
 */

@Component
public class NodeUtils {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(NodeUtils.class);

	/**
	 * Select node by criteria i.e the scheduler hints Nodes which has all the
	 * required hints are eligible nodes
	 *
	 * @param schedulerHints
	 *            the scheduler hints {@link Map}
	 * @param nodes
	 *            the nodes {@link Set} of {@link Node}
	 * @return the {@link Set} of {@link Node}
	 */
	public Set<Node> findNodeByCriteria(Map<String, String> schedulerHints, Set<Node> nodes) {
		LOGGER.debug("Selecting the node based on the criteria of hints");
		return nodes.stream().filter(node -> doHintsMatch(schedulerHints, node.getSchedulerHints()))
				.filter(node -> node.getCapacity().getUsed() < node.getCapacity().getTotal())
				.collect(Collectors.toSet());
	}

	/**
	 * Select least busy node. where the {@link Node} with the minimum Used
	 * {@link Capacity} is selected
	 *
	 * @param nodes
	 *            the nodes {@link Set} of {@link Node}
	 * @return the node {@link Node}
	 */
	public Node findLeastBusyNode(Set<Node> nodes) {
		LOGGER.debug("Selecting the least busy node from the list of eligible nodes");
		return nodes.stream()
				.min((node1, node2) -> Integer.compare(node1.getCapacity().getUsed(), node2.getCapacity().getUsed()))
				.orElseThrow(NoSuchElementException::new);
	}

	/**
	 * Check node for match. Nodes which has all the required hints are eligible
	 * nodes
	 *
	 * @param schedulerHints1
	 *            the scheduler hints {@link Map}
	 * @param schedulerHints2
	 *            the node scheduler hints {@link Map}
	 * @return true, if successful {@link Boolean}
	 */
	private boolean doHintsMatch(Map<String, String> schedulerHints1, Map<String, String> schedulerHints2) {
		return schedulerHints1.entrySet().stream()
				.allMatch(hint -> hint.getValue().equals(schedulerHints2.get(hint.getKey())));

	}

}
