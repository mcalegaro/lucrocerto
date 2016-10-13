package br.com.cgr.lucrocerto.service.providers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cgr.lucrocerto.service.LCPasswordEncoder;

@Service
public class PasswordEncoderProvider extends BCryptPasswordEncoder implements LCPasswordEncoder {

	public PasswordEncoderProvider() {
		super(13);
	}
	
}