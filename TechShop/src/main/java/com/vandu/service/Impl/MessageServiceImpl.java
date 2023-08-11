package com.vandu.service.Impl;

import java.time.LocalDate;
import java.util.Date;
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

import com.vandu.model.Conversations;
import com.vandu.model.Message;
import com.vandu.model.User;
import com.vandu.repository.MessageRepository;
import com.vandu.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{
	@Override
	public Optional<Conversations> findConversationsByUser(Long userId) {
		return messageRepository.findConversationsByUser(userId);
	}

	@Autowired
	private MessageRepository messageRepository;
	
	
	 @Override
	public void sendMessage(User user, String content,Conversations conversations) {
	        Message message = new Message();
	        message.setConversations(conversations);
	        message.setUser(user);
	        message.setMessage(content);
	        message.setCreateAt(new Date());
	        messageRepository.save(message);
	    }

//	    public void replyMessage(User admin, Long messageId, String content) {
//	        Optional<Message> optionalMessage = messageRepository.findById(messageId);
//	        if (optionalMessage.isPresent()) {
//	            Message message = optionalMessage.get();
//	            message.setAdmin(admin);
//	            message.setContent(content);
//	            message.setTimestamp(LocalDateTime.now());
//	            messageRepository.save(message);
//	        } else {
//	            // Xử lý khi không tìm thấy tin nhắn có id tương ứng
//	        }
//	    }
	
	@Override
	public List<Message> findByUserOrderByCreateAtDesc(User user) {
		return messageRepository.findByUserOrderByCreateAtDesc(user);
	}

	

	@Override
	public <S extends Message> S save(S entity) {
		return messageRepository.save(entity);
	}

	@Override
	public <S extends Message> List<S> saveAll(Iterable<S> entities) {
		return messageRepository.saveAll(entities);
	}

	@Override
	public <S extends Message> Optional<S> findOne(Example<S> example) {
		return messageRepository.findOne(example);
	}

	@Override
	public List<Message> findAll(Sort sort) {
		return messageRepository.findAll(sort);
	}

	@Override
	public void flush() {
		messageRepository.flush();
	}

	@Override
	public Page<Message> findAll(Pageable pageable) {
		return messageRepository.findAll(pageable);
	}

	@Override
	public <S extends Message> S saveAndFlush(S entity) {
		return messageRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Message> List<S> saveAllAndFlush(Iterable<S> entities) {
		return messageRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<Message> findAll() {
		return messageRepository.findAll();
	}

	@Override
	public List<Message> findAllById(Iterable<Long> ids) {
		return messageRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<Message> entities) {
		messageRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Message> Page<S> findAll(Example<S> example, Pageable pageable) {
		return messageRepository.findAll(example, pageable);
	}

	@Override
	public Optional<Message> findById(Long id) {
		return messageRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Message> entities) {
		messageRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return messageRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		messageRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Message> long count(Example<S> example) {
		return messageRepository.count(example);
	}

	@Override
	public <S extends Message> boolean exists(Example<S> example) {
		return messageRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		messageRepository.deleteAllInBatch();
	}

	@Override
	public Message getOne(Long id) {
		return messageRepository.getOne(id);
	}

	@Override
	public <S extends Message, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return messageRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return messageRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		messageRepository.deleteById(id);
	}

	@Override
	public Message getById(Long id) {
		return messageRepository.getById(id);
	}

	@Override
	public void delete(Message entity) {
		messageRepository.delete(entity);
	}

	@Override
	public Message getReferenceById(Long id) {
		return messageRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		messageRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Message> entities) {
		messageRepository.deleteAll(entities);
	}

	@Override
	public <S extends Message> List<S> findAll(Example<S> example) {
		return messageRepository.findAll(example);
	}

	@Override
	public <S extends Message> List<S> findAll(Example<S> example, Sort sort) {
		return messageRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		messageRepository.deleteAll();
	}

	
	
	
}
