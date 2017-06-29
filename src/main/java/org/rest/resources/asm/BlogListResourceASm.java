package org.rest.resources.asm;

import org.core.utils.BlogList;
import org.rest.controllers.BlogController;
import org.rest.resources.BlogListResource;
//import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class BlogListResourceASm extends ResourceAssemblerSupport<BlogList, BlogListResource>{

	public BlogListResourceASm() {
		super(BlogController.class, BlogListResource.class);
		
	}


	@Override
	public BlogListResource toResource(BlogList blogList) {
		// TODO Auto-generated method stub
		 BlogListResource bloListResource = new BlogListResource();
		 bloListResource.setBlogs(blogList.getBlogs());

		return bloListResource ;
	}

}
