package br.com.cgr.lucrocerto.service.providers;

import org.springframework.stereotype.Service;

import br.com.cgr.lucrocerto.model.User;
import br.com.cgr.lucrocerto.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Override
	public boolean sendConfirmationEmail(User user) {
		boolean success = false;
		System.out.println(user.getConfirmationKey());
		success = true;
		return success;
	}

}