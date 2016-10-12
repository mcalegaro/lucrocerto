package br.com.cgr.lucrocerto.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
public class UserEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private String name;
	
	private String pwd;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name="users_aut"
			, joinColumns={
				@JoinColumn(name="users_name")
				}
			, inverseJoinColumns={
				@JoinColumn(name="authorities_id")
				}
			)
	private Collection<AuthorityEntity> authorities;
	
	public UserEntity() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Collection<AuthorityEntity> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<AuthorityEntity> authorities) {
		this.authorities = authorities;
	}
	
}