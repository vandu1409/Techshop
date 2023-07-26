package com.vandu.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.vandu.dto.VoucherApplicationResultDTO;
import com.vandu.dto.VoucherDto;
import com.vandu.model.Voucher;

public interface VoucherService {

	void deleteAll();

	<S extends Voucher> List<S> findAll(Example<S> example, Sort sort);

	<S extends Voucher> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends Voucher> entities);

	void deleteAllById(Iterable<? extends Long> ids);

	Voucher getReferenceById(Long id);

	void delete(Voucher entity);

	Voucher getById(Long id);

	void deleteById(Long id);

	long count();

	<S extends Voucher, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	Voucher getOne(Long id);

	void deleteAllInBatch();

	<S extends Voucher> boolean exists(Example<S> example);

	<S extends Voucher> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	boolean existsById(Long id);

	void deleteAllInBatch(Iterable<Voucher> entities);

	Optional<Voucher> findById(Long id);

	<S extends Voucher> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<Voucher> entities);

	List<Voucher> findAllById(Iterable<Long> ids);

	List<Voucher> findAll();

	<S extends Voucher> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Voucher> S saveAndFlush(S entity);

	Page<Voucher> findAll(Pageable pageable);

	void flush();

	List<Voucher> findAll(Sort sort);

	<S extends Voucher> Optional<S> findOne(Example<S> example);

	<S extends Voucher> List<S> saveAll(Iterable<S> entities);

	<S extends Voucher> S save(S entity);


	VoucherDto findVoucherDtoById(Long id);

	Page<Voucher> findExpiredVouchers(Pageable pageable);

	Page<Voucher> findValidVouchers(Pageable pageable);

	Page<Voucher> findByStatus(Pageable pageable, int status);

	List<Voucher> findValidVouchers();

	VoucherApplicationResultDTO applyVoucher(Long voucherId, Long uid);


}
