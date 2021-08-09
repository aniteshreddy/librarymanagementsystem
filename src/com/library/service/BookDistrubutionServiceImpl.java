package com.library.service;


import java.time.temporal.ChronoUnit;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import com.library.bean.BookIssueBean;
import com.library.exception.BookNotDistributable;
import com.library.exception.InvalidReturnDateException;
import com.library.exception.MutipleSameBookIssue;
import com.library.exception.StockNotAvailableException;
import com.library.persistance.LibraryDao;


@Service
public abstract class BookDistrubutionServiceImpl implements LibraryService {

	@Autowired
	@Qualifier("employeeDaoImpl")
	protected LibraryDao libraryDao;

	@Override
	public boolean issueBook(BookIssueBean bookIssueBean)
			throws StockNotAvailableException, BookNotDistributable, MutipleSameBookIssue {
		if (!libraryDao.checkRecord(bookIssueBean.getBookId())) {
			return false;
		}
		libraryDao.stockRecordManipulation(bookIssueBean.getBookId(), -1);
		if (libraryDao.checkIfDistributed(bookIssueBean.getBookId(), bookIssueBean.getUserId())) {
			return false;
		}
		bookIssueBean.setIssueDate(LocalDateTime.now());
		bookIssueBean.setScheduleDate(LocalDateTime.now().plusDays(7));
		return libraryDao.issueRecord(bookIssueBean);

	}

	@Override
	public boolean returnBook(BookIssueBean bookIssueBean)
			throws InvalidReturnDateException, StockNotAvailableException {

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



}
