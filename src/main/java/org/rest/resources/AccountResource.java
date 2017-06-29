package org.rest.resources;

import org.core.model.Account;
import org.springframework.hateoas.ResourceSupport;

public class AccountResource extends ResourceSupport {

	
	
	private String name;
	private String password;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Account toAccount() {
		// TODO Auto-generated method stub
		 Account account = new Account();
	        account.setName(name);;
	        return account;
	}
}
