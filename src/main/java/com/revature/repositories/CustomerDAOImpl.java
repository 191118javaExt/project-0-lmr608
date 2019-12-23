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

import com.revature.Driver;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.services.MainMenu;
import com.revature.utilities.ConnectionUtil;

public class CustomerDAOImpl implements CustomerDAO {
	
	private static final Logger log = Logger.getLogger(Driver.class);
	
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
			log.warn("WARNING: SQLException occurred.");
		} catch (NullPointerException e) {
			log.warn("WARNING: NullPointerException occurred.");
		}
		return customerList;
	}

	public void createCustomer( ) {
		Scanner createCustomer = new Scanner(System.in);
		System.out.print("		     Create Username: ");
		String username = createCustomer.next();
		System.out.print("		     Create Password: ");
		String password = createCustomer.next();
		System.out.print("		     First Name: ");
		String firstName = createCustomer.next();
		System.out.print("		     Last Name: ");
		String lastName = createCustomer.next();
		System.out.print("		     Email: ");
		String email = createCustomer.next();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO customer (username, password, first_name, last_name, email) " + "VALUES (?, ?, ?, ?, ?);";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setString(3, firstName);
			pstmt.setString(4, lastName);
			pstmt.setString(5, email);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			log.warn("WARNING: SQLException occurred.");
		} catch (NullPointerException e) {
			log.warn("WARNING: NullPointerException occurred.");
		}
		System.out.println("		     Customer account has been created.");
		login();
	}
	
	public void viewCustomer() {
		Scanner viewCustomer = new Scanner(System.in);
		System.out.println("		     Enter Customer ID to view account: ");
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
			log.warn("WARNING: SQLException occurred.");
		} catch (NullPointerException e) {
			log.warn("WARNING: NullPointerException occurred.");
		}
	}

	public void updateCustomer() {
		
	}
	
	public void deleteCustomer() {
		Scanner deleteCustomer = new Scanner(System.in);
		System.out.print("		     Enter Customer ID you want to delete: ");
		String customer = deleteCustomer.next();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM TABLE customer WHERE customer_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, customer);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			log.warn("WARNING: SQLException occurred.");
		} catch (NullPointerException e) {
			log.warn("WARNING: NullPointerException occurred.");
		}
		System.out.println("		     Customer has been deleted.");
		System.out.println();
	}
	
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
	
	public void viewAccount() {
		
		Scanner viewAccount = new Scanner(System.in);
		System.out.println("		     Enter Account ID to view account: ");
		int id = 0;
		id = viewAccount.nextInt();

		try (Connection conn = ConnectionUtil.getConnection()) {
			Account account  = null;
			String sql = "SELECT * FROM project0.account WHERE account_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int account_id = rs.getInt("account_id");
				account = new Account(id, balance, approved);
				System.out.println(account);				
			}
		} catch (SQLException e) {
			log.warn("WARNING: SQLException occurred.");
		} catch (NullPointerException e) {
			log.warn("WARNING: NullPointerException occurred.");
		}
	}
	
	public void createAccount() {
		Scanner createAccount = new Scanner(System.in);
		System.out.print("		     Enter User ID: ");
		int id = 0;
		id = createAccount.nextInt();
		System.out.println("		     Enter Starting Balance: ");
		double balance;
		balance = createAccount.nextDouble();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO project0.account (user_id, balance) VALUES (?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setDouble(2, balance);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			log.warn("WARNING: SQLException occurred.");
		} catch (NullPointerException e) {
			log.warn("WARNING: NullPointerException occurred.");
		}
		System.out.println("		     Account created!");
		log.info("Account created.");
	}
	
	public void deleteAccount() {
		Scanner deleteAccount = new Scanner(System.in);
		System.out.print("		     Enter Username to Delete: ");
		String username = deleteAccount.next();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM project0.account WHERE username = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			log.warn("WARNING: SQLException occurred.");
		} catch (NullPointerException e) {
			log.warn("WARNING: NullPointerException occurred.");
		}
		System.out.println("		     User has been deleted.");
		log.info("Account deleted.")
	}
	
	public void deposit() {
		System.out.println("		     Enter Account ID: ");
		Scanner deposit = new Scanner(System.in);
		int id = 0;
		id = deposit.nextInt();
		
		System.out.println("		     Deposit Amount: ");
		double amount;
		amount = deposit.nextDouble();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
		String sql = "UPDATE project0.account SET balance += ?, WHERE account_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, amount);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			log.warn("WARNING: SQLException occurred.");
		} catch (NullPointerException e) {
			log.warn("WARNING: NullPointerException occurred.");
		}
		System.out.println("		     " + id + " has deposited " + amount + ".");
		log.info("Deposit successful.");
	}
	
	public void withdraw() {
		System.out.println("		     Enter Account ID: ");
		Scanner withdraw = new Scanner(System.in);
		int id = 0;
		id = withdraw.nextInt();
		
		System.out.println("		     Withdraw Amount: ");
		double amount;
		amount = withdraw.nextDouble();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
		String sql = "UPDATE project0.account SET balance -= ?, WHERE account_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, amount);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			log.warn("WARNING: SQLException occurred.");
		} catch (NullPointerException e) {
			log.warn("WARNING: NullPointerException occurred.");
		}
		System.out.println("		     " + id + " has withdrawn " + amount + ".");
		log.info("Withdraw successful.");
	}
	
	public void transfer() {
		System.out.println("		     Enter Account ID to Transfer from: ");
		Scanner transfer = new Scanner(System.in);
		int id = 0;
		id = transfer.nextInt();
		
		System.out.println("		     Enter Account ID to Transfer to: ");
		id = transfer.nextInt();
		
		System.out.println("		     Transfer Amount: ");
		double amount;
		amount = transfer.nextDouble();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
		String sql = "UPDATE project0.account SET balance -= ?, WHERE account_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, amount);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			
			String sql2 = "UPDATE project0.account SET balance += ?, WHERE account_id = ?";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setDouble(1, amount);
			pstmt2.setInt(2, id);
			pstmt2.executeUpdate();
		} catch (SQLException e) {
			log.warn("WARNING: SQLException occurred.");
		} catch (NullPointerException e) {
			log.warn("WARNING: NullPointerException occurred.");
		}
		System.out.println("		     Transfer complete.");
		log.info("Transfer successful."));
	}
}