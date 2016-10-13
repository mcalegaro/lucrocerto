package br.com.cgr.lucrocerto.service.providers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.cgr.lucrocerto.model.Authority;
import br.com.cgr.lucrocerto.service.UserService;

@Service
public class UserDetailsProvider implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = null;
		br.com.cgr.lucrocerto.model.User user = userService.findByEmail(username);
		if (user != null) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(user.getAuthorities().size());
			for (Authority authority : user.getAuthorities()) {
				GrantedAuthority ga = new SimpleGrantedAuthority(authority.getId());
				authorities.add(ga);
			}
			userDetails = new User(username, user.getPwd(), authorities);
		}
		return userDetails;
	}

}