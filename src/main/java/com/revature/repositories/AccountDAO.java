package com.revature.repositories;

import java.util.List;

import com.revature.models.Account;

public interface AccountDAO {
	
	public List<Account> getAllAccounts();
	public void createAccount();
	public void viewAccount();
	public void deleteAccount();
	
}