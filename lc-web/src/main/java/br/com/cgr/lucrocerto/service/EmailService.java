package br.com.cgr.lucrocerto.service;

import br.com.cgr.lucrocerto.model.User;

public interface EmailService {

	boolean sendConfirmationEmail(User user);
	
}