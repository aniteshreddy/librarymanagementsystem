package com.library.presentation;

import java.io.IOException;
import java.sql.SQLException;

import java.util.Collection;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.library.bean.BookIssueBean;
import com.library.exception.BookNotDistributable;
import com.library.exception.InvalidReturnDateException;
import com.library.exception.MutipleSameBookIssue;
import com.library.exception.StockNotAvailableException;
import com.library.service.LibraryService;

@Component
public class BookDistributionPresentationImpl implements LibraryPresentation {

	@Autowired
	@Qualifier("employeeServiceImp")
	private LibraryService userService;

	@Override
	public void showMenus() {
		System.out.println("1. Issue Book");
		System.out.println("2. Return Book");
		System.out.println("3. Get Details");
		System.out.println("0. Exit");
	}

	@Override
	public void performMenus(int choice) {
		Scanner scanner = new Scanner(System.in);

		switch (choice) {
		case 1:
			System.out.println(" Issuing a book");
			System.out.println("Enter user ID");
			int userId = scanner.nextInt();

			userService.getAllBooks().stream().forEach(System.out::println);

			System.out.println("Enter book ID");
			int bookId = scanner.nextInt();

			try {
				BookIssueBean bookIssueBean = new BookIssueBean();
				bookIssueBean.setBookId(bookId);
				bookIssueBean.setUserId(userId);

				if (userService.issueBook(bookIssueBean))
					System.out.println("Book issue successful");
				else
					System.out.println("Book issue failed");
			} catch (StockNotAvailableException | BookNotDistributable | MutipleSameBookIssue e) {

				System.out.println(e.getMessage());
			}

			break;

		case 2:

			System.out.println("Returning a book");
			System.out.println("Enter user ID");
			userId = scanner.nextInt();
			Collection<BookIssueBean> bookIssueBeans;

			bookIssueBeans = userService.getAllUserDetails(userId);
			if (bookIssueBeans.isEmpty()) {
				System.out.println("User id does not exist");
				break;
			}

			bookIssueBeans.stream().filter(e -> e.getReturnDate() == null).filter(e -> e.getUserId() == userId)
					.forEach(System.out::println);

			System.out.println("Select issue id of book that you want to return");
			int issueId = scanner.nextInt();
			BookIssueBean bookIssueBean = bookIssueBeans.stream().filter(bookissue -> bookissue.getIssueId() == issueId)
					.findAny().orElse(null);

			try {
				if (bookIssueBean != null && userService.returnBook(bookIssueBean)) {
					System.out.println("Book Return succesful");
					break;
				}
				System.out.println("Invalid issue id or book already returned");
			} catch (InvalidReturnDateException | StockNotAvailableException e) {
				System.out.println(e.getMessage());
			}
			break;

		case 3:
			System.out.println("Get details of user");
			System.out.println("Enter user ID");
			userId = scanner.nextInt();

			for (BookIssueBean bookIssueBean2 : userService.getAllUserDetails(userId))
				System.out.println(bookIssueBean2);

			break;
		case 0:
			System.out.println("Bye! Bye!");
			System.exit(0);
			break;

		}
	}
}
