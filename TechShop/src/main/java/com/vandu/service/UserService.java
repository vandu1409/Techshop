package com.vandu.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.vandu.enums.AuthenticationType;
import com.vandu.model.User;

import jakarta.transaction.Transactional;

@Service
public interface UserService {

	<S extends User> List<S> saveAll(Iterable<S> entities);

	<S extends User> S save(S entity);

	User getReferenceById(Long id);

	User getById(Long id);

	User getOne(Long id);

	<S extends User> List<S> saveAllAndFlush(Iterable<S> entities);

	void flush();

	<S extends User> Optional<S> findOne(Example<S> example);

	Optional<User> findById(Long id);

	<S extends User, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	List<User> findAllById(Iterable<Long> ids);

	List<User> findAll(Sort sort);

	Page<User> findAll(Pageable pageable);

	<S extends User> List<S> findAll(Example<S> example, Sort sort);

	<S extends User> List<S> findAll(Example<S> example);

	<S extends User> Page<S> findAll(Example<S> example, Pageable pageable);

	List<User> findAll();

	boolean existsById(Long id);

	<S extends User> boolean exists(Example<S> example);

	void deleteById(Long id);

	void deleteAllInBatch();

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteAllInBatch(Iterable<User> entities);

	void deleteInBatch(Iterable<User> entities);

	<S extends User> S saveAndFlush(S entity);

	void deleteAllById(Iterable<? extends Long> ids);

	void deleteAll(Iterable<? extends User> entities);

	void deleteAll();

	void delete(User entity);

	<S extends User> long count(Example<S> example);

	long count();



	Optional<User> findByUsername(String username);

	Page<User> findByUsernameContaining(String username, Pageable pageable);

	Optional<User> findByUsernameAndAuthenticationType(String username, AuthenticationType authenticationType);

	Optional<User> findByEmailAndAuthenticationType(String email, AuthenticationType authenticationType);

	Page<User> findAll(boolean isDelete, Pageable pageable);

	Page<User> findByUsernameContainingAndIsDeleted(String username, boolean isDelete, Pageable pageable);

	Optional<User> findByEmailAndUsernameAndAuthenticationType(String email, String username, AuthenticationType authenticationType);

	<S extends User> S update(S entity);


}
