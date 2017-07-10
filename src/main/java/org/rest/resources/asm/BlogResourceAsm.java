package org.rest.resources.asm;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.core.model.Blog;
import org.rest.controllers.AccountController;
import org.rest.controllers.BlogController;
import org.rest.resources.BlogResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class BlogResourceAsm extends ResourceAssemblerSupport<Blog , BlogResource> {

	public BlogResourceAsm() {
		super(BlogController.class,BlogResource.class);
	}

	@Override
	public BlogResource toResource(Blog blog) {
		
		BlogResource blogResource = new BlogResource();
		blogResource.setTitle(blog.getTitle());
		
		blogResource.add(linkTo(methodOn(BlogController.class).getBlog(blog.getId())).withSelfRel());
		blogResource.add(linkTo(methodOn(BlogController.class).findAllBlogEntries(blog.getId())).withRel("blog-entries"));
		if(blog.getOwner() != null){
			blogResource.add(linkTo(methodOn(AccountController.class).getAccount(blog.getOwner().getId())).withRel("accounts"));
		}

		return  blogResource;
	}

}
