package com.spoldzielnia.app.model;

public class User {
	
	private Integer 	id;
	private String 		firstName;
	private String 		lastName;
	private String		PESEL;
	private String 		birthDate;
	private String 		password;
	private String 		email;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getPESEL() {
		return PESEL;
	}
	public void setPESEL(String pESEL) {
		PESEL = pESEL;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
