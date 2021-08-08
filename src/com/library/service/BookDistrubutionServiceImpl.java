package com.library.service;

import java.io.IOException;

import java.time.temporal.ChronoUnit;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import com.library.bean.BookDetailBean;
import com.library.bean.BookIssueBean;
import com.library.exception.BookNotDistributable;
import com.library.exception.InvalidReturnDateException;
import com.library.exception.MutipleSameBookIssue;
import com.library.exception.StockNotAvailableException;
import com.library.persistance.LibraryDao;
import com.library.persistance.BookDistributionDaoImpl;
import com.library.persistance.EmployeeDaoImpl;

@Service
public abstract class BookDistrubutionServiceImpl implements LibraryService {

	@Autowired
	@Qualifier("employeeDaoImpl")
	protected LibraryDao libraryDao ;


	@Override
	public boolean issueBook(BookIssueBean bookIssueBean) throws ClassNotFoundException, SQLException, IOException,
			StockNotAvailableException, BookNotDistributable, MutipleSameBookIssue {

		bookIssueBean.setIssueDate(LocalDateTime.now());
		bookIssueBean.setScheduleDate(LocalDateTime.now().plusDays(7));
		return libraryDao.issueRecord(bookIssueBean);

	}

	@Override
	public boolean returnBook(BookIssueBean bookIssueBean) throws ClassNotFoundException, SQLException, IOException,
			InvalidReturnDateException, StockNotAvailableException {

		LocalDateTime scheduledDate = bookIssueBean.getScheduleDate();

		String bookCategory = bookIssueBean.getBookCategory();

		long days = Math.abs(ChronoUnit.DAYS.between(scheduledDate, LocalDateTime.now()));

		if (LocalDateTime.now().isAfter(scheduledDate)) {
			if (bookCategory.equals("Data Analytics")) {

				bookIssueBean.setCharges((int) days * 5);

			} else if (bookCategory.equals("Technology")) {
				bookIssueBean.setCharges((int) days * 6);
			} else if (bookCategory.equals("Management")) {
				bookIssueBean.setCharges((int) days * 5);
			}
		}

		return libraryDao.returnRecord(bookIssueBean);

	}
	
//	InvalidReturnDateException invalidReturnDateException = 
//
//	@Override
//	public UserDetailsBean getCharges(int userId, int bookId, LocalDateTime issueDate)
//			throws ClassNotFoundException, SQLException, IOException {
//
//		BookIssueBean bookIssueBean = new BookIssueBean();
//		bookIssueBean.setBookId(bookId);
//		bookIssueBean.setUserId(userId);
//		bookIssueBean.setIssueDate(issueDate);
//
//		return libraryDao.getChargesRecord(bookIssueBean);
//	}

}
