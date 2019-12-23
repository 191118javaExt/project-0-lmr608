package com.revature.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.revature.models.Account;

public class AccountServiceTest {

	private static Account account1;
	private static Account account2;

	@Before
	public void setUp() throws Exception {
		account1 = new Account(1, 100, true);
		account2 = new Account(2, 0, false)
	}

	@Test
	public void testDeposit() {
		assertEquals(new Double(101), new Double(account1.deposit(1)));
	}
	
	@Test
	public void testWithdraw() {
		assertEquals(new Double(99), new Double(account1.withdraw(1)));
	}
	
	@Test
	public void testTransfer() {
		assertEquals(new Double(99), new Double(account1.withdraw(1)));
		assertEquals(new Double(101), new Double(account2.deposit(1)));
	}
	
	@Test
	public void testApproveAccount() {
		assertEquals(new Boolean(true), new Boolean(account2.approveAccount(true)));
	}

}
