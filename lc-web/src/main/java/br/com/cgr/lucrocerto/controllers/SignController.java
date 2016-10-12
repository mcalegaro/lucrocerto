package br.com.cgr.lucrocerto.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cgr.lucrocerto.model.User;
import br.com.cgr.lucrocerto.model.UserSignup;
import br.com.cgr.lucrocerto.service.UserService;

@Controller
public class SignController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private UserService userService;

	@Autowired
	private UserDetailsService userDetailsService;

	@RequestMapping(value = "/login")
	public String showLogin() {
		return "login";
	}

	@RequestMapping(value = "/showSignup")
	public String showRegister(Model model) {
		UserSignup userSignup = new UserSignup();
		userSignup.setUser(new User());
		model.addAttribute("userSignup", userSignup);
		return "signup";
	}

	@RequestMapping(value = "/signup")
	public String signup(UserSignup userSignup, HttpServletRequest req) {
		String r = "redirect:/showSignup";
		User user = userService.createUser(userSignup);
		if (user != null) {
			r = login(user.getName(), userSignup.getConfirmPwd(), r);
		}
		return r;
	}

	// Somente para signup
	private String login(String userName, String password, String r) {
		// perform login authentication
		UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, password,
				userDetails.getAuthorities());
		authManager.authenticate(auth);
		if (auth.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		r = "redirect:/";
		return r;
	}

}