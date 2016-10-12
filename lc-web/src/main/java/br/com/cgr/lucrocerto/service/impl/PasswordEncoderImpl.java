package br.com.cgr.lucrocerto.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderImpl extends BCryptPasswordEncoder implements
		PasswordEncoder {

}