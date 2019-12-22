package com.revature;

import java.util.Scanner;

import com.revature.repositories.CustomerDAO;
import com.revature.repositories.CustomerDAOImpl;

public class Driver {
    
	public static void main(String[] args) {
    
		Scanner main = new Scanner(System.in);
		int selection;
		boolean quit = false;
		
		do {
			System.out.println("	       Welcome to Eagle Bank!\n\n"
					+ "         _._._                       _._._\r\n" + 
					"        _|   |_                     _|   |_\r\n" + 
					"        | ... |_._._._._._._._._._._| ... |\r\n" + 
					"        | ||| |  o EAGLE    BANK o  | ||| |\r\n" + 
					"        | \"\"\" |  \"\"\"    \"\"\"    \"\"\"  | \"\"\" |\r\n" + 
					"   ())  |[-|-]| [-|-]  [-|-]  [-|-] |[-|-]|  ())\r\n" + 
					"  (())) |     |---------------------|     | (()))\r\n" + 
					" (())())| \"\"\" |  \"\"\"    \"\"\"    \"\"\"  | \"\"\" |(())())\r\n" + 
					" (()))()|[-|-]|  :::   .-\"-.   :::  |[-|-]|(()))()\r\n" + 
					" ()))(()|     | |~|~|  |_|_|  |~|~| |     |()))(()\r\n" + 
					"    ||  |_____|_|_|_|__|_|_|__|_|_|_|_____|  ||\r\n" + 
					" ~ ~^^ @@@@@@@@@@@@@@/=======\\@@@@@@@@@@@@@@ ^^~ ~\r\n" + 
					"      ^~^~                                ~^~^");
			System.out.println("		     Main Menu:\n");
			System.out.println("		     1. Register");
			System.out.println("		     2. Login");
			System.out.println("		     3. Employee Login");
			System.out.println("		     0. Quit");
			System.out.println("\n		     Enter Selection: ");
			
			try {
			
			selection = main.nextInt();
			
			switch (selection) {
			case 1:
				// Register method
				CustomerDAO newCustomer = new CustomerDAOImpl();
				newCustomer.createCustomer();
				break;
			case 2:
				// Login method
				CustomerDAO existingCustomer = new CustomerDAOImpl();
				existingCustomer.login();
				break;
			case 3:
				// Employee login method with admin verification using boolean
				break;
			case 0:
				quit = true;
				break;
			default:
				System.out.println("Invalid selection.");
			}
			} catch (Exception e) {
			}
		} while (!quit);
		System.out.println("Goodbye!");
    }
}