package com.revature.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.utilities.ConnectionUtil;

public class CustomerService {

	public void login() {
		Scanner login = new Scanner(System.in);
		System.out.print("		     Enter Username: ");
		String username = login.next();
		System.out.print("		     Enter Password: ");
		String password = login.next();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM project0.customer WHERE username = ? AND password = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			int i = 1;
			if(!rs.next()) {
				while(i > 0 ) {
					System.out.println("		     Wrong username or password.\n		     Try again.");
					tryAgain(); i--;
				} 
			} else {
				System.out.println("		     Welcome " + username + ".");
				customerMenu();
			}
		} catch (SQLException e) {
			log.warn("WARNING: SQLException occurred.");
		} catch (NullPointerException e) {
			log.warn("WARNING: NullPointerException occurred.");
		}
	}

	public void tryAgain() {
		Scanner tryAgain = new Scanner(System.in);
		System.out.print("		     Enter Username: ");
		String username = tryAgain.next();
		System.out.print("		     Enter Password: ");
		String password = tryAgain.next();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM customer WHERE username = ? AND password = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			int i = 1;
			if(!rs.next()) {
				while(i > 0 ) {
					System.out.println("		     Wrong username or password.\n		     Try again.");
					tryAgain(); i--;
				} 
			} else {
				System.out.println("		     Welcome " + username + ".");
				customerMenu();
			}
		} catch (SQLException e) {
			log.warn("WARNING: SQLException occurred.");
		} catch (NullPointerException e) {
			log.warn("WARNING: NullPointerException occurred.");
		}
	}
	
	public void customerMenu() {
		Scanner customerMenu = new Scanner(System.in);
		int selection = -1;
		while(selection != 0) {
			System.out.println("		     Bank Menu:\n");
			System.out.println("		     1. View Account");
			System.out.println("		     2. Create Account");
			System.out.println("		     3. Delete Account");
			System.out.println("		     4. Deposit");
			System.out.println("		     5. Withdraw");
			System.out.println("		     6. Transfer");
			System.out.println("		     0. Main Menu");
			System.out.println("\n		     Enter selection: ");
			
			selection = customerMenu.nextInt();
			
			switch(selection) {
			case 1:
				viewAccount();
				break;
			case 2:
				createAccount();
				break;
			case 3:
				deleteAccount();
				break;
			case 4:
				deposit();
				break;
			case 5:
				withdraw();
				break;
			case 6:
				transfer();
				break;
			case 0:
				//MainMenu();
				break;
			default:
			 	System.out.println("		     Invalid Selection.");
			}
		}
	}
	
}