package com.htphuoc.bookstore.exception;

@SuppressWarnings("serial")
public class AlreadyExistsException extends Exception {

	public AlreadyExistsException(String message) {
		super(message);
	}
}
