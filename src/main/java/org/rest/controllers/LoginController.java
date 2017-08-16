//package org.rest.controllers;
//
//import org.core.dao.AccountDao;
//import org.core.dao.LoginDao;
//import org.core.model.Login;
//import org.core.services.exceptions.AccountDoesNotExistException;
//import org.rest.exceptions.BadRequestException;
//import org.rest.resources.LoginResource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//@Controller
//public class LoginController {
//	@Autowired
//	LoginDao loginDao;
//	@RequestMapping(value="rest/login", method = RequestMethod.POST)
//	public ResponseEntity<LoginResource> checkLogin(@RequestBody LoginResource loginResource,String email , String password){
//		try{
//			HttpHeaders httpHeaders = new HttpHeaders();
//           Login login = loginDao.login(loginResource.toLogin());
//		}catch(AccountDoesNotExistException exception){
//			throw new BadRequestException();
//		}
//		return new ResponseEntity<LoginResource>();
//	}
//}
