package org.rest.resources;

import java.util.ArrayList;
import java.util.List;

import org.core.model.Blog;
import org.springframework.hateoas.ResourceSupport;

public class BlogListResource extends ResourceSupport{



    private List<Blog> blogs = new ArrayList<Blog>();

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}
