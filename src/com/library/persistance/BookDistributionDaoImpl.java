package com.library.persistance;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.library.bean.BookDetailBean;
import com.library.bean.BookIssueBean;
import com.library.exception.BookNotDistributable;
import com.library.exception.MutipleSameBookIssue;
import com.library.exception.StockNotAvailableException;
import com.library.helper.BookDetailRowMapper;
import com.library.helper.BookIssueRowMapper;
import com.library.helper.SqlConnectionManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public abstract class BookDistributionDaoImpl implements LibraryDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean issueRecord(BookIssueBean bookDetails) throws StockNotAvailableException, BookNotDistributable,
			MutipleSameBookIssue {

		int rows = jdbcTemplate.update("insert into Issue(user_id,book_id,issue_date,scheduled_date) values(?,?,?,?);",
				bookDetails.getUserId(), bookDetails.getBookId(), Timestamp.valueOf(bookDetails.getIssueDate()),
				Timestamp.valueOf(bookDetails.getScheduleDate()));

		if (rows > 0) {
			return true;
		}

		return false;

	}

	@Override
	public boolean checkIfDistributed(int bookId, int userId) throws MutipleSameBookIssue {

		Map<String, Object> rows=null;
		try {
		rows = jdbcTemplate.queryForMap(
				"select * from Issue where book_id=? and user_id=? and return_date is null;", 
				bookId, userId);
		}catch (Exception e) {
			
		}
		
		if (rows==null) {
			return false;
		}
		throw new MutipleSameBookIssue();

	}

	@Override
	public boolean checkRecord(int bookId) throws BookNotDistributable {
		Map<String, Object> rows = null;

		rows = jdbcTemplate.queryForMap("select * from BookDetails where book_id=?;", bookId);

		if ((boolean) rows.get("distributable")) {
			return true;
		}

		throw new BookNotDistributable();
	}

	@Override
	public boolean returnRecord(BookIssueBean bookIssueBean)
			throws StockNotAvailableException {

		int rows = jdbcTemplate.update(
				"update Issue set return_date=current_timestamp(), charges=? where issue_id=? and return_date is NULL;",
				bookIssueBean.getCharges(), bookIssueBean.getIssueId());

		if (rows > 0) {
		
			stockRecordManipulation(bookIssueBean.getBookId(), 1);
			return true;
		}

		return false;
	}

//	@Override
//	public UserDetailsBean getChargesRecord(BookIssueBean bookissue)
//			throws ClassNotFoundException, SQLException, IOException {
//		Connection connection = SqlConnectionManager.getConnection();
//		PreparedStatement preparedStatement = connection.prepareStatement(
//				"select issue_id,user_name,book_category,book_name,issue_date, return_date,scheduled_date,charges from Issue  join BookDetails  on Issue.book_id=BookDetails.book_id join UserDetails on UserDetails.user_id = Issue.user_id join BookCategory on BookCategory.book_category_id=BookDetails.book_category_id where Issue.user_id=?  and Issue.book_id=? and issue_date=?  ORDER BY issue_id DESC LIMIT 1;");
//
//		preparedStatement.setInt(1, bookissue.getUserId());
//		preparedStatement.setInt(2, bookissue.getBookId());
//		preparedStatement.setTimestamp(3, Timestamp.valueOf(bookissue.getIssueDate()));
//
//		ResultSet resultSet = preparedStatement.executeQuery();
//		UserDetailsBean userDetailsBean = new UserDetailsBean();
//		while (resultSet.next()) {
//			userDetailsBean.setUserName(resultSet.getString("user_name"));
//			userDetailsBean.setBookCategory(resultSet.getString("book_category"));
//			userDetailsBean.setBookName(resultSet.getString("book_name"));
//			userDetailsBean.setIssueDate(resultSet.getTimestamp("issue_date").toLocalDateTime());
//			if (resultSet.getTimestamp("return_date") != null)
//				userDetailsBean.setReturnDate(resultSet.getTimestamp("return_date").toLocalDateTime());
//			userDetailsBean.setScheduleDate(resultSet.getTimestamp("scheduled_date").toLocalDateTime());
//			userDetailsBean.setCharges(resultSet.getInt("charges"));
//
//		}
//
//		return userDetailsBean;
//	}

}
