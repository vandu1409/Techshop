package com.vandu.service.Impl;

import java.time.LocalDate;
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

import com.vandu.enums.PaymentMethod;
import com.vandu.enums.PaymentStatus;
import com.vandu.model.Order;
import com.vandu.model.Payments;
import com.vandu.repository.PaymentsRepository;
import com.vandu.service.PaymentsService;

@Service
public class PaymentsServiceImpl implements PaymentsService{

	@Override
	public Optional<Payments> findByOrder(Long orderId) {
		return paymentsRepository.findByOrder(orderId);
	}

	@Autowired
	private PaymentsRepository paymentsRepository;

	
	@Override
	public Payments addToPayment(Order DBOrder,PaymentMethod paymentMethod,PaymentStatus paymentStatus) {
		Payments payments = new Payments();
		payments.setOrder(DBOrder);
		payments.setTotalPrice(DBOrder.getTotalPrice());
		LocalDate currentDate = LocalDate.now();
		payments.setCreateDate(currentDate);
		payments.setPaymentMethod(paymentMethod);
		payments.setPaymentStatus(paymentStatus);
		
		return save(payments);
	}
	@Override
	public <S extends Payments> S save(S entity) {
		return paymentsRepository.save(entity);
	}

	@Override
	public <S extends Payments> List<S> saveAll(Iterable<S> entities) {
		return paymentsRepository.saveAll(entities);
	}

	@Override
	public <S extends Payments> Optional<S> findOne(Example<S> example) {
		return paymentsRepository.findOne(example);
	}

	@Override
	public List<Payments> findAll(Sort sort) {
		return paymentsRepository.findAll(sort);
	}

	@Override
	public void flush() {
		paymentsRepository.flush();
	}

	@Override
	public Page<Payments> findAll(Pageable pageable) {
		return paymentsRepository.findAll(pageable);
	}

	@Override
	public <S extends Payments> S saveAndFlush(S entity) {
		return paymentsRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Payments> List<S> saveAllAndFlush(Iterable<S> entities) {
		return paymentsRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<Payments> findAll() {
		return paymentsRepository.findAll();
	}

	@Override
	public List<Payments> findAllById(Iterable<Long> ids) {
		return paymentsRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<Payments> entities) {
		paymentsRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Payments> Page<S> findAll(Example<S> example, Pageable pageable) {
		return paymentsRepository.findAll(example, pageable);
	}

	@Override
	public Optional<Payments> findById(Long id) {
		return paymentsRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Payments> entities) {
		paymentsRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return paymentsRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		paymentsRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Payments> long count(Example<S> example) {
		return paymentsRepository.count(example);
	}

	@Override
	public <S extends Payments> boolean exists(Example<S> example) {
		return paymentsRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		paymentsRepository.deleteAllInBatch();
	}

	@Override
	public Payments getOne(Long id) {
		return paymentsRepository.getOne(id);
	}

	@Override
	public <S extends Payments, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return paymentsRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return paymentsRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		paymentsRepository.deleteById(id);
	}

	@Override
	public Payments getById(Long id) {
		return paymentsRepository.getById(id);
	}

	@Override
	public void delete(Payments entity) {
		paymentsRepository.delete(entity);
	}

	@Override
	public Payments getReferenceById(Long id) {
		return paymentsRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		paymentsRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Payments> entities) {
		paymentsRepository.deleteAll(entities);
	}

	@Override
	public <S extends Payments> List<S> findAll(Example<S> example) {
		return paymentsRepository.findAll(example);
	}

	@Override
	public <S extends Payments> List<S> findAll(Example<S> example, Sort sort) {
		return paymentsRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		paymentsRepository.deleteAll();
	}
	
	
}
