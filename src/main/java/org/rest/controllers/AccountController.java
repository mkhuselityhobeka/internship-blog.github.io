package org.rest.controllers;



import java.net.URI;
import org.core.model.Account;
import org.core.model.Blog;
import org.core.services.AccountService;
import org.core.services.exceptions.AccountDoesNotExistException;
import org.core.services.exceptions.AccountExistsException;
import org.core.services.exceptions.BlogExistsException;
import org.rest.exceptions.BadRequestException;
import org.rest.exceptions.ConflictException;
import org.rest.resources.AccountResource;
import org.rest.resources.BlogResource;
import org.rest.resources.asm.AccountResourceAsm;
import org.rest.resources.asm.BlogResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController {

	@Autowired
	private AccountService accountService; 
	
	public AccountController(AccountService accountService){
		this.accountService = accountService;
	}
	
	@RequestMapping(value="/rest/account-entries/{accountId}", method= RequestMethod.GET)
	public ResponseEntity<AccountResource> getAccount(@PathVariable Long accountId){
		
		Account account = accountService.findAccount(accountId);	
		if(account != null){
			
			AccountResource accountResource = new AccountResourceAsm().toResource(account);
			return new  ResponseEntity<AccountResource>(accountResource, HttpStatus.OK);

		}else{
			return new  ResponseEntity<AccountResource>( HttpStatus.NOT_FOUND);

		}
		
	}
	
	@RequestMapping(value="/rest/Createaccount-entries", method= RequestMethod.POST)
    public ResponseEntity<AccountResource> createAccounts(@RequestBody AccountResource resource){
	try{
		HttpHeaders httpHeaders = new HttpHeaders();
		Account account = accountService.createAccount(resource.toAccount());
		AccountResource accountResource = new AccountResourceAsm().toResource(account);
		httpHeaders.setLocation(URI.create(accountResource.getLink("self").getHref()));
		return new ResponseEntity<AccountResource>(accountResource,httpHeaders, HttpStatus.CREATED);
	}catch(AccountExistsException exception){
	 throw new ConflictException();
	}
		
	}
	
	@RequestMapping(value="/rest/CreateBlog-entries/{blogId}", method= RequestMethod.POST)
	 public ResponseEntity<BlogResource> createBlog(@PathVariable Long blogId ,
			 @RequestBody BlogResource resource){
		try{
			HttpHeaders httpHeaders = new HttpHeaders();
			Blog blog = accountService.createBlog(blogId, resource.toBlog());
			BlogResource blogResource = new BlogResourceAsm().toResource(blog);
			httpHeaders.setLocation(URI.create(blogResource.getLink("self").getHref()));
			return new ResponseEntity<BlogResource>(blogResource,httpHeaders, HttpStatus.CREATED);
		}catch(AccountDoesNotExistException exception){
			throw new BadRequestException() ;
		}catch(BlogExistsException ex){
			throw new ConflictException();
		}
		
	}
}
