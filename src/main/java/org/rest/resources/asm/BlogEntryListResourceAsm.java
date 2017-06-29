package org.rest.resources.asm;


import java.util.*;

import org.core.utils.BlogEntryList;
import org.rest.controllers.BlogController;
import org.rest.resources.BlogEntryListResource;
import org.rest.resources.BlogEntryResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class BlogEntryListResourceAsm extends ResourceAssemblerSupport<BlogEntryList,BlogEntryListResource> {

	public BlogEntryListResourceAsm() {
		super(BlogController.class, BlogEntryListResource.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BlogEntryListResource toResource(BlogEntryList list) {
		 List<BlogEntryResource> resources = new BlogEntryResourceAsm().toResources(list.getEntries());
		 BlogEntryListResource listResource = new BlogEntryListResource();
		 listResource.setEntries(resources);
		 listResource.add(linkTo(methodOn(BlogController.class).findAllBlogEntries(list.getBlogId())).withSelfRel());
		return listResource;
	}

}
