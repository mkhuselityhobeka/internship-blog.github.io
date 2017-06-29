package org.rest.resources.asm;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.core.model.Account;
import org.rest.controllers.AccountController;
import org.rest.resources.AccountResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class AccountResourceAsm extends ResourceAssemblerSupport<Account, AccountResource> {

	public AccountResourceAsm() {
		super(AccountController.class, AccountResource.class);
	}

	@Override
	public AccountResource toResource(Account account) {
		   AccountResource accountResource = new AccountResource();
		   accountResource.setName(account.getName());
		   accountResource.setPassword(account.getPassword());
		   accountResource.add(linkTo(methodOn(AccountController.class).getAccount(account.getId())).withSelfRel());
		  return accountResource;
	}

}
