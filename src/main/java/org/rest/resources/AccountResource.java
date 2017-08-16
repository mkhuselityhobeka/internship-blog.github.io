package org.rest.resources;

import org.core.model.Account;
import org.springframework.hateoas.ResourceSupport;

public class AccountResource extends ResourceSupport {
	
	private Long id;	
	
	
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	private String password;
	public void setId(Long id) {
		this.id = id;
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
		 
		    account.setId(id);
	        account.setName(name);
	        account.setEmail(email);
	        account.setPassword(password);
	        return account;
	}
}
