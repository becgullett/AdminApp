package edu.ben.models;

public class AddForm {
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private String security;
	
	
	/**
	 * Constructor
	 * 
	 * @param firstName
	 *            first name on form
	 * @param lastName
	 *            last name on form
	 * @param email
	 *            email on form
	 * @param username
	 *            username on form
	 * @param password
	 *            password on form
	 * @param security
	 *			  security level
	 */
	public AddForm(String firstName, String lastName, String email, String username, String password, String security) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.security = security;
	}

	/**
	 * Constructor that sets all variables to empty strings
	 */
	public AddForm() {
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.username = "";
		this.password = "";
		this.security = "";
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

	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}
	
	
}
