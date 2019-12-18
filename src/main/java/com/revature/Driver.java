package com.revature;

import java.util.Scanner;

import com.revature.repositories.CustomerDAO;
import com.revature.repositories.CustomerDAOImpl;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.EmployeeDAOImpl;

public class Driver {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int selection = 0;
		while(selection != 4) {
			System.out.println("Welcome!" + "\n" + "Main menu: " + "\n" + "1. Login" 
			+ "\n" + "2. Register" + "\n" + "3. Employees" + "\n" + "4. Quit");
			System.out.print("Enter selection: ");
			
			selection = s.nextInt();
			
			switch(selection) {
			case 1:
				CustomerDAO c1 = new CustomerDAOImpl();
				c1.checkCustomer();
				break;
			case 2:
				CustomerDAO c2 = new CustomerDAOImpl();
				c2.createCustomer();
				break;
			case 3:
				EmployeeDAO c3 = new EmployeeDAOImpl();
				c3.checkEmployee();
				break;
			case 4:
				System.out.println("Goodbye!");
				break;
			}
		}
		s.close();
	}
}