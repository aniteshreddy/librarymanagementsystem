package com.library.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.library.bean.BookIssueBean;

public class BookIssueRowMapper implements RowMapper<BookIssueBean> {

	@Override
	public BookIssueBean mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		BookIssueBean bookIssueBean = new BookIssueBean();
		bookIssueBean.setUserId(resultSet.getInt("user_id"));
		bookIssueBean.setIssueId(resultSet.getInt("issue_id"));
		bookIssueBean.setBookId(resultSet.getInt("book_id"));
		bookIssueBean.setUserName(resultSet.getString("user_name"));
		bookIssueBean.setBookCategory(resultSet.getString("book_category"));
		bookIssueBean.setBookName(resultSet.getString("book_name"));	
		bookIssueBean.setIssueDate(resultSet.getTimestamp("issue_date").toLocalDateTime());
		if(resultSet.getTimestamp("return_date")!=null)
			bookIssueBean.setReturnDate(resultSet.getTimestamp("return_date").toLocalDateTime());
		bookIssueBean.setScheduleDate(resultSet.getTimestamp("scheduled_date").toLocalDateTime());
		bookIssueBean.setCharges(resultSet.getInt("charges"));
		return bookIssueBean;
	}

}
