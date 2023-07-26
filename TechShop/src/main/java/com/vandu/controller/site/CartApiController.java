package com.vandu.controller.site;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.vandu.dto.CartDto;
import com.vandu.dto.response.CartResponseDto;
import com.vandu.dto.response.ProductDetalisResponseDto;
import com.vandu.exception.DiscontinuedProductException;
import com.vandu.exception.ErrorDetails;
import com.vandu.exception.NotLoggedInException;
import com.vandu.exception.OutOfStockException;
import com.vandu.exception.ProductNotFoundException;
import com.vandu.helper.UserHelper;
import com.vandu.helper.Views;
import com.vandu.model.Cart;
import com.vandu.model.ProductDetails;
import com.vandu.model.User;
import com.vandu.service.CartService;
import com.vandu.service.ProductDetailsService;
import com.vandu.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("cart")
public class CartApiController {

	@Autowired
	private ProductDetailsService productDetailsService;

	@Autowired
	private CartService cartService;

	@Autowired
	private UserService userService;

	@Autowired
	private HttpServletRequest request;

	@PostMapping("insert")
	public ResponseEntity<?> insert(@RequestBody CartDto cartDto) throws Exception {

		try {
			ProductDetails productDetails = productDetailsService.findById(cartDto.getProductDetailsId())
					.orElseThrow(() -> new ProductNotFoundException());

			if (productDetails != null) {

				User user = UserHelper.getCurrentUser(request, userService);

				if (user == null) {
					throw new NotLoggedInException();
				}

				if (!productDetails.getProductVersion().getProduct().isAvailable()) {
					throw new DiscontinuedProductException("Sản phẩm đã ngừng kinh doanh!");
				}

				Cart cart = new Cart();
				cart.setCreateDate(new Date());
				cart.setQuantity(cartDto.getQuantity());
//				cart.setTotalPrice(productDetails.getPrice() * cartDto.getQuantity());
				cart.setProductDetails(productDetails);
				cart.setUser(user);
				cart.setSelectedItems(false);
				cartService.addToCart(cart);
				return ResponseEntity.ok(cartDto);
			}

		} catch (NotLoggedInException e) {
			throw e;
		} catch (DiscontinuedProductException e) {
			throw e;
		}

		return ResponseEntity.badRequest().build();

	}

	@GetMapping("getAllCart")
//	@JsonView(Views.Public.class)
	public ResponseEntity<?> getAllCart() throws NotLoggedInException {
		try {

			User user = UserHelper.getCurrentUser(request, userService);

			if (user == null) {
				throw new NotLoggedInException();
			}
			List<Cart> listCart = cartService.getAllCart(user.getUserId());

			List<CartResponseDto> listCartRes = new ArrayList<>();

			if (listCart != null) {
				listCartRes = listCart.stream().map(item -> {
					
					return cartService.convertCartResponseDto(item);
				}).toList();
			}
			return ResponseEntity.ok(listCartRes);
		} catch (NotLoggedInException e) {
			throw e;
		}

	}

	@GetMapping("getSelectedCarts")
//	@JsonView(Views.Public.class)
	public ResponseEntity<?> getSelectedCarts() throws NotLoggedInException {
		try {

			User user = UserHelper.getCurrentUser(request, userService);

			if (user == null) {
				throw new NotLoggedInException();
			}
			
			List<Cart> listCart = cartService.getSelectedCarts(user.getUserId(), true);
			
			List<CartResponseDto> listCartResponseDtos = new ArrayList<>();
			
			if(listCart!=null) {
				listCartResponseDtos = listCart.stream().map(item->{
					return cartService.convertCartResponseDto(item);
				}).toList();
			}
			
			return ResponseEntity.ok(listCartResponseDtos);
		} catch (NotLoggedInException e) {
			throw e;
		}

	}

	@PutMapping("updateQuantity/{cartId}/{quantity}")
	public ResponseEntity<?> updateQuantity(@PathVariable("cartId") Long cartId,
			@PathVariable("quantity") Long quantity) throws NotLoggedInException, OutOfStockException {

		try {
			User user = UserHelper.getCurrentUser(request, userService);

			if (user == null) {
				throw new NotLoggedInException();
			}

			Cart cart = cartService.findById(cartId).orElse(null);

			if (quantity != null) {

				Long remainingAmount = cart.getProductDetails().getQuantity() - quantity;
				if (remainingAmount < 0) {
					throw new OutOfStockException("Số lượng đã vượt quá số lượng sản phẩm trong kho!");
				}

				cart.setQuantity(quantity);
//				cart.setTotalPrice(cart.getProductDetails().getPrice() * cart.getQuantity());
				cartService.save(cart);
			}

			CartDto cartDto = new CartDto();

			BeanUtils.copyProperties(cart, cartDto);

			return ResponseEntity.ok(cartDto);
		} catch (NotLoggedInException e) {
			throw e;
		} catch (OutOfStockException e) {
			throw e;
		}
	}

	@DeleteMapping("deleteCart/{cartId}")
	public ResponseEntity<?> deleteCart(@PathVariable("cartId") Long cartId) {
		cartService.delete(cartService.findById(cartId).get());

		return ResponseEntity.ok("Success");
	}

	@PutMapping("updateSelectedItems/{cartId}")
	public ResponseEntity<?> updateSelectedItems(@PathVariable("cartId") Long cartId) {
		Cart cart = cartService.findById(cartId).orElse(null);

		if (cart != null) {
			cart.setSelectedItems(!cart.getSelectedItems());
			cartService.save(cart);
		}

		return ResponseEntity.ok("success");
	}

}
