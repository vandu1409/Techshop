package com.vandu.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vandu.enums.AuthenticationType;
import com.vandu.model.User;

import com.vandu.repository.UserRepository;
import com.vandu.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public Optional<User> findByEmailAndUsernameAndAuthenticationType(String email, String username,
			AuthenticationType authenticationType) {
		return userRepository.findByEmailAndUsernameAndAuthenticationType(email, username, authenticationType);
	}

	@Autowired
	private UserRepository userRepository;

	
	@Autowired
	PasswordEncoder passwordEncoder;

//	@Autowired
//	private PasswordEncoder passwordEncoder;



	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}


	@Override
	public Page<User> findByUsernameContainingAndIsDeleted(String username, boolean isDelete, Pageable pageable) {
		return userRepository.findByUsernameContainingAndIsDeleted(username, isDelete, pageable);
	}


	@Override
	public Page<User> findAll(boolean isDelete, Pageable pageable) {
		return userRepository.findAll(isDelete, pageable);
	}


	@Override
	public Optional<User> findByEmailAndAuthenticationType(String email, AuthenticationType authenticationType) {
		return userRepository.findByEmailAndAuthenticationType(email, authenticationType);
	}


	@Override
	public Optional<User> findByUsernameAndAuthenticationType(String username, AuthenticationType authenticationType) {
		return userRepository.findByUsernameAndAuthenticationType(username, authenticationType);
	}


	@Override
	public Page<User> findByUsernameContaining(String username, Pageable pageable) {
		return userRepository.findByUsernameContaining(username, pageable);
	}

	@Override
	public long count() {
		return userRepository.count();
	}

	@Override
	public <S extends User> long count(Example<S> example) {
		return userRepository.count(example);
	}

	@Override
	public void delete(User entity) {
		userRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		userRepository.deleteAll();
	}

	@Override
	public void deleteAll(Iterable<? extends User> entities) {
		userRepository.deleteAll(entities);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		userRepository.deleteAllById(ids);
	}

	@Override
	public <S extends User> S saveAndFlush(S entity) {
	
		return userRepository.saveAndFlush(entity);
	}

	@Override
	public void deleteInBatch(Iterable<User> entities) {
		userRepository.deleteInBatch(entities);
	}

	@Override
	public void deleteAllInBatch(Iterable<User> entities) {
		userRepository.deleteAllInBatch(entities);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		userRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void deleteAllInBatch() {
		userRepository.deleteAllInBatch();
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public <S extends User> boolean exists(Example<S> example) {
		return userRepository.exists(example);
	}

	@Override
	public boolean existsById(Long id) {
		return userRepository.existsById(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
		return userRepository.findAll(example, pageable);
	}

	@Override
	public <S extends User> List<S> findAll(Example<S> example) {
		return userRepository.findAll(example);
	}

	@Override
	public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
		return userRepository.findAll(example, sort);
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public List<User> findAll(Sort sort) {
		return userRepository.findAll(sort);
	}

	@Override
	public List<User> findAllById(Iterable<Long> ids) {
		return userRepository.findAllById(ids);
	}

	@Override
	public <S extends User, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return userRepository.findBy(example, queryFunction);
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public <S extends User> Optional<S> findOne(Example<S> example) {
		return userRepository.findOne(example);
	}

	@Override
	public void flush() {
		userRepository.flush();
	}

	@Override
	public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
		return userRepository.saveAllAndFlush(entities);
	}

	@Override
	public User getOne(Long id) {
		return userRepository.getOne(id);
	}

	@Override
	public User getById(Long id) {
		return userRepository.getById(id);
	}

	@Override
	public User getReferenceById(Long id) {
		return userRepository.getReferenceById(id);
	}

	@Override
	public <S extends User> S save(S entity) {
		System.err.println("Password : "+entity.getPassword());
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		return userRepository.save(entity);
	}
	@Override
	public <S extends User> S update(S entity) {
		
		return userRepository.save(entity);
	}
	
	@Override
	public <S extends User> List<S> saveAll(Iterable<S> entities) {
		return userRepository.saveAll(entities);
	}

	

}
