package org.core.utils;

import java.util.*;

import org.core.model.BlogEntry;

public class BlogEntryList {
    
    private Long blogId;
	
    public BlogEntryList(List<BlogEntry> findByBlogId) {
		
	}
	public BlogEntryList(long l, List<BlogEntry> list) {
		// TODO Auto-generated constructor stub
	}
	public Long getBlogId() {
		return blogId;
	}
	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}
	String title;
	
	List<BlogEntry> entries = new ArrayList<>();
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<BlogEntry> getEntries() {
		return entries;
	}
	public void setEntries(List<BlogEntry> entries) {
		this.entries = entries;
	}
	
}
