package org.core.security;

import java.util.ArrayList;
import java.util.Collection;


import org.core.model.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;



@SuppressWarnings("serial")
public class AccountUserDetails implements UserDetails{

	
	private  Account account;
	
	public  AccountUserDetails(){
		super();
	}
	
	public AccountUserDetails(Account account){
		this.account= account;
	}
	

	@Override
	public Collection<? extends GrantedAuthority>getAuthorities() {
		
		@SuppressWarnings("serial")
		GrantedAuthority grantedAuthority = new GrantedAuthority(){

			@Override
			public String getAuthority() {
				// TODO Auto-generated method stub
				return "USER";
			}
			
		};
		ArrayList<GrantedAuthority> authories = new ArrayList<>();
		authories.add(grantedAuthority);
		return authories;
	}
	
	

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return account.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return account.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
