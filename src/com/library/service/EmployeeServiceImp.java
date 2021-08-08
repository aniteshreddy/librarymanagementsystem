
package com.library.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.library.bean.BookDetailBean;
import com.library.bean.BookIssueBean;
import com.library.exception.StockNotAvailableException;



@Service
public class EmployeeServiceImp extends BookDistrubutionServiceImpl implements LibraryService {
	@Override
	public Collection<BookDetailBean> getAllBooks() throws ClassNotFoundException, SQLException, IOException {

		return libraryDao.getAllBooksRecord();
	}

	@Override
	public boolean stockManipulation(int bookId, int values)
			throws ClassNotFoundException, SQLException, IOException, StockNotAvailableException {

		return libraryDao.stockRecordManipulation(bookId, values);
	}

	@Override
	public Collection<BookIssueBean> getAllUserDetails(Integer id)
			throws ClassNotFoundException, SQLException, IOException {

		return libraryDao.getAllUserIssuesRecord(id);
	}

	@Override
	public boolean addBook(BookDetailBean bookDetailBean)
			throws ClassNotFoundException, SQLException, IOException {
		

		return libraryDao.addBookRecord(bookDetailBean);
	}
}