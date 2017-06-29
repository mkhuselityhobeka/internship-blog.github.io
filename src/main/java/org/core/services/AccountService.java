package org.core.services;

import org.core.model.Account;
import org.core.model.Blog;
import org.core.utils.AccountList;
import org.core.utils.BlogList;

public interface AccountService {
	 public Account findAccount(Long id);
	    public Account createAccount(Account data);
	    public Blog createBlog(Long accountId, Blog data);
	    public BlogList findBlogsByAccount(Long accountId);
	    public AccountList findAllAccounts();
	    public Account findByAccountName(String name);

}
