package com.library.bean;




import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * Has 3 Tables
 * 1) BookType(book id,book category, book name)
 * 2) Library (id, employee id, book_id , issue date_and_time, return date_and_time, late_fee)
 * 3) Employee ( id, name) 
 * 
 * 
 * Exceptions
 * 1) Book and Employee not available
 * 2) invalid date of return
 * 
 * Methods
 * 1) issue book
 * 2) return book
 * 3) get charges
 * 
 * 
 * 
 * */

//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@ToString
//
//
//public class UserDetailsBean {
//	private int issueId;
//	private String bookCategory;
//	private String bookName;
//	private LocalDateTime issueDate;
//	private LocalDateTime returnDate;
//	private LocalDateTime scheduleDate;
//	private int charges;
//	private String userName;
//	
//
//
//}
