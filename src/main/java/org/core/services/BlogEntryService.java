package org.core.services;

import org.core.model.BlogEntry;

public interface BlogEntryService {
	
	 BlogEntry findBlogEntry(Long id); 
	 BlogEntry deleteBlogEntry(Long id); 
	 BlogEntry updateBlogEntry(Long id, BlogEntry data);

}
