package br.com.cgr.lucrocerto.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import br.com.cgr.lucrocerto.model.UserStatus;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String email;

	private String pwd;

	@Enumerated
	private UserStatus status;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_aut", joinColumns = { @JoinColumn(name = "users_email") }, inverseJoinColumns = {
			@JoinColumn(name = "authorities_id") })
	private Collection<AuthorityEntity> authorities;

	public UserEntity() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public Collection<AuthorityEntity> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<AuthorityEntity> authorities) {
		this.authorities = authorities;
	}

}