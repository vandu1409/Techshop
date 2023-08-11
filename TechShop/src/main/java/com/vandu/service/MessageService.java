package com.vandu.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.vandu.model.Conversations;
import com.vandu.model.Message;
import com.vandu.model.User;

public interface MessageService {

	void deleteAll();

	<S extends Message> List<S> findAll(Example<S> example, Sort sort);

	<S extends Message> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends Message> entities);

	void deleteAllById(Iterable<? extends Long> ids);

	Message getReferenceById(Long id);

	void delete(Message entity);

	Message getById(Long id);

	void deleteById(Long id);

	long count();

	<S extends Message, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	Message getOne(Long id);

	void deleteAllInBatch();

	<S extends Message> boolean exists(Example<S> example);

	<S extends Message> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	boolean existsById(Long id);

	void deleteAllInBatch(Iterable<Message> entities);

	Optional<Message> findById(Long id);

	<S extends Message> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<Message> entities);

	List<Message> findAllById(Iterable<Long> ids);

	List<Message> findAll();

	<S extends Message> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Message> S saveAndFlush(S entity);

	Page<Message> findAll(Pageable pageable);

	void flush();

	List<Message> findAll(Sort sort);

	<S extends Message> Optional<S> findOne(Example<S> example);

	<S extends Message> List<S> saveAll(Iterable<S> entities);

	<S extends Message> S save(S entity);



	List<Message> findByUserOrderByCreateAtDesc(User user);

	void sendMessage(User user, String content, Conversations conversations);

	Optional<Conversations> findConversationsByUser(Long userId);

}
