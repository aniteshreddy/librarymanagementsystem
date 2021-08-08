package com.library.persistance;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import com.library.bean.BookDetailBean;
import com.library.bean.BookIssueBean;
import com.library.exception.BookNotDistributable;
import com.library.exception.MutipleSameBookIssue;
import com.library.exception.StockNotAvailableException;

public interface LibraryDao {
//	UserDetailsBean getChargesRecord(BookIssueBean bookissue) throws ClassNotFoundException, SQLException, IOException;

	boolean issueRecord(BookIssueBean bookDetails) throws StockNotAvailableException, BookNotDistributable, MutipleSameBookIssue, ClassNotFoundException, SQLException, IOException;

	boolean returnRecord(BookIssueBean bookDetails) throws StockNotAvailableException, ClassNotFoundException, SQLException, IOException;

	boolean addBookRecord(BookDetailBean bookBean) ;

	Collection<BookIssueBean> getAllUserIssuesRecord(Integer userId) ;

	
	Collection<BookDetailBean> getAllBooksRecord() ;

	boolean stockRecordManipulation(int bookId,int value) throws StockNotAvailableException;

//	boolean checkRecord(int bookId) throws SQLException, ClassNotFoundException, IOException, BookNotDistributable;

//	boolean checkIfDistributed(int bookId, int userId) throws ClassNotFoundException, SQLException, IOException, MutipleSameBookIssue;
	
	
}