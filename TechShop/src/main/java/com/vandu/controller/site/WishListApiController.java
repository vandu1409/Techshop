package com.vandu.controller.site;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.vandu.dto.WishListDto;
import com.vandu.dto.response.ProductResponseDto;
import com.vandu.dto.response.WishlitResponeDto;
import com.vandu.exception.NotLoggedInException;
import com.vandu.helper.UserHelper;
import com.vandu.model.Product;
import com.vandu.model.User;
import com.vandu.model.WishList;
import com.vandu.service.UserService;
import com.vandu.service.WishListService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class WishListApiController {
	
	@Autowired
	private WishListService wishListService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("addToWishlist")
	public ResponseEntity<?> addToWishlist(@RequestBody WishListDto wishListDto) throws NotLoggedInException{
		
		try {
			User user = UserHelper.getCurrentUser(request, userService);
			
			if (user == null) {
				throw new NotLoggedInException();
			}
			
			WishList dbWishList = wishListService.findByUserAndProduct(user.getUserId(), wishListDto.getProductId()).orElse(null);
			
			if(dbWishList!=null) {
				wishListService.deleteWishlist(wishListDto.getProductId(), user);
				
				return ResponseEntity.ok("Đã xóa khỏi danh sách yêu thích");
			}
			
			WishList wishList = new WishList();
			
			BeanUtils.copyProperties(wishListDto, wishList);
		
			
			

			
			
			Product product = new Product();
			product.setProductId(wishListDto.getProductId());
			
			wishList.setUser(user);
			wishList.setProduct(product);
			
			BeanUtils.copyProperties(wishListService.save(wishList), wishListDto);
			
			return ResponseEntity.ok("Đã thêm vào danh sách yêu thích!");
		} catch (NotLoggedInException e) {
			throw e;
		}
		
	}
	
	@DeleteMapping("delete-wishlist/{id}")
	public ResponseEntity<?> deleteWishlist(@PathVariable("id")Long id){
//		User user = UserHelper.getCurrentUser(request, userService);
		wishListService.deleteById(id);
//		wishListService.deleteWishlist(id, user);
		
		return ResponseEntity.ok("success");
	}
	
	@GetMapping("wishlist/getCurrentUser")
	public ResponseEntity<?> getCurentUser(){
		User user = UserHelper.getCurrentUser(request, userService);
		
		List<WishlitResponeDto> list = wishListService.findByUser(user.getUserId()).stream().map(item->{
			WishlitResponeDto wishlitResponeDto = new WishlitResponeDto();
			
			BeanUtils.copyProperties(item, wishlitResponeDto);
			wishlitResponeDto.setProduct(new ProductResponseDto());
			
			
			BeanUtils.copyProperties(item.getProduct(), wishlitResponeDto.getProduct());
			wishlitResponeDto.getProduct().setCategoryCode(item.getProduct().getCategoryCode());
			wishlitResponeDto.getProduct().setDiscountedPrice(item.getProduct().getDiscountedPrice());
			
		
			
			return wishlitResponeDto;
		}).toList();
		
		return ResponseEntity.ok(list);
	}
}
