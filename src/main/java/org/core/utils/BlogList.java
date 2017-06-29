package org.core.utils;

import java.util.*;

import org.core.model.Blog;

public class BlogList {


    private List<Blog> blogs = new ArrayList<Blog>();

    public List<Blog> getBlogs() {
        return blogs;
    }

    public BlogList(List<Blog> blogs) {
		super();
		this.blogs = blogs;
	}

	public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
	
	
}
