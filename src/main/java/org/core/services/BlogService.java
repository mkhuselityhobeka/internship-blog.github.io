package org.core.services;

import org.core.model.Blog;
import org.core.model.BlogEntry;
import org.core.utils.BlogEntryList;
import org.core.utils.BlogList;

public interface BlogService {


	   BlogEntry createBlogEntry(Long blogId, BlogEntry data);

	   BlogList findAllBlogs();

	   BlogEntryList findAllBlogEntries(Long blogId); 

	    Blog findBlog(Long id);

}
