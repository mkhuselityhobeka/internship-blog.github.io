package org.core.services.serviceImpl;

import org.core.dao.BlogDao;
import org.core.dao.BlogEntryDao;
import org.core.model.Blog;
import org.core.model.BlogEntry;
import org.core.services.BlogService;
import org.core.services.exceptions.BlogNotFoundException;
import org.core.utils.BlogEntryList;
import org.core.utils.BlogList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BlogServiceImpl implements BlogService{

	@Autowired
	BlogDao blogDao;
	
	@Autowired
	BlogEntryDao blogEntryDao;
	
	@Override
	public BlogEntry createBlogEntry(Long blogId, BlogEntry data) {
		
		Blog blog = blogDao.findBlog(blogId);
		if(blog == null){
			throw new BlogNotFoundException();
		}
    	 BlogEntry	blogEntry = blogEntryDao.createBlogEntry(data);
	            blogEntry.setBlog(blog);
		
	             return blogEntry;
	}

	@Override
	public BlogList findAllBlogs() {

		return new BlogList(blogDao.findAllBlogs());
	}

	@Override
	public BlogEntryList findAllBlogEntries(Long blogId) {
		
		Blog blog = blogDao.findBlog(blogId);
		if(blog == null){
			throw new BlogNotFoundException();
		}
		
		
        return new BlogEntryList(blogEntryDao.findByBlogId(blogId));
	}

	@Override
	public Blog findBlog(Long id) {
		
		return blogDao.findBlog(id);
	}

}
