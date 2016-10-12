package br.com.cgr.lucrocerto.model;

public class UserSignup {
	private User user;
	private String confirmName;
	private String confirmPwd;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getConfirmName() {
		return confirmName;
	}

	public void setConfirmName(String confirmName) {
		this.confirmName = confirmName;
	}

	public String getConfirmPwd() {
		return confirmPwd;
	}

	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}

}