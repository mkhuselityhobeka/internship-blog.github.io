package org.core.repositories;


import static org.junit.Assert.*;
import org.core.dao.AccountDao;
import org.core.model.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
public class AccountDaoTest {

@Autowired
AccountDao accountDao;

 private Account account = new Account();





@Before
@Transactional
@Rollback(false)
public void setup(){

 

    account.setName("name");
    account.setPassword("password");
    accountDao.createAccount(account);
}

@Test
@Transactional
public void testFind(){

	 
    accountDao.findAccountByName(this.account.getName());
   assertNotNull(account);	
   assertEquals(account.getName(),"name");
   assertEquals(account.getPassword(),"password");
   System.out.println(account.getName());

    
     }
}
