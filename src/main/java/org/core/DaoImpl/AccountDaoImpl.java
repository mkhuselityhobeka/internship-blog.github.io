package org.core.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.core.dao.AccountDao;
import org.core.model.Account;
import org.core.model.Blog;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class AccountDaoImpl implements AccountDao{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<Account> findAllAccounts() {
		Query query = entityManager.createQuery("SELECT a FROM Account a");
		return query.getResultList();
	}

	
	@Override
	public Account findAccount(Long id) {
		return entityManager.find(Account.class, id);
	}
	@Override
	
	public Account createAccount(Account data) {
		// TODO Auto-generated method stub
		
		  entityManager.persist(data);
		  return data;
	}

	
	@Override
	public Account findAccountByName(String name) {
		Query query = entityManager.createQuery("SELECT a FROM Account a WHERE a.name = ?1");
		query.setParameter(1, name);
		List<Account> accounts = query.getResultList();
		if(accounts.size() == 0){
			return null;
		}else{
			return accounts.get(0);
		}
		
	}

}
