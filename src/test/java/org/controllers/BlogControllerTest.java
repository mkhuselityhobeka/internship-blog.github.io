package org.controllers;



import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.*;
import org.core.model.Account;
import org.core.model.Blog;
import org.core.model.BlogEntry;
import org.core.services.BlogService;
import org.core.services.exceptions.BlogNotFoundException;
import org.core.utils.BlogEntryList;
import org.core.utils.BlogList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rest.controllers.BlogController;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class BlogControllerTest {
	
	@InjectMocks
	private BlogController blogController;
    MockMvc mockMvc;
    
	@Mock
	private BlogService blogService;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(blogController).build();
	}
	
	@Test
	public void getExisitngBlogs(){
		try{
			Blog blog = new Blog();
			
			blog.setId(1L);
			blog.setTitle("Fuse blog");
			Account jumpCo = new Account();
			blog.setOwner(jumpCo);
			jumpCo.setId(1L);
			jumpCo.setName("labs");
			jumpCo.setPassword("cape town");
			when(blogService.findBlog(1L)).thenReturn(blog);
			mockMvc.perform(get("/rest/blog-entries/1"))
			.andDo(print())
			.andExpect(jsonPath("$.title",is(blog.getTitle())))
			//.andExpect(jsonPath("$.id",is(jumpCo.getId())))			
			.andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/blog-entries/1"))))
			.andExpect(status().isOk());		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void createBlogEntry(){
		try{ 
			
			Blog blog = new Blog();
			blog.setId(1L);
			BlogEntry blogEntry = new BlogEntry();
			blogEntry.setTitle("JBPM BLOG LINK");
			blogEntry.setId(1L);
			when(blogService.createBlogEntry(eq(1L), any(BlogEntry.class))).thenReturn(blogEntry);
			 mockMvc.perform(post("/rest/1/blog-entries")			 		 
		     .content("{\"title\":\"Generic Title\"}")
		     .contentType(MediaType.APPLICATION_JSON))
			 .andDo(print())
		     .andExpect(jsonPath("$.title", is(blogEntry.getTitle())))
		     .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("rest/blog-entries/1"))))
		     .andExpect(header().string("Location", endsWith("rest/blog-entries/1")))
		     .andExpect(status().isCreated());
		}catch(Exception e){
			e.printStackTrace();
		}
		  
	}
	
	
	
	
	
	//@SuppressWarnings("unchecked")
	@SuppressWarnings("unchecked")
	@Test
	public void findAllBlogs(){
		try{
			
			List<Blog> list = new ArrayList<>();
		    Blog blogA = new Blog();
		    blogA.setId(1L);
			blogA.setTitle("new java Blog");
			list.add(blogA);			
			 Blog blogB = new Blog();
			 blogB.setId(1L);
			 blogB.setTitle("new Fuse Blog");
			 list.add(blogB);
			
			 BlogList allBlogs = new BlogList(list);			 
			 allBlogs.setBlogs(list);
			 when((blogService.findAllBlogs())).thenReturn(allBlogs);
			 mockMvc.perform(get("/rest/blogs"))
			 .andDo(print())
//			 .andExpect(jsonPath("$.title", is(blogA.getTitle())))
//			 .andExpect(jsonPath("$.title", is(blogB.getTitle())))
			 .andExpect(jsonPath("$.blogs[*].title",hasItems(endsWith(blogA.getTitle()), endsWith(blogB.getTitle()))))
             .andExpect(status().isOk());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void findAllBlogEntries(){
		
		try{
			BlogEntry blogEntryA = new BlogEntry();
			
			blogEntryA.setId(1L);
			blogEntryA.setTitle("Camel blog added");
			List<BlogEntry> list = new ArrayList<>();
			list.add(blogEntryA);
			BlogEntry blogEntryB = new BlogEntry();
			blogEntryB.setId(3L);
			blogEntryB.setTitle("JBPM blog added");
			list.add(blogEntryB);
		     BlogEntryList listEntry = new BlogEntryList(1L, list);
			listEntry.setEntries(list);
			listEntry.setBlogId(1L);
			//listEntry.setTitle("blogs");
			when(blogService.findAllBlogEntries(1L)).thenReturn(listEntry);
			mockMvc.perform(get("/rest/blog-Bentries/1"))
			.andDo(print())
			
	        .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/rest/blog-Bentries/1"))))
	       //.andExpect(jsonPath("$.entries[*].title", hasItem(is("Test Title"))))
	        .andExpect(status().isOk());
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
	}
	
	@Test
	public void listBlogEntriesForNonExistingBlog() throws Exception{
		when(blogService.findAllBlogEntries(1L)).thenThrow(new BlogNotFoundException());
		mockMvc.perform(get("/rest/blog-Bentries/1"))
		.andDo(print())
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void createBlogEntryNonexistingBlog() throws Exception{
        BlogEntry blogEntry = new BlogEntry();
		when(blogService.createBlogEntry(eq(1L), any(BlogEntry.class))).thenReturn(blogEntry).thenThrow(new BlogNotFoundException());
		mockMvc.perform(post("/rest/blog-Pentries/1")
		.content("{\"title\":\"Generic Title\"}")
	   .contentType(MediaType.APPLICATION_JSON))
	   .andExpect(status().isNotFound());
		
		
		
	}
}
