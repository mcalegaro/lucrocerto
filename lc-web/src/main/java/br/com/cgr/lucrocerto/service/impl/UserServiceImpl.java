package br.com.cgr.lucrocerto.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cgr.lucrocerto.entity.UserEntity;
import br.com.cgr.lucrocerto.model.Authorities;
import br.com.cgr.lucrocerto.model.Authority;
import br.com.cgr.lucrocerto.model.User;
import br.com.cgr.lucrocerto.model.UserSignup;
import br.com.cgr.lucrocerto.repository.UserRepository;
import br.com.cgr.lucrocerto.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userR;

	@Autowired
	private PasswordEncoder pwdEncoder;

	@Override
	public List<User> getUsers(Pageable pageable, Sort sort) {
		Page<UserEntity> usersE = userR.findAll(pageable);
		List<User> users = new ArrayList<User>(usersE.getSize());
		for (UserEntity userE : usersE) {
			User user = new User();
			BeanUtils.copyProperties(userE, user);
			users.add(user);
		}
		return users;
	}

	@Override
	public User createUser(UserSignup userSignup) {
		UserEntity userE = null;
		if (userSignup.getUser().getName().equals(userSignup.getConfirmName())
				&& userSignup.getUser().getPwd().equals(userSignup.getConfirmPwd())) {
			userE = new UserEntity();
			userSignup.getUser().setPwd(pwdEncoder.encode(userSignup.getUser().getPwd()));
			Authority authority = new Authority();
			authority.setId(Authorities.USER.getId());
			List<Authority> authorities = new ArrayList<Authority>();
			authorities.add(authority);
			userSignup.getUser().setAuthorities(authorities);
			BeanUtils.copyProperties(userSignup.getUser(), userE, "authorities");
			userE.setAuthorities(Authority.getEntities(userSignup.getUser().getAuthorities()));
			userE = userR.save(userE);
		}
		return (userE != null ? userSignup.getUser() : null);
	}

	@Override
	public List<String> getNames(Pageable pageable) {
		Page<String> usersE = userR.findAllNames(pageable);
		List<String> users = new ArrayList<String>(usersE.getSize());
		for (String userE : usersE) {
			users.add(userE);
		}
		return users;
	}

	@Override
	public User getNameAndPwd(String name) {
		User user = new User();
		String[] userE = userR.findByName(name);
		if (userE != null) {
			user.setName(userE[0]);
			user.setPwd(userE[1]);
		}
		return user;
	}

	@Override
	public User findByName(String username) {
		User user = null;
		UserEntity userE = userR.findOne(username);
		if (userE != null) {
			user = new User();
			BeanUtils.copyProperties(userE, user);
			user.setAuthorities(Authority.parse(userE.getAuthorities()));
		}
		return user;
	}

}