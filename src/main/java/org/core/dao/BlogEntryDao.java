package org.core.dao;

import java.util.List;

import org.core.model.BlogEntry;

public interface BlogEntryDao {

	  BlogEntry findBlogEntry(Long id); 
	  BlogEntry deleteBlogEntry(Long id); 
      BlogEntry updateBlogEntry(Long id, BlogEntry data);
      BlogEntry createBlogEntry(BlogEntry data);
      List<BlogEntry> findByBlogId(Long blogId);

}
