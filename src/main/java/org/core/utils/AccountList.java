package org.core.utils;

import java.util.ArrayList;
import java.util.List;

import org.core.model.Account;

public class AccountList {

	private List<Account> accounts = new ArrayList<>();

	
	public AccountList(List<Account> accounts) {

		this.accounts = accounts;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
}
