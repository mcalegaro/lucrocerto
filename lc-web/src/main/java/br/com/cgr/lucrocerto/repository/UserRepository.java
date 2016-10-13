package br.com.cgr.lucrocerto.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.cgr.lucrocerto.entity.UserEntity;
import br.com.cgr.lucrocerto.model.UserStatus;

public interface UserRepository extends CrudRepository<UserEntity, String> {

	Page<UserEntity> findAll(Pageable pageable);

	List<UserEntity> findAll(Sort sort);

	@Query("select u.name from UserDataEntity u")
	Page<String> findAllNames(Pageable pageable);

	@Query("select u.email, u.pwd, u.status from UserEntity u where u.email = ?1")
	String[] findByEmail(String name);

	@Query("select u.status from UserEntity u where u.email = ?1")
	UserStatus getStatus(String email);

}