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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cgr.lucrocerto.model.User;
import br.com.cgr.lucrocerto.model.UserSignup;
import br.com.cgr.lucrocerto.service.EmailService;
import br.com.cgr.lucrocerto.service.UserService;
import br.com.cgr.lucrocerto.service.exception.AlreadyRegisteredException;

@Controller
public class SignController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private UserService userService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private EmailService emailService;

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
	public String signup(UserSignup userSignup, Model model) {
		String r = "redirect:/showSignup";
		User user = null;
		try {
			user = userService.createUser(userSignup);
			if (user != null) {
				// r = login(user.getName(), userSignup.getConfirmPwd(), r);
				if (emailService.sendConfirmationEmail(user)) {
					r = "notLogged/awaitingConfirmation";
				}
			}
		} catch (AlreadyRegisteredException e) {
			model.addAttribute("validateEmail", "Usuário indisponível.");
		}
		return r;
	}

	@RequestMapping(value = "/confirm")
	public String confirm(HttpServletRequest req, Model model) {
		String message = null;
		String email = req.getParameter("email");
		String key = req.getParameter("confirmationKey");
		boolean validate = validateParams(email, key);
		if (validate) {
			message = userService.confirm(email, key);
			validate = StringUtils.isEmpty(message);
		}
		model.addAttribute("confirmationSuccess", validate);
		model.addAttribute("confirmationMessage", message);
		return "notLogged/confirmation";
	}

	private boolean validateParams(String... param) {
		boolean success = true;
		for (String string : param) {
			if (StringUtils.isEmpty(string)) {
				success = false;
			}
		}
		return success;
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