package org.core.DaoImpl;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.core.dao.BlogDao;
import org.core.model.Account;
import org.core.model.Blog;
import org.springframework.stereotype.Repository;

@Repository
public class BlogDaoImpl implements BlogDao{

	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Blog createBlog(Blog data) {
		
		 entityManager.persist(data);;
		 return data;
	}

	@Override
	public List<Blog> findAllBlogs() {
			
		return entityManager.createQuery("FROM Blog order by id",Blog.class).getResultList();
	}

	@Override
	public Blog findBlog(Long id) {
		
		return entityManager.find(Blog.class, id);
	}

	@Override
	public Blog findBlogByTitle(String title) {
		Query query = entityManager.createQuery("SELECT b FROM Blog b WHERE a.title =?1 ");
		query.setParameter(1, title);
		List<Blog> blogs = query.getResultList();
		if(blogs.isEmpty()){
			return null;
		}else{
			return blogs.get(0);

		}
	}

	@Override
	public List<Blog> findBlogsByAccount(Long id) {
		
		return entityManager.createQuery("FROM Blog order by id",Blog.class).getResultList();
	}

	

}
