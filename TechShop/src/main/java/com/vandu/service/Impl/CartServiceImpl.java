package com.vandu.service.Impl;

import java.time.LocalDate;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vandu.dto.VoucherApplicationResultDTO;
import com.vandu.dto.response.CartResponseDto;
import com.vandu.dto.response.ProductDetalisResponseDto;
import com.vandu.enums.DiscountType;
import com.vandu.exception.OutOfStockException;
import com.vandu.model.Cart;
import com.vandu.model.ProductDetails;
import com.vandu.model.Voucher;
import com.vandu.repository.CartRepository;
import com.vandu.service.CartService;
import com.vandu.service.ProductDetailsService;

import jakarta.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductDetailsService productDetailsService;

	@Override
	public long count() {
		return cartRepository.count();
	}

	

	@Override
	public List<Cart> getSelectedCarts(Long uid,Boolean selectedItems) {
		return cartRepository.getSelectedCarts(uid,selectedItems);
	}

	@Override
	public CartResponseDto convertCartResponseDto(Cart item) {
		CartResponseDto cartResponseDto = new CartResponseDto();

		BeanUtils.copyProperties(item, cartResponseDto);
		cartResponseDto.setProductDetalis(new ProductDetalisResponseDto());
		BeanUtils.copyProperties(item.getProductDetails(), cartResponseDto.getProductDetalis());
		cartResponseDto.getProductDetalis().setImportPrice(null);
		String productName = item.getProductDetails().getProductVersion().getProduct().getName() + " - "
				+ item.getProductDetails().getProductVersion().getVersionName() + " -Màu : "
				+ item.getProductDetails().getColor().getName();
		cartResponseDto.setProductName(productName);
		
		return cartResponseDto;
	}
	@Override
	@Transactional
	public void deleteCartAll(Long uid,Boolean selectedItems) {
		cartRepository.deleteCartAll(uid,selectedItems);
	}

	@Override
	public List<Cart> getAllCart(Long uid) {
		return cartRepository.getAllCart(uid);
	}

	@Override
	public Optional<Cart> findByUserAndProductDetails(Long uid, Long pid) {
		return cartRepository.findByUserAndProductDetails(uid, pid);
	}


	@Override
	public Cart addToCart(Cart cart) throws Exception{
		
			Cart currentCart = findByUserAndProductDetails(cart.getUser().getUserId(),
					cart.getProductDetails().getProductDetailsId()).orElse(null);

		

			if (currentCart != null) {
				ProductDetails productDetails = currentCart.getProductDetails();
				
				currentCart.setQuantity(currentCart.getQuantity() + cart.getQuantity());
//				currentCart.setTotalPrice(cart.getProductDetails().getPrice() * currentCart.getQuantity());

//				productDetails.setQuantity(productDetails.getQuantity() - cart.getQuantity());

				if (productDetails.getQuantity() < 0) {
					throw new OutOfStockException("Sản phẩm đã hết hàng");
				}

				productDetailsService.save(productDetails);

				return cartRepository.save(currentCart);
			} else {
				ProductDetails productDetails = cart.getProductDetails();
				
//				productDetails.setQuantity(productDetails.getQuantity() - cart.getQuantity());
				productDetailsService.save(productDetails);
			}

			return cartRepository.save(cart);
		

	}

	@Override
	public <S extends Cart> long count(Example<S> example) {
		return cartRepository.count(example);
	}

	@Override
	public void delete(Cart entity) {
		cartRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		cartRepository.deleteAll();
	}

	@Override
	public void deleteAll(Iterable<? extends Cart> entities) {
		cartRepository.deleteAll(entities);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		cartRepository.deleteAllById(ids);
	}

	@Override
	public <S extends Cart> S saveAndFlush(S entity) {
		return cartRepository.saveAndFlush(entity);
	}

	@Override
	public void deleteInBatch(Iterable<Cart> entities) {
		cartRepository.deleteInBatch(entities);
	}

	@Override
	public void deleteAllInBatch(Iterable<Cart> entities) {
		cartRepository.deleteAllInBatch(entities);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		cartRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void deleteAllInBatch() {
		cartRepository.deleteAllInBatch();
	}

	@Override
	public void deleteById(Long id) {
		cartRepository.deleteById(id);
	}

	@Override
	public <S extends Cart> boolean exists(Example<S> example) {
		return cartRepository.exists(example);
	}

	@Override
	public boolean existsById(Long id) {
		return cartRepository.existsById(id);
	}

	@Override
	public List<Cart> findAll() {
		return cartRepository.findAll();
	}

	@Override
	public <S extends Cart> Page<S> findAll(Example<S> example, Pageable pageable) {
		return cartRepository.findAll(example, pageable);
	}

	@Override
	public <S extends Cart> List<S> findAll(Example<S> example) {
		return cartRepository.findAll(example);
	}

	@Override
	public <S extends Cart> List<S> findAll(Example<S> example, Sort sort) {
		return cartRepository.findAll(example, sort);
	}

	@Override
	public Page<Cart> findAll(Pageable pageable) {
		return cartRepository.findAll(pageable);
	}

	@Override
	public List<Cart> findAll(Sort sort) {
		return cartRepository.findAll(sort);
	}

	@Override
	public List<Cart> findAllById(Iterable<Long> ids) {
		return cartRepository.findAllById(ids);
	}

	@Override
	public <S extends Cart, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return cartRepository.findBy(example, queryFunction);
	}

	@Override
	public Optional<Cart> findById(Long id) {
		return cartRepository.findById(id);
	}

	@Override
	public <S extends Cart> Optional<S> findOne(Example<S> example) {
		return cartRepository.findOne(example);
	}

	@Override
	public void flush() {
		cartRepository.flush();
	}

	@Override
	public <S extends Cart> List<S> saveAllAndFlush(Iterable<S> entities) {
		return cartRepository.saveAllAndFlush(entities);
	}

	@Override
	public Cart getOne(Long id) {
		return cartRepository.getOne(id);
	}

	@Override
	public Cart getById(Long id) {
		return cartRepository.getById(id);
	}

	@Override
	public Cart getReferenceById(Long id) {
		return cartRepository.getReferenceById(id);
	}

	@Override
	public <S extends Cart> S save(S entity) {
		return cartRepository.save(entity);
	}

	@Override
	public <S extends Cart> List<S> saveAll(Iterable<S> entities) {
		return cartRepository.saveAll(entities);
	}

}
