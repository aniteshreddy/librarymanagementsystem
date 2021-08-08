package com.library.client;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.library.presentation.LibraryPresentation;
import com.library.config.JdbcConfiguration;
import com.library.presentation.BookDistributionPresentationImpl;
import com.library.presentation.EmployeePresentationImpl;

public class UserClient {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		LibraryPresentation bookDistributionPresentation;

		System.out.println("Welcome to STAR library");

		System.out.println("Select choices\n1.Employee Portal\n2.Book Distribution");
//		ApplicationContext springContainer = new AnnotationConfigApplicationContext(JdbcConfiguration.class);
		ApplicationContext springContainer = new ClassPathXmlApplicationContext("library.xml");

		if (scanner.nextInt() == 1) {
			bookDistributionPresentation = (EmployeePresentationImpl) springContainer
					.getBean("employeePresentationImpl");
		} else {
			bookDistributionPresentation = (BookDistributionPresentationImpl) springContainer
					.getBean("bookDistributionPresentationImpl");
		}

		while (true) {
			bookDistributionPresentation.showMenus();
			System.out.println("Select an option");
			int choice = scanner.nextInt();
			bookDistributionPresentation.performMenus(choice);

		}
	}
}
