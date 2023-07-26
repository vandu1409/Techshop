package com.vandu.service.Impl;

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

import com.vandu.enums.TokenType;
import com.vandu.model.Token;
import com.vandu.model.User;
import com.vandu.repository.TokenRepository;
import com.vandu.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {
	@Autowired
	private TokenRepository tokenRepository;

	@Override
	public long count() {
		return tokenRepository.count();
	}

	@Override
	public Optional<Token> findByTokenAndTokenType(String token, TokenType tokenType) {
		return tokenRepository.findByTokenAndTokenType(token, tokenType);
	}

	@Override
	public Optional<User> findUserByToken(String token, TokenType tokenType) {
		return tokenRepository.findUserByToken(token, tokenType);
	}

	@Override
	public <S extends Token> long count(Example<S> example) {
		return tokenRepository.count(example);
	}

	@Override
	public void delete(Token entity) {
		tokenRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		tokenRepository.deleteAll();
	}

	@Override
	public void deleteAll(Iterable<? extends Token> entities) {
		tokenRepository.deleteAll(entities);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		tokenRepository.deleteAllById(ids);
	}

	@Override
	public <S extends Token> S saveAndFlush(S entity) {
		return tokenRepository.saveAndFlush(entity);
	}

	@Override
	public void deleteInBatch(Iterable<Token> entities) {
		tokenRepository.deleteInBatch(entities);
	}

	@Override
	public void deleteAllInBatch(Iterable<Token> entities) {
		tokenRepository.deleteAllInBatch(entities);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		tokenRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void deleteAllInBatch() {
		tokenRepository.deleteAllInBatch();
	}

	@Override
	public void deleteById(Long id) {
		tokenRepository.deleteById(id);
	}

	@Override
	public <S extends Token> boolean exists(Example<S> example) {
		return tokenRepository.exists(example);
	}

	@Override
	public boolean existsById(Long id) {
		return tokenRepository.existsById(id);
	}

	@Override
	public List<Token> findAll() {
		return tokenRepository.findAll();
	}

	@Override
	public <S extends Token> Page<S> findAll(Example<S> example, Pageable pageable) {
		return tokenRepository.findAll(example, pageable);
	}

	@Override
	public <S extends Token> List<S> findAll(Example<S> example) {
		return tokenRepository.findAll(example);
	}

	@Override
	public <S extends Token> List<S> findAll(Example<S> example, Sort sort) {
		return tokenRepository.findAll(example, sort);
	}

	@Override
	public Page<Token> findAll(Pageable pageable) {
		return tokenRepository.findAll(pageable);
	}

	@Override
	public List<Token> findAll(Sort sort) {
		return tokenRepository.findAll(sort);
	}

	@Override
	public List<Token> findAllById(Iterable<Long> ids) {
		return tokenRepository.findAllById(ids);
	}

	@Override
	public <S extends Token, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return tokenRepository.findBy(example, queryFunction);
	}

	@Override
	public Optional<Token> findById(Long id) {
		return tokenRepository.findById(id);
	}

	@Override
	public <S extends Token> Optional<S> findOne(Example<S> example) {
		return tokenRepository.findOne(example);
	}

	@Override
	public void flush() {
		tokenRepository.flush();
	}

	@Override
	public <S extends Token> List<S> saveAllAndFlush(Iterable<S> entities) {
		return tokenRepository.saveAllAndFlush(entities);
	}

	@Override
	public Token getOne(Long id) {
		return tokenRepository.getOne(id);
	}

	@Override
	public Token getById(Long id) {
		return tokenRepository.getById(id);
	}

	@Override
	public Token getReferenceById(Long id) {
		return tokenRepository.getReferenceById(id);
	}

	@Override
	public <S extends Token> S save(S entity) {
		return tokenRepository.save(entity);
	}

	@Override
	public <S extends Token> List<S> saveAll(Iterable<S> entities) {
		return tokenRepository.saveAll(entities);
	}
	
	
}
