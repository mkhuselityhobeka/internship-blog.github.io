package org.controllers;


import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rest.controllers.BlogEntryController;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.core.model.BlogEntry;
import org.core.services.BlogEntryService;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class BlogEntryControllerTest {

	@InjectMocks
	private BlogEntryController blogEntryController;
	 private MockMvc mockMvc;
	
	 @Mock 
	 BlogEntryService  blogEntryService; 
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(blogEntryController).build();
	}
	
	//Testing a existing blog entries
	@Test
	public void getExistingBlogEntry(){
		
		
		try{
				
			    BlogEntry entry = new BlogEntry();
		        entry.setId(1L);
		        entry.setTitle("Test Title");

		        when(blogEntryService.findBlogEntry(1L)).thenReturn(entry);

		        mockMvc.perform(get("/rest/blog-entries/1"))
		        		.andDo(print())
		                .andExpect(jsonPath("$.title", is(entry.getTitle())))
		                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/blog-entries/1"))))
		                .andExpect(status().isOk());	    
		}catch(Exception e){
			e.getStackTrace();
		}
		
}
	
	
		@Test
		public void getNonExistingBlogEntry(){

		try{
			   when(blogEntryService.findBlogEntry(1L)).thenReturn(null);
		        mockMvc.perform(get("/rest/blog-entries/1"))
		        .andExpect(status().isNotFound());	    
		}catch(Exception e){
			e.getStackTrace();
		}
		
		
	}
		
		@Test
		public void deleteEntries(){
			
			try{
				
				 BlogEntry deleteEntry = new BlogEntry();
				 deleteEntry.setId(1L);
				 deleteEntry.setTitle("delete this blog entry");
				 when(blogEntryService.deleteBlogEntry(1L)).thenReturn(deleteEntry);
				 mockMvc.perform(delete("/rest/delete-entries/1"))
				 .andDo(print())
				 .andExpect(jsonPath("$.title", is(deleteEntry.getTitle())))
				 .andExpect(status().isOk());
				 
				 
			}catch(Exception e){
				
				e.getStackTrace();
			}
		}
		
		@Test
		public void updateEntries(){
			try{
				BlogEntry updateEntry = new BlogEntry();
				updateEntry.setId(1L);
				updateEntry.setTitle("Updated entry");
				when(blogEntryService.updateBlogEntry(eq(1L), any(BlogEntry.class))).thenReturn(updateEntry);
				 mockMvc.perform(put("/rest/update-entries/1")
			    
			     .content("{\"title\":\"Test Title\"}")
			     .contentType(MediaType.APPLICATION_JSON))
				 .andDo(print())
			     .andExpect(jsonPath("$.title", is(updateEntry.getTitle())))
			     .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/blog-entries/1"))))
			     .andExpect(status().isOk());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
}

