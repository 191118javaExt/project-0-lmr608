package com.revature.repositories;

import java.util.List;

import com.revature.models.Customer;

public interface CustomerDAO {

	public List<Customer> getAllCustomers();
	public void createCustomer();
	public void viewCustomer();
	public void updateCustomer();
	public void deleteCustomer();
	public void login();
	
}