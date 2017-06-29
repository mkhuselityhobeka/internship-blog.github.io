package org.rest.controllers;


import java.net.URI;

import org.core.model.Blog;
import org.core.model.BlogEntry;
import org.core.services.BlogService;
import org.core.services.exceptions.BlogNotFoundException;
import org.core.utils.BlogEntryList;
import org.core.utils.BlogList;

import org.rest.exceptions.NotFoundException;
import org.rest.resources.BlogEntryListResource;
import org.rest.resources.BlogEntryResource;
import org.rest.resources.BlogListResource;
import org.rest.resources.BlogResource;
import org.rest.resources.asm.BlogEntryListResourceAsm;
import org.rest.resources.asm.BlogEntryResourceAsm;
import org.rest.resources.asm.BlogListResourceASm;
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
public class BlogController {
	
	@Autowired
	private BlogService blogService;
 	
	public BlogController(BlogService blogService){
		this.blogService = blogService;
	}

	// get blog id
	@RequestMapping(value="/rest/blog-entries/{blogId}", method = RequestMethod.GET)
	public ResponseEntity<BlogResource> getBlog(@PathVariable Long blogId){
	
		Blog blog = blogService.findBlog(blogId);
		BlogResource blogResource = new BlogResourceAsm().toResource(blog);
		return new ResponseEntity<BlogResource>(blogResource, HttpStatus.OK);		
		
	}
	//post blog
	@RequestMapping(value="/rest/blog-Pentries/{blogEntryId}", method = RequestMethod.POST)
	public ResponseEntity<BlogEntryResource>createBlogEntry(@PathVariable Long  blogEntryId, 
			@RequestBody BlogEntryResource blogEntryResource){
		try{
			HttpHeaders httpHeaders = new HttpHeaders();		
			BlogEntry createBlogEntry = blogService.createBlogEntry(blogEntryId, blogEntryResource.toBlogEntry());
			BlogEntryResource blogResource = new BlogEntryResourceAsm().toResource(createBlogEntry);
			httpHeaders.setLocation(URI.create(blogResource.getLink("self").getHref()));
			return new ResponseEntity<BlogEntryResource>(blogResource, httpHeaders, HttpStatus.CREATED);
		
		}catch(BlogNotFoundException e){
			throw new NotFoundException(e);
		}
		 
		
	}
	
	//finding all blogs
	@RequestMapping(value="/rest/blogs", method = RequestMethod.GET)
	public ResponseEntity<BlogListResource>findAllBlogs(){
		
		BlogList blogList = blogService.findAllBlogs();
		BlogListResource blogListResource = new BlogListResourceASm().toResource(blogList);		
		return new ResponseEntity<BlogListResource>(blogListResource, HttpStatus.OK);
		
	}
	
	
@RequestMapping(value="/rest/blog-Bentries/{blogId}",method = RequestMethod.GET )
public ResponseEntity<BlogEntryListResource>findAllBlogEntries(@PathVariable Long blogId ){
	
	BlogEntryList blogEntryList = blogService.findAllBlogEntries(blogId);
	BlogEntryListResource blogEntryListResource = new BlogEntryListResourceAsm().toResource(blogEntryList);
	return new ResponseEntity<BlogEntryListResource>(blogEntryListResource, HttpStatus.OK);
	
}
	

}