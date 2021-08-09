package com.library.persistance;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.library.bean.BookDetailBean;
import com.library.bean.BookIssueBean;
import com.library.exception.StockNotAvailableException;
import com.library.helper.BookDetailRowMapper;
import com.library.helper.BookIssueRowMapper;

@Repository
public class EmployeeDaoImpl extends BookDistributionDaoImpl implements LibraryDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean stockRecordManipulation(int bookId, int value) throws StockNotAvailableException {

		if (checkStock(bookId, value)) {
			int rows = 0;

			rows = jdbcTemplate.update("update BookDetails set stock=stock+(?) where book_id=?;", value, bookId);

			if (rows > 0) {
				return true;
			}
		}

		throw new StockNotAvailableException();
	}

	private boolean checkStock(int bookId, int value) {
		Map<String, Object> rows = jdbcTemplate.queryForMap("select * from BookDetails where book_id=?", bookId);
		if (((int) rows.get("stock") + value) >= 0) {
			return true;
		} else
			return false;
	}

	@Override
	public boolean addBookRecord(BookDetailBean bookBean) {

		int rows = jdbcTemplate.update(
				"insert into BookDetails(book_category_id,book_name,distributable,total_books,stock) values(?,?,?,?,?,?)",
				bookBean.getBookCategoryId(), bookBean.getBookName(), bookBean.isDistributable(),
				bookBean.getTotalBooks(), bookBean.getStock());

		if (rows > 0)
			return true;

		return false;
	}

	@Override
	public Collection<BookDetailBean> getAllBooksRecord() {

		return jdbcTemplate.query(
				"select book_id,BookDetails.book_category_id,book_name,book_category,stock,total_books,distributable from BookDetails join BookCategory on BookDetails.book_category_id=BookCategory.book_category_id;",
				new BookDetailRowMapper());

	}

	@Override
	public Collection<BookIssueBean> getAllUserIssuesRecord(Integer id) {
		Collection<BookIssueBean> userDetailsCollection;
		if (id == null) {

			userDetailsCollection = jdbcTemplate.query(
					"select Issue.user_id,issue_id,BookDetails.book_id,user_name,book_category,book_name,issue_date, return_date,scheduled_date,charges from Issue join BookDetails  on Issue.book_id=BookDetails.book_id join UserDetails  on UserDetails.user_id = Issue.user_id join BookCategory  on BookDetails.book_category_id=BookCategory.book_category_id;",
					new BookIssueRowMapper());
		} else {
			userDetailsCollection = jdbcTemplate.query(
					"select Issue.user_id,issue_id,BookDetails.book_id,user_name,book_category,book_name,issue_date, return_date,scheduled_date,charges from Issue join BookDetails  on Issue.book_id=BookDetails.book_id join UserDetails  on UserDetails.user_id = Issue.user_id join BookCategory  on BookDetails.book_category_id=BookCategory.book_category_id where UserDetails.user_id=?;",
					new BookIssueRowMapper(), id);

		}

		return userDetailsCollection;
	}

}
