//package org.rest.resources.asm;
//
//
//
//import org.core.model.Login;
//import org.rest.controllers.LoginController;
//import org.rest.resources.LoginResource;
//import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
//
//public class LoginResourceAsm extends ResourceAssemblerSupport<Login,LoginResource> {
//
//	public LoginResourceAsm() {
//		super(LoginController.class, LoginResource.class);
//		// TODO Auto-generated constructor stub
//	}
//
//
//
//	@Override
//	public LoginResource toResource(Login login) {
//		LoginResource loginResource = new LoginResource();
//		loginResource.setEmail(login.getEmail());
//		loginResource.setPassword(login.getPassword());
//		return loginResource;
//	}}
