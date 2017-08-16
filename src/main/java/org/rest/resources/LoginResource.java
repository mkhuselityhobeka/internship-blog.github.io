package org.rest.resources;

import org.core.model.Login;
import org.springframework.hateoas.ResourceSupport;

public class LoginResource extends ResourceSupport {
	private String email;
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Login toLogin(){
		Login login = new Login();
		login.setEmail(email);
		login.setPassword(password);
		return login;
		
	}
}
