package org.rest.resources.asm;

import java.util.List;

import org.core.utils.AccountList;
import org.rest.controllers.AccountController;
import org.rest.resources.AccountListResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.rest.resources.AccountResource;


public class AccountListResurceAsm extends ResourceAssemblerSupport<AccountList,AccountListResource>{

	public AccountListResurceAsm() {
		super(AccountController.class, AccountListResource.class);
	}

	@Override
	public AccountListResource toResource(AccountList accountList) {
		
		List<AccountResource> resList = new AccountResourceAsm().toResources(accountList.getAccounts());
	        AccountListResource accountListResource = new AccountListResource();
	        accountListResource.setAccounts(resList);
	        return accountListResource;
	}

}
