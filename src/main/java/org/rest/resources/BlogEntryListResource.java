package org.rest.resources;

import java.util.*;


import org.springframework.hateoas.ResourceSupport;

public class BlogEntryListResource extends ResourceSupport{
	String title;
    public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	List<BlogEntryResource> entries = new ArrayList<>();
	public List<BlogEntryResource> getEntries() {
		return entries;
	}
	public void setEntries(List<BlogEntryResource> entries) {
		this.entries = entries;
	}
	
	
}
