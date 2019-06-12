package com.scheduler.validator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scheduler.annotation.ContainerSchemaConstraint;
import com.scheduler.dto.ContainerDTO;

/**
 * @author Kishori Patil
 *
 */
@Component
public class ContainerSchemaValidator implements ConstraintValidator<ContainerSchemaConstraint, ContainerDTO> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContainerSchemaValidator.class);

	@Autowired
	ObjectMapper objectMapper;

	Schema schema;

	@Override
	public void initialize(ContainerSchemaConstraint container) {
		try {
			File schemaFile = ResourceUtils.getFile("classpath:container-schema.json");
			String fileContent = new String(Files.readAllBytes(schemaFile.toPath()));
			JSONObject jsonSchema = new JSONObject(new JSONTokener(fileContent));
			schema = SchemaLoader.load(jsonSchema);
		} catch (JSONException | IOException e) {
			LOGGER.error("Schema Processing Error :: {}", e);
		}
	}

	@Override
	public boolean isValid(ContainerDTO container, ConstraintValidatorContext cxt) {
		try {
			schema.validate(new JSONObject(objectMapper.writeValueAsString(container)));
		} catch (JsonProcessingException | JSONException e) {
			LOGGER.error("JSON Processing Error :: {}", e);
		}

		return true;
	}

}