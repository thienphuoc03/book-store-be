package com.htphuoc.bookstore.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import io.jsonwebtoken.SignatureException;

@RestControllerAdvice
public class ApiExceptionHandler {
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handlerNotFoundException(NotFoundException ex, WebRequest req) {
		return new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
	}

	@ExceptionHandler(ClientException.class)
	public ResponseEntity<ErrorDetails> handelClientException(Exception ex, HttpServletRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
				"BAD_REQUEST_ERROR", ex.getMessage(), request.getRequestURI());

		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ServerException.class)
	public ResponseEntity<ErrorDetails> handelServerException(Exception ex, HttpServletRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"INTERNAL_SERVER_ERROR", ex.getMessage(), request.getRequestURI());

		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorDetails handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
		String fieldName = ((FieldError) ex.getBindingResult().getAllErrors().get(0)).getField();
		String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "BAD_REQUEST",
				fieldName + " : " + message, request.getRequestURI());

		return errorDetails;
	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(AccessDeniedException.class)
	public ErrorDetails handelAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), HttpStatus.FORBIDDEN.value(), "FORBIDDEN",
				ex.getMessage(), request.getRequestURI());

		return errorDetails;
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(SignatureException.class)
	public ErrorDetails handelAuthenticatetionException(SignatureException ex, HttpServletRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(),
				"UNAUTHORIZED", ex.getMessage(), request.getRequestURI());

		return errorDetails;
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(AlreadyExistsException.class)
	public ErrorDetails handelAlreadyExistsException(AlreadyExistsException ex, HttpServletRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), HttpStatus.CONFLICT.value(), "CONFLICT",
				ex.getMessage(), request.getRequestURI());

		return errorDetails;
	}

	// Xử lý tất cả các exception chưa được khai báo
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handlerException(Exception ex, WebRequest req) {
		// Log err

		return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	}
}
