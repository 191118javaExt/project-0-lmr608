package com.revature.repositories;

public interface AccountDAO {
	
	public List<Account> getAllAccounts();
	public void viewAccount();
	public void createAccount();
	public void deleteAccount();
	
}