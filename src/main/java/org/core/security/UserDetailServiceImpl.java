package org.core.security;

import org.core.model.Account;
import org.core.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	AccountService service;
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    Account account = service.findByAccountName(name);	
    if(account == null){
    	throw new UsernameNotFoundException("No user has been found with this name sorry! "+name);
    }

     return new AccountUserDetails(account);
	}

}
