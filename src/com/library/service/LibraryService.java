package com.library.service;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;

import com.library.bean.BookDetailBean;
import com.library.bean.BookIssueBean;
import com.library.exception.BookNotDistributable;
import com.library.exception.InvalidReturnDateException;
import com.library.exception.MutipleSameBookIssue;
import com.library.exception.StockNotAvailableException;

public interface LibraryService {
//	UserDetailsBean getCharges(int userId,int bookId,LocalDateTime issueDate) throws ClassNotFoundException, SQLException, IOException;
	boolean issueBook(BookIssueBean bookIssueBean) throws  StockNotAvailableException, BookNotDistributable, MutipleSameBookIssue;
	boolean returnBook(BookIssueBean bookIssueBean) throws  InvalidReturnDateException, StockNotAvailableException;
	Collection<BookDetailBean> getAllBooks() ;
	boolean stockManipulation(int bookId,int value) throws StockNotAvailableException;
	Collection<BookIssueBean> getAllUserDetails(Integer id) ;
	boolean addBook(BookDetailBean bookDetailBean) ;
}
