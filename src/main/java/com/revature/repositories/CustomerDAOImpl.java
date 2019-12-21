package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.models.Customer;
import com.revature.utilities.ConnectionUtil;

public class CustomerDAOImpl implements CustomerDAO {
	
	public List<Customer> getAllCustomers() {
		List<Customer> customerList = new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM customer";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("customer_id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				customerList.add(new Customer(id, username, password, firstName, lastName, email));
			}
		} catch (SQLException e) {
		} catch (NullPointerException e) {
		}
		return customerList;
	}

	public void createCustomer( ) {
		Scanner createCustomer = new Scanner(System.in);
		System.out.print("Create Username: ");
		String username = createCustomer.next();
		System.out.print("Create Password: ");
		String password = createCustomer.next();
		System.out.print("First Name: ");
		String firstName = createCustomer.next();
		System.out.print("Last Name: ");
		String lastName = createCustomer.next();
		System.out.print("Email: ");
		String email = createCustomer.next();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO customer (username, password, first_name, last_name, email) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setString(3, firstName);
			pstmt.setString(4, lastName);
			pstmt.setString(5, email);
			pstmt.executeUpdate();
		} catch (SQLException e) {
		}
		System.out.println("Customer account has been created.");
		login();
	}
	
	public void viewCustomer() {
		Scanner viewCustomer = new Scanner(System.in);
		System.out.println("Enter Customer ID to view account: ");
		int id = 0;
		id = viewCustomer.nextInt();

		try (Connection conn = ConnectionUtil.getConnection()) {
			Customer customer  = null;
			String sql = "SELECT * FROM customer WHERE customer_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int customer_id = rs.getInt("customer_id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String email = rs.getString("email");
				customer = new Customer(customer_id, username, password, first_name, last_name, email);
				System.out.println(customer);				
			}
		} catch (SQLException e) {
		}
	}

	public void updateCustomer() {
		
	}
	
	public void deleteCustomer() {
		Scanner deleteCustomer = new Scanner(System.in);
		System.out.print("Enter Customer ID you want to delete: ");
		String customer = deleteCustomer.next();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM TABLE customer WHERE customer_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, customer);
			pstmt.executeUpdate();
		} catch (SQLException e) {
		}
		System.out.println("Customer has been deleted.");
		System.out.println();
	}
	
	public void login() {
		Scanner login = new Scanner(System.in);
		System.out.print("Enter your username: ");
		String username = login.next();
		System.out.print("Enter your password: ");
		String password = login.next();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM customer WHERE username = ? AND password = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			int i = 1;
			if(!rs.next()) {
				while(i > 0 ) {
					System.out.println("Wrong username or password. Try again.");
					tryAgain(); i--;
				} 
			} else {
				System.out.println("Welcome " + username + ".");
				customerMenu();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void tryAgain() {
		Scanner tryAgain = new Scanner(System.in);
		System.out.print("Enter username: ");
		String username = tryAgain.next();
		System.out.print("Enter password: ");
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
					System.out.println("Wrong username or password. Try again.");
					tryAgain(); i--;
				} 
			} else {
				System.out.println("Welcome " + username + ".");
				customerMenu();
			}
		} catch (SQLException e) {		
		}
	}
	
	public void customerMenu() {
		Scanner customerMenu = new Scanner(System.in);
		int selection = -1;
		while(selection != 0) {
			System.out.println("Bank Menu:\n");
			System.out.println("1. View Account");
			System.out.println("2. Create Account");
			System.out.println("3. Delete Account");
			System.out.println("4. Deposit");
			System.out.println("5. Withdraw");
			System.out.println("6. Transfer");
			System.out.println("0. Main Menu");
			System.out.println("\nEnter selection: ");
			
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
				
				break;
			}
		}
	}
}