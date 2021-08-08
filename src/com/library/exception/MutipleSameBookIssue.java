package com.library.exception;

public class MutipleSameBookIssue extends Exception{
	public MutipleSameBookIssue() {
		super("Same book cannot be issued to a person until the previously taken book is returned");
	}
}
