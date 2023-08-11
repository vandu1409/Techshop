package com.vandu.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.vandu.dto.response.ConversationResponseDto;
import com.vandu.model.Conversations;

public interface ConversationService {

	void deleteAll();

	<S extends Conversations> List<S> findAll(Example<S> example, Sort sort);

	<S extends Conversations> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends Conversations> entities);

	void deleteAllById(Iterable<? extends Long> ids);

	Conversations getReferenceById(Long id);

	void delete(Conversations entity);

	Conversations getById(Long id);

	void deleteById(Long id);

	long count();

	<S extends Conversations, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	Conversations getOne(Long id);

	void deleteAllInBatch();

	<S extends Conversations> boolean exists(Example<S> example);

	<S extends Conversations> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	boolean existsById(Long id);

	void deleteAllInBatch(Iterable<Conversations> entities);

	Optional<Conversations> findById(Long id);

	<S extends Conversations> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<Conversations> entities);

	List<Conversations> findAllById(Iterable<Long> ids);

	List<Conversations> findAll();

	<S extends Conversations> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Conversations> S saveAndFlush(S entity);

	Page<Conversations> findAll(Pageable pageable);

	void flush();

	List<Conversations> findAll(Sort sort);

	<S extends Conversations> Optional<S> findOne(Example<S> example);

	<S extends Conversations> List<S> saveAll(Iterable<S> entities);

	<S extends Conversations> S save(S entity);

	ConversationResponseDto converConversationResponseDto(Conversations conversations);

}
