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

import com.revature.models.Customer;
import com.revature.utilities.ConnectionUtil;

public class CustomerDAOImpl implements CustomerDAO {
	public List<Customer> getAllCustomers() {
		
		List<Customer> c1 = new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM TABLE customer";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("customer_id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				c1.add(new Customer(id, username, password, firstName, lastName, phone, email));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return c1;
	}

	public void createCustomer( ) {
		Scanner s = new Scanner(System.in);
		System.out.print("Create username: ");
		String username = s.next();
		System.out.print("Create password: ");
		String password = s.next();
		System.out.print("First name: ");
		String firstName = s.next();
		System.out.print("Last name: ");
		String lastName = s.next();
		System.out.print("Phone: ");
		String phone = s.next();
		System.out.print("Email: ");
		String email = s.next();

		try (Connection con = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO TABLE customer (username, password, first_name, last_name, phone, email) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setString(3, firstName);
			pstmt.setString(4, lastName);
			pstmt.setString(5, phone);
			pstmt.setString(6, email);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Customer account has been created.");
		System.out.println();
		s.close();
	}

	public void deleteCustomer() {
		Scanner s = new Scanner(System.in);
		System.out.print("Enter Customer ID you want to delete: ");
		String c2 = s.next();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM TABLE customer WHERE customer_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c2);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Customer has been deleted.");
		System.out.println();
	}

	public void viewCustomer() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Customer ID to view account: ");
		int id = 0;
		id = s.nextInt();

		try (Connection conn = ConnectionUtil.getConnection()) {
			Customer c3  = null;
			String sql = "SELECT * FROM TABLE customer WHERE customer_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int customer_id = rs.getInt("customer_id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				c3 = new Customer(customer_id, username, password, first_name, last_name, phone, email);
				System.out.println(c3);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		s.close();
	}
	
	public void checkCustomer() {
		Scanner s = new Scanner(System.in);
		System.out.print("Enter username: ");
		String username;
		username = s.next();

		System.out.print("Enter password: ");
		String password = s.next();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM TABLE customer WHERE username = ? AND password = ?";
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
				System.out.println();
				giveUserOptions();
			}
		} catch (SQLException e) {
		} catch(NullPointerException e) {
		}
		s.close();
	}

	public void tryAgain() {
		Scanner s = new Scanner(System.in);
		System.out.print("Enter username: ");
		String username = s.next();

		System.out.print("Enter password: ");
		String password = s.next();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM TABLE customer WHERE username = ? AND password = ?";
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
				System.out.println();
				giveUserOptions();
			}
		} catch (SQLException e) {
		} catch(NullPointerException e) {
		}
		s.close();
	}
	
	public void giveUserOptions() {
		Scanner s = new Scanner(System.in);
		int selection = 0;
		while(selection != 4) {
			System.out.println("Bank menu: " + "\n" + "1. View account" + "\n" + "2. Create account" + "\n" + 
			"3. Delete account " + "\n" + "4. Deposit/Withdraw" + "\n" + "5. Main menu");
			System.out.print("Enter selection: ");
			selection = s.nextInt();
			switch(selection) {
			case 1:
				viewAccount();
				System.out.println();
				break;
			case 2:
				createAccount();
				System.out.println();
				break;
			case 3:
				deleteAccount();
				System.out.println();
				break;
			case 4:
				deposit();
				System.out.println("Deposit/Withdraw");
				System.out.println();
				break;
			case 5: 
				System.out.println("Main menu");
				System.out.println();
				break;  
			}
		}
		s.close();
	}
}		

