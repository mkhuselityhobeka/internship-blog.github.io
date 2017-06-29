package org.rest.resources.asm;

import org.core.model.BlogEntry;
import org.rest.controllers.BlogEntryController;
import org.rest.resources.BlogEntryResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;


public class BlogEntryResourceAsm extends ResourceAssemblerSupport<BlogEntry, BlogEntryResource>{
	public BlogEntryResourceAsm(){
		super(BlogEntryController.class ,BlogEntryResource.class);
	}
	
@Override
public BlogEntryResource toResource(BlogEntry blogEntry){
	
	BlogEntryResource blogEntryResource = new BlogEntryResource();
	 blogEntryResource.setTitle(blogEntry.getTitle());
	 linkTo(methodOn(BlogEntryController.class).getBlogEntry(blogEntry.getId())).withSelfRel();
	 blogEntryResource.add (linkTo(methodOn(BlogEntryController.class).getBlogEntry(blogEntry.getId())).withSelfRel());
	 if(blogEntry.getBlog()!=null){
		 blogEntryResource.add (linkTo(methodOn(BlogEntryController.class).getBlogEntry(blogEntry.getId())).withSelfRel());
	}
	 
	 return blogEntryResource;
	
}

}
