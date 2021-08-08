package com.library.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString


public class BookDetailBean {
	
	private int bookId;
	private int bookCategoryId;
	private String category;
	private String bookName;
	private int stock;
	private int totalBooks;
	private boolean distributable;
	
	
	
	
	
	
	
}
