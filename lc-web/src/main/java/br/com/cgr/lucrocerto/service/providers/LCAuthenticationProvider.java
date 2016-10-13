package br.com.cgr.lucrocerto.service.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.cgr.lucrocerto.model.UserStatus;
import br.com.cgr.lucrocerto.repository.UserRepository;

public class LCAuthenticationProvider extends DaoAuthenticationProvider {

	@Autowired
	private UserRepository userR;

//	@Override
//	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//		Authentication authenticate = super.authenticate(authentication);
//		UserDetails details = (UserDetails) authentication.getPrincipal();
//		UserStatus status = userR.getStatus(details.getUsername());
//		if (!UserStatus.ACTIVE.equals(status)) {
//			authenticate.setAuthenticated(false);
//		}
//		return authenticate;
//	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		UserStatus status = userR.getStatus(userDetails.getUsername());
		if (!UserStatus.ACTIVE.equals(status)) {
			throw new LockedException("Usuário não está ativo.");
		}
	}
	
}