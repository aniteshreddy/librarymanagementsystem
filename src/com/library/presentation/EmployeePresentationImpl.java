package com.library.presentation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.library.bean.BookIssueBean;
import com.library.exception.StockNotAvailableException;
import com.library.service.LibraryService;

@Component
public class EmployeePresentationImpl implements LibraryPresentation {

	@Autowired
	@Qualifier("employeeServiceImp")
	private LibraryService userService;

	@Override
	public void showMenus() {
		System.out.println("1. Get all user details");
		System.out.println("2. List all books");
		System.out.println("3. Increase of decrease stock");
		System.out.println("0. Exit");
	}

	@Override
	public void performMenus(int choice) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		switch (choice) {

		case 1:
			System.out.println("All user Records");

			for (BookIssueBean bookIssueBean : userService.getAllUserDetails(null))
				System.out.println(bookIssueBean);

			break;
		case 2:
			System.out.println("Listing all books");

			userService.getAllBooks().stream().forEach(System.out::println);

			break;

		case 3:
			System.out.println("Changes in stock");

			userService.getAllBooks().stream().forEach(System.out::println);

			System.out.println("Enter book ID");
			int bookId = scanner.nextInt();
			System.out.println("Enter stock to be increased or decreased");
			int value = scanner.nextInt();

			try {
				if (userService.stockManipulation(bookId, value)) {
					System.out.println("Record changed");
				} else
					System.out.println("Record not changed");
			} catch (StockNotAvailableException e) {
				System.out.println(e.getMessage());
			}

			break;

		case 0:
			System.out.println("Bye! Bye!");

			System.exit(0);
			break;

		}
	}

}
