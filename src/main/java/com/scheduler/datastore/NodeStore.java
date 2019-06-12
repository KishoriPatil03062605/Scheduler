package com.scheduler.datastore;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scheduler.model.Node;

/**
 * The NodeStore is the Data store i.e. in memory Database like storage for for
 * Node {@link com.scheduler.model.Node} data.
 *
 * @author Kishori Patil
 */
@Component
@Scope(value = "singleton")
public class NodeStore {

	@Autowired
	ObjectMapper objectMapper;

	/** The nodes. */
	private Set<Node> nodes;

	@PostConstruct
	void setUp() throws IOException {
		File nodesJSONFile = ResourceUtils.getFile("classpath:nodes.json");
		String fileContent = new String(Files.readAllBytes(nodesJSONFile.toPath()));
		this.nodes = objectMapper.readValue(fileContent, new TypeReference<Set<Node>>() {
		});
		this.nodes.stream().forEach((node) -> {
			Long random = System.currentTimeMillis();
			node.setId(UUID.nameUUIDFromBytes((String.valueOf(random) + node.getName()).getBytes()));
		});
	}

	/**
	 * Gets the nodes.
	 *
	 * @return the nodes
	 */
	public Set<Node> getNodes() {
		return nodes;
	}

	/**
	 * Sets the nodes.
	 *
	 * @param nodes
	 *            the new nodes
	 */
	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}

}
