package org.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.core.model.Account;
import org.core.model.Blog;
import org.core.services.AccountService;
import org.core.services.exceptions.AccountDoesNotExistException;
import org.core.services.exceptions.BlogExistsException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rest.controllers.AccountController;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


public class AccountControllerTest {
	
	@InjectMocks
	AccountController accountController;
	MockMvc mockMvc;
	
	@Mock
	AccountService accountService;
	
	 private ArgumentCaptor<Account> accountCaptor;
	@Before 
	public void setup(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
	}
	
 @Test
 public void getExistingAccount(){ // test to get all existing blog accounts
	 try{
		 Account account = new Account();
		 account.setId(1L);
		 account.setName("Mkhsueli");
		 account.setPassword("Tyhobeka");
		 when(accountService.findAccount(1L)).thenReturn(account);
		 mockMvc.perform(get("/rest/account-entries/1"))
		 .andDo(print())
		 .andExpect(jsonPath("$.name",is(account.getName())))
		 .andExpect(jsonPath("$.password",is(account.getPassword())))
		 .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/account-entries/1"))))
		 .andExpect(status().isOk());
		
		 
		 
	 }catch(Exception e){
		 e.printStackTrace();
	 }
   }
 
 
 @Test
 public void getNonExistingAccount() throws Exception { //Test to determine when user request a non existing account
     
	 when(accountService.findAccount(1L)).thenReturn(null);
     mockMvc.perform(get("/rest/account-entries/1"))
             .andDo(print())
             .andExpect(status().isNotFound());
 }
 
 @Test
 public void createAccountNoneExistingUsername(){ // Test to determine existing username in case user tries to create with non existing user name 
	 try{
		 Account account = new Account();
		 account.setName("JumpCo");
		 account.setPassword("Cape Town");		
	   	 when(accountService.createAccount(any(Account.class))).thenReturn(account);
		 mockMvc.perform(post("/rest/Createaccount-entries")
		 .content("{\"name\":\"test\",\"password\":\"test\"}")
         .contentType(MediaType.APPLICATION_JSON))
		 .andDo(print())
         .andExpect(header().string("Location", org.hamcrest.Matchers.endsWith("/rest/accounts")))
         .andExpect(jsonPath("$.name", is(account.getName())))
         .andExpect(status().isCreated());
		 
		 verify(accountService).createAccount(accountCaptor.capture());
		 String password = accountCaptor.getValue().getPassword();
	        assertEquals("test", password);
	 }catch(Exception e){
		 
	 }
  }
 
 @Test
 public void createAccountExistingUsername(){ // test to create exixting username ,username cannot have same usernmae
	 try{
		 Account account = new Account();
		 account.setName("JumpCo");
		 account.setPassword("Cape Town");
		 when(accountService.createAccount(any(Account.class))).thenReturn(account);
		 mockMvc.perform(post("/rest/Createaccount-entries")
		 .content("{\"name\":\"test\",\"password\":\"test\"}")
		 .contentType(MediaType.APPLICATION_JSON))
		 .andDo(print())
         .andExpect(header().string("Location", org.hamcrest.Matchers.endsWith("/rest/accounts")))
         .andExpect(jsonPath("$.name", is(account.getName())))
         .andExpect(status().isConflict());
	 }catch(Exception e){
		 
	 }
 }
 
 @Test
 public void createBlogExistingAccount(){ //create blog in a account ,eg if user is already registered then he/she create a blod
	 try{
		 
		Blog blog = new Blog();
		blog.setId(1L);
		blog.setTitle("Spring blog");
		when(accountService.createBlog(eq(1L), any(Blog.class))).thenReturn(blog);
		mockMvc.perform(post("/rest/CreateBlog-entries/1")
		.content("{\"title\":\"Test Title\"}")
        .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(jsonPath("$.title", is(blog.getTitle())))
        
        .andExpect(status().isCreated());
		
		 
	 }catch(Exception exception){
		 exception.getStackTrace();
	 }
 }
 @Test
 public void createBlogNonExistingAccount() throws Exception{ //cant create blog with not existing user must first register the bad requestStaus is returned
 
		when(accountService.createBlog(eq(1L), any(Blog.class))).thenThrow(new AccountDoesNotExistException ());
		mockMvc.perform(post("/rest/CreateBlog-entries/1")
		.content("{\"title\":\"Test Title\"}")
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
	    .andExpect(status().isBadRequest());		
     }
 
 @Test
 public void createExistingBlogName() throws Exception{ //
	 
	 when(accountService.createBlog(eq(1L), any(Blog.class))).thenThrow(new BlogExistsException());
	 mockMvc.perform(post("/rest/CreateBlog-entries/1")
			 .content("{\"title\":\"Test Title\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
			    .andExpect(status().isConflict());		 
        }
}