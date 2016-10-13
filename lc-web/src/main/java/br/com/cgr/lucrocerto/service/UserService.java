package br.com.cgr.lucrocerto.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import br.com.cgr.lucrocerto.model.User;
import br.com.cgr.lucrocerto.model.UserSignup;

public interface UserService {
	List<User> getUsers(Pageable pageable, Sort sort);

	List<String> getNames(Pageable pageable);

	User getEmailAndPwd(String name);

	User findByEmail(String email);

	User createUser(UserSignup userSignup);

	String buildConfirmationKey(Date date, String email);

	String confirm(String email, String key);

}
