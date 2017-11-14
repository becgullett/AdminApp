package edu.ben.models;

import java.io.Serializable;

public class User implements Serializable{
	int idUser;
	String firstName;
	String lastName;
	String email;
	String username;
	String password;
	boolean isActive;
	boolean locked;
	String security;
	public User(int idUser, String firstName, String lastName, String email, String username, String password,
			boolean isActive, boolean locked, String security) {
		super();
		this.idUser = idUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.isActive = isActive;
		this.locked = locked;
		this.security = security;
	}
	
	public User(String firstName, String lastName, String email, String username, String password, String security) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.isActive = true;
		this.locked = false;
		this.security = security;
	}
	public User() {
		
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean isLocked) {
		this.locked = isLocked;
	}
	public String getSecurity() {
		return security;
	}
	public void setSecurity(String security) {
		this.security = security;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", username=" + username + ", password=" + password + ", isActive=" + isActive + ", isLocked="
				+ locked + ", security=" + security + "]";
	}
	
	
	
}
