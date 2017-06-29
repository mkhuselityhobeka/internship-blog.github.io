package org.core.dao;

import java.util.List;

import org.core.model.Blog;

public interface BlogDao {

	  Blog createBlog(Blog data);
	  List<Blog> findAllBlogs();
	  Blog findBlog(Long id);
	  Blog findBlogByTitle(String title);
	  List<Blog> findBlogsByAccount(Long accountId);
}
