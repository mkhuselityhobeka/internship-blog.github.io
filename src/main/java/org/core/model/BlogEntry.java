package org.core.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



@Entity
public class BlogEntry {
	
	
	@Id
	@GeneratedValue
	private Long id;
  
	@ManyToOne
	private Blog blog;
    
	private String content;
    
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	 public Blog getBlog() {
	        
			return blog;
	    }

	    public void setBlog(Blog blog) {
	        this.blog = blog;
	    }
}
