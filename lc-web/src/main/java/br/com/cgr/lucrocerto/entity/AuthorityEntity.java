package br.com.cgr.lucrocerto.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * Entity implementation class for Entity: Authority
 *
 */
@Entity
public class AuthorityEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@ManyToMany(mappedBy="authorities")
	private List<UserEntity> users;
	
	public AuthorityEntity() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}
	
}