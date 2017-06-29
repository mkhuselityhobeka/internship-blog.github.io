package org.core.dao;

import java.util.List;

import org.core.model.Account;
import org.core.model.Blog;

public interface AccountDao {

	  Account findAccount(Long id);
	  List<Account>findAllAccounts();
	  Account createAccount(Account data);
	  Account findAccountByName(String name);
	 
}
