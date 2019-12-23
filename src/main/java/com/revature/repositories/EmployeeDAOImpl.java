package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.revature.models.Employee;
import com.revature.utilities.ConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	public List<Employee> getAllEmployees() {
		
		return null;
	}
	
	public void employeeLogin() {
		Scanner login = new Scanner(System.in);
		System.out.print("		     Enter Employee ID: ");
		String employeeID = login.next();
		System.out.print("		     Enter Password: ");
		String password = login.next();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM project0.customer WHERE employee_id = ? AND password = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, employeeID);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			int i = 1;
			if(!rs.next()) {
				while(i > 0 ) {
					System.out.println("		     Wrong ID or password.\n		     Try again.");
					//tryAgain(); i--;
				} 
			} else {
				System.out.println("		     Welcome " + employeeID + ".");
				employeeMenu();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void employeeMenu() {
		Scanner employeeMenu = new Scanner(System.in);
		int selection = -1;
		while(selection != 0) {
			System.out.println("		     Employee Menu:\n");
			System.out.println("		     1. Approve/Deny Account");
			System.out.println("		     2. View Account");
			System.out.println("		     3. Create Account");
			System.out.println("		     4. Delete Account");
			System.out.println("		     5. Deposit");
			System.out.println("		     6. Withdraw");
			System.out.println("		     7. Transfer");
			System.out.println("		     0. Main Menu");
			System.out.println("\n		     Enter selection: ");
			
			selection = employeeMenu.nextInt();
			
			/*switch(selection) {
			case 1:
				approveAccount();
			
			case 2:
				viewAccount();
				break;
			case 3:
				createAccount();
				break;
			case 4:
				deleteAccount();
				break;
			case 5:
				deposit();
				break;
			case 6:
				withdraw();
				break;
			case 7:
				transfer();
				break;
			case 0:
				//MainMenu();
				break;
			default:
			 	System.out.println("		     Invalid Selection.");
			}
		}*/
	}

	public void approveAccount() {
		
	}
}