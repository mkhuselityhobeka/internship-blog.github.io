package org.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;




@Entity
public class Account {
	@Id
	@GeneratedValue
	private Long id;
	@Column	
	private String name;
	@Column	
	private String email;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	@Column	
	private String password;
	@Override
	public String toString() {
		return "Account [name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	
	
	
	
	
}
