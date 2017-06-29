package org.core.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.core.dao.BlogEntryDao;
import org.core.model.BlogEntry;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BlogEntryDaoImpl implements BlogEntryDao {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public BlogEntry findBlogEntry(Long id) {
		return entityManager.find(BlogEntry.class, id);
	}

	@Override
	
	public BlogEntry deleteBlogEntry(Long id) {
	BlogEntry blogEntry = entityManager.find(BlogEntry.class, id);
	 entityManager.remove(blogEntry);
		return blogEntry;
	}

	@Override
	public BlogEntry updateBlogEntry(Long id, BlogEntry data) {
		BlogEntry blogEntry = entityManager.find(BlogEntry.class, id);
		blogEntry.setTitle(data.getTitle());
		blogEntry.setContent(data.getContent());
		return blogEntry;
	}

	@Override
	public BlogEntry createBlogEntry(BlogEntry data) {
		entityManager.persist(data);
		return data;
	}

	@Override
	public List<BlogEntry> findByBlogId(Long blogId) {
		Query query = entityManager.createQuery("SELECT b FROM Blog b WHERE b.blogId =?1");
		query.setParameter(1, blogId);	
		return query.getResultList();
	}

}
