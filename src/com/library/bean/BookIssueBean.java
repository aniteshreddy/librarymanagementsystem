package com.library.bean;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

public class BookIssueBean {
	private int issueId;
	private int bookId;
	private String userName;
	private String bookCategory;
	private String bookName;
	private LocalDateTime returnDate;
	private LocalDateTime issueDate;
	private LocalDateTime scheduleDate;
	private int userId;
	private int charges;
}
