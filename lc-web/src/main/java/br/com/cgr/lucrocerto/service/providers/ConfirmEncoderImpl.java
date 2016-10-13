package br.com.cgr.lucrocerto.service.providers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cgr.lucrocerto.service.ConfirmEncoder;

@Service
public class ConfirmEncoderImpl extends BCryptPasswordEncoder implements ConfirmEncoder {

	public ConfirmEncoderImpl() {
		super(14);
	}
	
}