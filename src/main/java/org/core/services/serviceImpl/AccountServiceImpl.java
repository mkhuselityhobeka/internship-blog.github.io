package org.core.services.serviceImpl;



import org.core.dao.AccountDao;
import org.core.dao.BlogDao;
import org.core.model.Account;
import org.core.model.Blog;
import org.core.services.AccountService;
import org.core.services.exceptions.AccountDoesNotExistException;
import org.core.services.exceptions.AccountExistsException;
import org.core.services.exceptions.BlogExistsException;
import org.core.utils.AccountList;
import org.core.utils.BlogList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountDao accountDao;
	
	@Autowired
	BlogDao blogDao;
	
	@Override
	public Account findAccount(Long id) {
		return accountDao.findAccount(id);
	}

	@Override
	public Account createAccount(Account data) {
		
       Account account = accountDao.findAccountByName(data.getName());
     
       if(account != null){
    	   throw new AccountExistsException();
       }
		return accountDao.createAccount(data);
	}

	@Override
	public Blog createBlog(Long accountId, Blog data) {
		
		Blog blog = blogDao.findBlogByTitle(data.getTitle());
		if(blog != null){
			throw new BlogExistsException();
		}
		Account account = accountDao.findAccount(accountId);
			if(account == null){
				throw new AccountDoesNotExistException();
			}
		Blog createBlog = blogDao.createBlog(data);
		createBlog.setOwner(account);
		return createBlog;
	}

	
	@Override
	public BlogList findBlogsByAccount(Long accountId) {
		
		Account account = accountDao.findAccount(accountId);
		if(account == null){
			throw new AccountDoesNotExistException();
		}		
		
		return new BlogList(blogDao.findBlogsByAccount(accountId));
	}
	

	@Override
	public AccountList findAllAccounts() {
		
		
		return new AccountList(accountDao.findAllAccounts());
	}

	@Override
	public Account findByAccountName(String name) {
		
		Account account = accountDao.findAccountByName(name);
		return account;
	}

	
	

}
