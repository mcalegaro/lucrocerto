package br.com.cgr.lucrocerto.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.cgr.lucrocerto.entity.AuthorityEntity;

public class Authority {
	private String id;
	private List<User> users;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public static Collection<AuthorityEntity> getEntities(
			List<Authority> authorities) {
		Collection<AuthorityEntity> authorityEntities = new ArrayList<AuthorityEntity>();
		for (Authority authority : authorities) {
			AuthorityEntity authorityEntity = new AuthorityEntity();
			authorityEntity.setId(authority.getId());
			authorityEntities.add(authorityEntity);
		}
		return authorityEntities;
	}

	public static List<Authority> parse(Collection<AuthorityEntity> authorityEntities) {
		List<Authority> authorities = new ArrayList<Authority>();
		for (AuthorityEntity authorityEntity : authorityEntities) {
			Authority authority = new Authority();
			authority.setId(authorityEntity.getId());
			authorities.add(authority);
		}
		return authorities;
	}

}