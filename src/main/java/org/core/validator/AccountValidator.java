package org.core.validator;

import org.core.model.Account;
import org.core.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AccountValidator implements Validator{

	@Autowired
	AccountService accountService;
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Account.class.isAssignableFrom(clazz);
		}

	@Override
	public void validate(Object target, Errors errors) {
		Account account =  (Account)target; 
		if(!StringUtils.hasText(account.getPassword())){
			errors.rejectValue("password", "error.field.empty");
		}
		
		
	}

}
