package com.library.exception;

public class InvalidReturnDateException extends Exception {
	public InvalidReturnDateException() {
		super("Return date cannot be before the issue date");
	}
}
