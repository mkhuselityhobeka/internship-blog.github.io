package org.core.dao;

import java.util.List;

import org.core.model.Account;


public interface AccountDao {

	  Account findAccount(Long id);
	  List<Account>findAllAccounts();
	  Account createAccount(Account data);
	  Account findAccountByName(String name);
	 
}
