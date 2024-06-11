package com.ig;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class InstagramSignUp {

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);// HTML
		// input type=text
		// input type=password 
		// input type=submit 

		displaySignInOptions();
		int userChoice = sc.nextInt();

		switch (userChoice) {
		case 1: {
			signUpApplication(sc);
			break;
		}
		case 2: {
			isValidUser(sc);

			break;

		}

		default: {
			System.out.println("Not valid Option !! , Try again !!");
		}
		}

	}

	private static void displaySignInOptions() {
		System.out.println("Welcome to Instagram !!");
		System.out.println("Press 1 for SignUp");
		System.out.println("Press 2 for signIn");
		// forgot password
		// 
	}

	private static void isValidUser(Scanner sc) throws SQLException {
		System.out.println("please enter user name");
		String username = sc.next();
		System.out.println("Please enter password");
		String password = sc.next();
		boolean isValidUser = DButil.validateUserLogin(username, password);

		if (isValidUser) {
			System.out.println("Welcome to Instagram "+username);
		} else {
			System.out.println("Invalid credentials , please try again !!");
			//every wrong attemp -- update failed count upto 3
			// >3 update acc status to Locked
			// LocalDateTime (lock)  (login)
			LocalDateTime.now();
			//locked Localdate 
			// subtract 24 
			//
		}
	}

	private static void signUpApplication(Scanner sc) throws SQLException {
		System.out.println("Welcome to Instagram , Kill time effortless!!");

		System.out.println("please enter first name");
		String firstName = sc.next();
		// not empty 

		System.out.println("please enter last name");
		String lastName = sc.next();

		System.out.println("please enter mail id");
		String mail = sc.next();
		// @ . @. side by side // type="email"
		

		System.out.println("please enter user_id of your choice");
		String userId = sc.next();

		System.out.println("please enter password");
		String password = sc.next();

		System.out.println("please re enter confirm password");
		String confirmpassword = sc.next();

		if (password.equals(confirmpassword)) {
			UserDetails userDetailsobject = new UserDetails(firstName, lastName, mail, userId, confirmpassword);
			String response = DButil.signUpInstagramUser(userDetailsobject);
			System.out.println(response);
		} else {
			System.out.println("Password and confirm password should be same , Pora ");
		}
	}

}
