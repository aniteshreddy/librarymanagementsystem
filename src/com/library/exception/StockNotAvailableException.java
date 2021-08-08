package com.library.exception;

public class StockNotAvailableException extends Exception{

	public StockNotAvailableException() {
		super("Book is not available at the moment");
	}
}
