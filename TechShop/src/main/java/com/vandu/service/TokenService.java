package com.vandu.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.vandu.enums.TokenType;
import com.vandu.model.Token;
import com.vandu.model.User;

public interface TokenService {

	<S extends Token> List<S> saveAll(Iterable<S> entities);

	<S extends Token> S save(S entity);

	Token getReferenceById(Long id);

	Token getById(Long id);

	Token getOne(Long id);

	<S extends Token> List<S> saveAllAndFlush(Iterable<S> entities);

	void flush();

	<S extends Token> Optional<S> findOne(Example<S> example);

	Optional<Token> findById(Long id);

	<S extends Token, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	List<Token> findAllById(Iterable<Long> ids);

	List<Token> findAll(Sort sort);

	Page<Token> findAll(Pageable pageable);

	<S extends Token> List<S> findAll(Example<S> example, Sort sort);

	<S extends Token> List<S> findAll(Example<S> example);

	<S extends Token> Page<S> findAll(Example<S> example, Pageable pageable);

	List<Token> findAll();

	boolean existsById(Long id);

	<S extends Token> boolean exists(Example<S> example);

	void deleteById(Long id);

	void deleteAllInBatch();

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteAllInBatch(Iterable<Token> entities);

	void deleteInBatch(Iterable<Token> entities);

	<S extends Token> S saveAndFlush(S entity);

	void deleteAllById(Iterable<? extends Long> ids);

	void deleteAll(Iterable<? extends Token> entities);

	void deleteAll();

	void delete(Token entity);

	<S extends Token> long count(Example<S> example);

	long count();

	Optional<User> findUserByToken(String token, TokenType tokenType);

	Optional<Token> findByTokenAndTokenType(String token, TokenType tokenType);

}
