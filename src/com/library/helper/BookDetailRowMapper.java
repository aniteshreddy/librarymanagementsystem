package com.library.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.library.bean.BookDetailBean;

public class BookDetailRowMapper implements RowMapper<BookDetailBean> {

	@Override
	public BookDetailBean mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		
		BookDetailBean bookDetailBean = new BookDetailBean();
		bookDetailBean.setBookId(resultSet.getInt("book_id"));
//		bookDetailBean.setBookCategoryId(resultSet.getInt("book_category_id"));
		bookDetailBean.setBookName(resultSet.getString("book_name"));
		bookDetailBean.setCategory(resultSet.getString("book_category"));
		bookDetailBean.setDistributable(resultSet.getBoolean("distributable"));
		bookDetailBean.setTotalBooks(resultSet.getInt("total_books"));
		bookDetailBean.setStock(resultSet.getInt("stock"));
		
		
		
		return bookDetailBean;
		
		
		
	}

}
