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

	public void viewAccount() {
		
		Scanner viewAccount = new Scanner(System.in);
		System.out.println("Enter Account ID to view account: ");
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
		}
	}

	/*
	 * createAccount();
	 * 
	 * deleteAccount();
	 * 
	 * deposit();
	 * 
	 * withdraw();
	 * 
	 * transfer();
	 */
}