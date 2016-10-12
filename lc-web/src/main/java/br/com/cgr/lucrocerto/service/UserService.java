package br.com.cgr.lucrocerto.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import br.com.cgr.lucrocerto.model.User;
import br.com.cgr.lucrocerto.model.UserSignup;

public interface UserService {
	List<User> getUsers(Pageable pageable, Sort sort);

	List<String> getNames(Pageable pageable);

	User getNameAndPwd(String name);

	User findByName(String username);

	User createUser(UserSignup userSignup);
}
