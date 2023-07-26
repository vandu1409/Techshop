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

import com.vandu.model.Address;
import com.vandu.repository.AddressRepository;
import com.vandu.service.AddressService;
import com.vandu.service.UserService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserService userService;

	@Override
	public void changeDefaultAddressesToRegular(Long uid) {
		Address address = getDefaultAddress(uid).orElse(null);
		
		if(address!=null) {
			address.setIsActive(false);
			addressRepository.save(address);
		}
	}

	@Override
	public List<Address> findAllByUser(Long uid, boolean isDetele) {
		return addressRepository.findAllByUser(uid, isDetele);
	}

	@Override
	public List<Address> findAllByUser(Long uid) {
		return addressRepository.findAllByUser(uid);
	}

	@Override
	public long count() {
		return addressRepository.count();
	}

	@Override
	public <S extends Address> long count(Example<S> example) {
		return addressRepository.count(example);
	}

	@Override
	public Optional<Address> getDefaultAddress(Long uid) {
		return addressRepository.getDefaultAddress(uid);
	}

	@Override
	public void delete(Address entity) {
		addressRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		addressRepository.deleteAll();
	}

	@Override
	public void deleteAll(Iterable<? extends Address> entities) {
		addressRepository.deleteAll(entities);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		addressRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		addressRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void deleteAllInBatch() {
		addressRepository.deleteAllInBatch();
	}

	@Override
	public void deleteAllInBatch(Iterable<Address> entities) {
		addressRepository.deleteAllInBatch(entities);
	}

	@Override
	public void deleteById(Long id) {
		addressRepository.deleteById(id);
	}

	@Override
	public void deleteInBatch(Iterable<Address> entities) {
		addressRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Address> boolean exists(Example<S> example) {
		return addressRepository.exists(example);
	}

	@Override
	public boolean existsById(Long id) {
		return addressRepository.existsById(id);
	}

	@Override
	public List<Address> findAll() {
		return addressRepository.findAll();
	}

	@Override
	public <S extends Address> Page<S> findAll(Example<S> example, Pageable pageable) {
		return addressRepository.findAll(example, pageable);
	}

	@Override
	public <S extends Address> List<S> findAll(Example<S> example, Sort sort) {
		return addressRepository.findAll(example, sort);
	}

	@Override
	public <S extends Address> List<S> findAll(Example<S> example) {
		return addressRepository.findAll(example);
	}

	@Override
	public Page<Address> findAll(Pageable pageable) {
		return addressRepository.findAll(pageable);
	}

	@Override
	public List<Address> findAll(Sort sort) {
		return addressRepository.findAll(sort);
	}

	@Override
	public List<Address> findAllById(Iterable<Long> ids) {
		return addressRepository.findAllById(ids);
	}

	@Override
	public <S extends Address, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return addressRepository.findBy(example, queryFunction);
	}

	@Override
	public Optional<Address> findById(Long id) {
		return addressRepository.findById(id);
	}

	@Override
	public <S extends Address> Optional<S> findOne(Example<S> example) {
		return addressRepository.findOne(example);
	}

	@Override
	public void flush() {
		addressRepository.flush();
	}

	@Override
	public Address getById(Long arg0) {
		return addressRepository.getById(arg0);
	}

	@Override
	public Address getOne(Long arg0) {
		return addressRepository.getOne(arg0);
	}

	@Override
	public Address getReferenceById(Long id) {
		return addressRepository.getReferenceById(id);
	}

	@Override
	public <S extends Address> S save(S entity) {
		return addressRepository.save(entity);
	}

	@Override
	public <S extends Address> List<S> saveAll(Iterable<S> entities) {
		return addressRepository.saveAll(entities);
	}

	@Override
	public <S extends Address> List<S> saveAllAndFlush(Iterable<S> entities) {
		return addressRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Address> S saveAndFlush(S entity) {
		return addressRepository.saveAndFlush(entity);
	}

}
