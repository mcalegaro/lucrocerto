package br.com.cgr.lucrocerto.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value = "/")
	public String showIndex(Model model) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("userName", userName);
		return "home";
	}

}