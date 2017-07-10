package org.rest.controllers;

import org.core.model.BlogEntry;
import org.core.services.BlogEntryService;
import org.rest.resources.BlogEntryResource;
import org.rest.resources.asm.BlogEntryResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class BlogEntryController{
	
	
private BlogEntryService blogEntryService ;

public BlogEntryController(){
	
}
@Autowired

private BlogEntryController(BlogEntryService  blogEntryService){
	 this.blogEntryService =  blogEntryService;
}


@RequestMapping(value="/rest/blog-entries/{blogEntryId}", method=RequestMethod.GET)
	public ResponseEntity<BlogEntryResource> getBlogEntry(@PathVariable Long blogEntryId){
	   
	   BlogEntry blogEntry = blogEntryService.findBlogEntry(blogEntryId);
	   if(blogEntry != null){
		   BlogEntryResource  blogEntryResource = new  BlogEntryResourceAsm().toResource(blogEntry);
			return new ResponseEntity<BlogEntryResource>(blogEntryResource, HttpStatus.OK);
	   }else
		   
		   return new ResponseEntity<BlogEntryResource>( HttpStatus.NOT_FOUND);
		
	}


@RequestMapping(value="rest/delete-entries/{blogEntryId}", method=RequestMethod.DELETE)
public ResponseEntity<BlogEntryResource> deleteBlog(@PathVariable Long blogEntryId){
	 BlogEntry blogEntry = blogEntryService.deleteBlogEntry(blogEntryId);
	 BlogEntryResource blogEntryResource = new BlogEntryResourceAsm().toResource(blogEntry); 
	 if(blogEntry !=null){
		 return new ResponseEntity<BlogEntryResource>(blogEntryResource,HttpStatus.OK);
	  
	 }else{
		 return new ResponseEntity<BlogEntryResource>( HttpStatus.NOT_FOUND);
	 }
   }

@RequestMapping(value="rest/update-entries/{blogEntryId}", method=RequestMethod.PUT)
public ResponseEntity<BlogEntryResource> updateEntry(@PathVariable Long blogEntryId,
		@RequestBody BlogEntryResource resource){
	
	BlogEntry updateEntry = blogEntryService.updateBlogEntry(blogEntryId, resource.toBlogEntry());
	BlogEntryResource blogEntryResource = new BlogEntryResourceAsm().toResource(updateEntry);
	if(updateEntry !=null){
		return new  ResponseEntity<BlogEntryResource>(blogEntryResource, HttpStatus.OK);

	}else{
		 	return new  ResponseEntity<BlogEntryResource>( HttpStatus.NOT_FOUND);

	}
	
     }
}
