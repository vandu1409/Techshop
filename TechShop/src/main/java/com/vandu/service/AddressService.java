package com.vandu.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.vandu.model.Address;

public interface AddressService {

	<S extends Address> S saveAndFlush(S entity);

	<S extends Address> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Address> List<S> saveAll(Iterable<S> entities);

	<S extends Address> S save(S entity);

	Address getReferenceById(Long id);

	Address getOne(Long arg0);

	Address getById(Long arg0);

	void flush();

	<S extends Address> Optional<S> findOne(Example<S> example);

	Optional<Address> findById(Long id);

	<S extends Address, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	List<Address> findAllById(Iterable<Long> ids);

	List<Address> findAll(Sort sort);

	Page<Address> findAll(Pageable pageable);

	<S extends Address> List<S> findAll(Example<S> example);

	<S extends Address> List<S> findAll(Example<S> example, Sort sort);

	<S extends Address> Page<S> findAll(Example<S> example, Pageable pageable);

	List<Address> findAll();

	boolean existsById(Long id);

	<S extends Address> boolean exists(Example<S> example);

	void deleteInBatch(Iterable<Address> entities);

	void deleteById(Long id);

	void deleteAllInBatch(Iterable<Address> entities);

	void deleteAllInBatch();

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteAllById(Iterable<? extends Long> ids);

	void deleteAll(Iterable<? extends Address> entities);

	void deleteAll();

	void delete(Address entity);

	<S extends Address> long count(Example<S> example);

	long count();

	Optional<Address> getDefaultAddress(Long uid);

	List<Address> findAllByUser(Long uid);

	void changeDefaultAddressesToRegular(Long uid);

	List<Address> findAllByUser(Long uid, boolean isDetele);

}
