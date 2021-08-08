package com.library.exception;

public class BookNotDistributable extends Exception{
	
	public BookNotDistributable() {
		super("Book is available for in library use only");
	}

}
