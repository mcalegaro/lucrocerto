package br.com.cgr.lucrocerto.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.cgr.lucrocerto.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, String> {

	Page<UserEntity> findAll(Pageable pageable);

	List<UserEntity> findAll(Sort sort);

	@Query("select name from UserEntity u")
	Page<String> findAllNames(Pageable pageable);

	@Query("select u.name, u.pwd from UserEntity u where u.name = ?1")
	String[] findByName(String name);
}