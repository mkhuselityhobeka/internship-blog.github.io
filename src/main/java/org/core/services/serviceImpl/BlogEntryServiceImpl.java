package org.core.services.serviceImpl;


import org.core.dao.BlogEntryDao;
import org.core.model.BlogEntry;
import org.core.services.BlogEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BlogEntryServiceImpl implements BlogEntryService{

	@Autowired
	BlogEntryDao blogEntryDao;
	
	
	
	@Override
	public BlogEntry findBlogEntry(Long id) {
		
		return blogEntryDao.findBlogEntry(id);
	}

	@Override
	public BlogEntry deleteBlogEntry(Long id) {
		
		return blogEntryDao.deleteBlogEntry(id);
	}

	@Override
	public BlogEntry updateBlogEntry(Long id, BlogEntry data) {	
		
		return blogEntryDao.updateBlogEntry(id, data);
	}

	

}
