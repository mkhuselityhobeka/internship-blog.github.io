package org.rest.controllers;


import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

import javax.validation.Valid;

import org.core.model.Account;
import org.core.model.Blog;
import org.core.services.AccountService;
import org.core.services.exceptions.AccountDoesNotExistException;
import org.core.services.exceptions.AccountExistsException;
import org.core.services.exceptions.BlogExistsException;
import org.core.utils.AccountList;
import org.core.utils.BlogList;
import org.core.validator.AccountValidator;
import org.rest.exceptions.BadRequestException;
import org.rest.exceptions.ConflictException;
import org.rest.exceptions.NotFoundException;
import org.rest.resources.AccountListResource;
import org.rest.resources.AccountResource;
import org.rest.resources.BlogListResource;
import org.rest.resources.BlogResource;
import org.rest.resources.asm.AccountListResurceAsm;
import org.rest.resources.asm.AccountResourceAsm;
import org.rest.resources.asm.BlogListResourceASm;
import org.rest.resources.asm.BlogResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AccountController {

	
	public AccountController() {
	
	}

	@Autowired
	private AccountService accountService; 
	
	@Autowired
	AccountValidator validator;
	
	public AccountController(AccountService accountService){
		this.accountService = accountService;
	}
	
	@RequestMapping(value="/rest/accounts/{accountId}", method= RequestMethod.GET)
	public ResponseEntity<AccountResource> getAccount(@PathVariable Long accountId){	
		
		Account account = accountService.findAccount(accountId);	
		
		if(account != null){	
			AccountResource accountResource = new AccountResourceAsm().toResource(account);
			return new  ResponseEntity<AccountResource>(accountResource, HttpStatus.OK);

		}else{
			return new  ResponseEntity<AccountResource>( HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(value="/rest/accounts", method= RequestMethod.POST)
    public ResponseEntity<AccountResource> createAccounts(@Validated@RequestBody AccountResource resource){
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
	
	
	@RequestMapping(value="/rest/accounts/{accountId}/blogs", method= RequestMethod.POST)
	 public ResponseEntity<BlogResource> createBlog(@PathVariable Long accountId ,
			 @RequestBody BlogResource resource){
		try{
			HttpHeaders httpHeaders = new HttpHeaders();
			Blog blog = accountService.createBlog(accountId, resource.toBlog());
			BlogResource blogResource = new BlogResourceAsm().toResource(blog);
			httpHeaders.setLocation(URI.create(blogResource.getLink("self").getHref()));
			return new ResponseEntity<BlogResource>(blogResource,httpHeaders, HttpStatus.CREATED);
		}catch(AccountDoesNotExistException exception){
			throw new BadRequestException() ;
		}catch(BlogExistsException ex){
			throw new ConflictException();
		}
		
	}
	
	
	
	@RequestMapping(value="/rest/accounts", method= RequestMethod.GET)
	public ResponseEntity<AccountListResource>findAllAccounts(@RequestParam(value="name", required = false) String name){
		
		 AccountList list = null;
	        if(name == null) {
	            list = accountService.findAllAccounts();
	        } else {
	            Account account = accountService.findByAccountName(name);
	            if(account == null) {
	                list = new AccountList(new ArrayList<Account>());
	            } else {
	                list = new AccountList(Arrays.asList(account));
	            }
	        }
	        AccountListResource res = new AccountListResurceAsm().toResource(list);
	        return new ResponseEntity<AccountListResource>(res, HttpStatus.OK);
	    }
	
	
	
	@RequestMapping(value="/rest/accounts/{accountId}/blogs", method= RequestMethod.GET)
	public ResponseEntity<BlogListResource>getAllBlogs(@PathVariable Long accountId){
		
		 try {
	            BlogList blogList = accountService.findBlogsByAccount(accountId);
	            BlogListResource blogListRes = new BlogListResourceASm().toResource(blogList);
	            return new ResponseEntity<BlogListResource>(blogListRes, HttpStatus.OK);
	        } catch(AccountDoesNotExistException exception)
	        {
	            throw new NotFoundException(exception);
	        }
	    }
		
	
	
}
