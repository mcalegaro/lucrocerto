package br.com.cgr.lucrocerto.service.providers;

import org.springframework.stereotype.Service;

import br.com.cgr.lucrocerto.service.SomeService;

@Service
public class SomeServiceImpl implements SomeService {

	@Override
	public String getName() {
		return SomeServiceImpl.class.getName();
	}

}