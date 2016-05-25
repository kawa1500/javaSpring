package com.spoldzielnia.app.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idUser;
	
	private String firstName;
	private String lastName;
	private String PESEL;
	private String email;
	private String phone;
	private String password;
	private String login;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<UserRole> userRole = new HashSet<UserRole>(0);

	@OneToMany(mappedBy="user")
	private List<Counters> userCounters;
	
	@OneToOne
	private Flat flat;
	
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
	public void setPESEL(String PESEL) {
		this.PESEL = PESEL;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString()
	{
		String result = "name: "+firstName+" ,surname: "+lastName+" ,email: "+email+" ,PESEL: "+PESEL+" ,phone: "+phone+" ,password: "+password;
		return result;
	}
	/*
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_userrole_flat", joinColumns = {@JoinColumn(name = "user_iduser", nullable = false, updatable = false), 
			@JoinColumn(name = "idFlat", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "userrole_id", nullable = false, updatable = false) })
	*/
		@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_userrole", joinColumns = {@JoinColumn(name = "user_iduser", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "userrole_id", nullable = false, updatable = false) })
	public Set<UserRole> getUserRole() {
		return userRole;
	}
	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public List<Counters> getUserCounters() {
		return userCounters;
	}
	public void setUserCounters(List<Counters> userCounters) {
		this.userCounters = userCounters;
	}
	public Flat getFlat() {
		return flat;
	}
	public void setFlat(Flat flat) {
		this.flat = flat;
	}
	
}
