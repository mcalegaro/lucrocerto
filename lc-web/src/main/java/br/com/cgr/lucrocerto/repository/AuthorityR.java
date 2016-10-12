package br.com.cgr.lucrocerto.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.cgr.lucrocerto.entity.AuthorityEntity;

public interface AuthorityR extends CrudRepository<AuthorityEntity, String> {

}
