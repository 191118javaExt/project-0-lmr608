package com.revature.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.utilities.ConnectionUtil;

public class AccountService {

	/*public void viewAccount() {
		
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
	}*/
}