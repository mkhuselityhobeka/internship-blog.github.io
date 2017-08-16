package org.rest.resources;


import org.core.model.Account;
import org.core.model.Blog;
import org.springframework.hateoas.ResourceSupport;

public class BlogResource extends ResourceSupport{
	
	private String title;
	private String url;
	private Account owner;	

	public Account getOwner() {
		return owner;
	}
	public void setOwner(Account owner) {
		this.owner = owner;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	

	public Blog toBlog() {
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setUrl(url);
        blog.setOwner(owner);
        return blog;
    }
}
