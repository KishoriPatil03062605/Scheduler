package com.scheduler.exceptionhandler;

import org.everit.json.schema.ValidationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The RestExceptionHandler is global exception handler It handles all the
 * exceptions and packs them in a consistent format
 * {@link com.scheduler.exceptionhandler.ApiError}
 *
 * @author Kishori Patil
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Malformed JSON request";
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
	}

	/**
	 * Builds the response entity.
	 *
	 * @param apiError
	 *            the api error
	 * @return the response entity
	 */
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	/**
	 * Handle JSON schema validation exception.
	 *
	 * @param ex
	 *            the ex
	 * @return the response entity
	 */
	@ExceptionHandler(ValidationException.class)
	protected ResponseEntity<Object> handleSchemaValidationException(final ValidationException ex) {
		String error = "JSON Schema Invalid.";
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));

	}

	/**
	 * Handle node not found exception.
	 *
	 * @param ex
	 *            the ex
	 * @return the response entity
	 */
	@ExceptionHandler(NodeNotFoundException.class)
	protected ResponseEntity<Object> handleNodeNotFoundException(final NodeNotFoundException ex) {
		String error = "Node Not Found";
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));

	}

	/**
	 * Handle container not found exception.
	 *
	 * @param ex
	 *            the ex
	 * @return the response entity
	 */
	@ExceptionHandler(ContainerNotFoundException.class)
	protected ResponseEntity<Object> handleContainerNotFoundException(final ContainerNotFoundException ex) {
		String error = "Container Not Found";
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));

	}

	/**
	 * Handle container not scheduled exception.
	 *
	 * @param ex
	 *            the ex
	 * @return the response entity
	 */
	@ExceptionHandler(NoSuitableNodeFoundException.class)
	protected ResponseEntity<Object> handleContainerNotScheduledException(final NoSuitableNodeFoundException ex) {
		String error = "Container Not Scheduled";
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));

	}
}