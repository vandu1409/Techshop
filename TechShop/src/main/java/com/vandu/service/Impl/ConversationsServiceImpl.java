package com.vandu.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.vandu.dto.response.ConversationResponseDto;
import com.vandu.dto.response.MessageResponseDto;
import com.vandu.enums.Role;
import com.vandu.model.Conversations;
import com.vandu.repository.ConversationRespository;
import com.vandu.service.ConversationService;

@Service
public class ConversationsServiceImpl  implements ConversationService{
	
	
	
	@Override
	public ConversationResponseDto converConversationResponseDto(Conversations conversations) {
		ConversationResponseDto conversationResponseDto = new ConversationResponseDto();
		
		BeanUtils.copyProperties(conversations, conversationResponseDto);
		
		if(conversations.getMessages()!=null) {
			List<MessageResponseDto> list = conversations.getMessages().stream().map(item->{
				MessageResponseDto messageResponseDto = new MessageResponseDto();
				BeanUtils.copyProperties(item, messageResponseDto);
				messageResponseDto.setAdmin(!item.getUser().getRole().equals(Role.ROLE_USER));
				
				if(item.getUser().getRole().equals(Role.ROLE_USER)) {
					conversationResponseDto.setUsername(item.getUser().getFullname());
				}
				
				return messageResponseDto;
			}).toList();
			
			if(list.size()>0) {
				conversationResponseDto.setTitle(list.get(list.size()-1).getMessage());
				
			}
			
			
			
			conversationResponseDto.setListMessages(list);
		}
		
		return conversationResponseDto;
	}
	
	@Override
	public <S extends Conversations> S save(S entity) {
		return conversationRespository.save(entity);
	}

	@Override
	public <S extends Conversations> List<S> saveAll(Iterable<S> entities) {
		return conversationRespository.saveAll(entities);
	}

	@Override
	public <S extends Conversations> Optional<S> findOne(Example<S> example) {
		return conversationRespository.findOne(example);
	}

	@Override
	public List<Conversations> findAll(Sort sort) {
		return conversationRespository.findAll(sort);
	}

	@Override
	public void flush() {
		conversationRespository.flush();
	}

	@Override
	public Page<Conversations> findAll(Pageable pageable) {
		return conversationRespository.findAll(pageable);
	}

	@Override
	public <S extends Conversations> S saveAndFlush(S entity) {
		return conversationRespository.saveAndFlush(entity);
	}

	@Override
	public <S extends Conversations> List<S> saveAllAndFlush(Iterable<S> entities) {
		return conversationRespository.saveAllAndFlush(entities);
	}

	@Override
	public List<Conversations> findAll() {
		return conversationRespository.findAll();
	}

	@Override
	public List<Conversations> findAllById(Iterable<Long> ids) {
		return conversationRespository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<Conversations> entities) {
		conversationRespository.deleteInBatch(entities);
	}

	@Override
	public <S extends Conversations> Page<S> findAll(Example<S> example, Pageable pageable) {
		return conversationRespository.findAll(example, pageable);
	}

	@Override
	public Optional<Conversations> findById(Long id) {
		return conversationRespository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Conversations> entities) {
		conversationRespository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return conversationRespository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		conversationRespository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Conversations> long count(Example<S> example) {
		return conversationRespository.count(example);
	}

	@Override
	public <S extends Conversations> boolean exists(Example<S> example) {
		return conversationRespository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		conversationRespository.deleteAllInBatch();
	}

	@Override
	public Conversations getOne(Long id) {
		return conversationRespository.getOne(id);
	}

	@Override
	public <S extends Conversations, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return conversationRespository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return conversationRespository.count();
	}

	@Override
	public void deleteById(Long id) {
		conversationRespository.deleteById(id);
	}

	@Override
	public Conversations getById(Long id) {
		return conversationRespository.getById(id);
	}

	@Override
	public void delete(Conversations entity) {
		conversationRespository.delete(entity);
	}

	@Override
	public Conversations getReferenceById(Long id) {
		return conversationRespository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		conversationRespository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Conversations> entities) {
		conversationRespository.deleteAll(entities);
	}

	@Override
	public <S extends Conversations> List<S> findAll(Example<S> example) {
		return conversationRespository.findAll(example);
	}

	@Override
	public <S extends Conversations> List<S> findAll(Example<S> example, Sort sort) {
		return conversationRespository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		conversationRespository.deleteAll();
	}

	@Autowired
	private ConversationRespository conversationRespository;
	
	

}
