package com.spoldzielnia.app.model;

public class PasswordUser {
	private String oldPassword;
	private String oldPasswordHash;
	private String newPassword;
	private String login;
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getOldPasswordHash() {
		return oldPasswordHash;
	}
	public void setOldPasswordHash(String oldPasswordHash) {
		this.oldPasswordHash = oldPasswordHash;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
}
