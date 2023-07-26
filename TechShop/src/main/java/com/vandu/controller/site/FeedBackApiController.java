package com.vandu.controller.site;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vandu.dto.FeedBackDto;
import com.vandu.exception.DuplicateDataException;
import com.vandu.helper.UserHelper;
import com.vandu.model.FeedBack;
import com.vandu.model.FeedBackImage;
import com.vandu.model.OrderItem;
import com.vandu.model.Product;
import com.vandu.model.User;
import com.vandu.service.FeatureService;
import com.vandu.service.FeedBackImageService;
import com.vandu.service.FeedBackService;
import com.vandu.service.FileSystemStorageService;
import com.vandu.service.OrderItemService;
import com.vandu.service.UserService;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@MultipartConfig
public class FeedBackApiController {

	
	@Autowired
	private FeedBackService feedBackService;
	
	@Autowired
	private FeedBackImageService feedBackImageService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private FileSystemStorageService fileSystemStorageService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpServletRequest request;
	@PostMapping("addFeedBack")
	public ResponseEntity<?> addFeedBack(@ModelAttribute FeedBackDto feedBackDto){
		
		try {
			User user = UserHelper.getCurrentUser(request, userService);
			
			FeedBack feedBack = new FeedBack();
			
			BeanUtils.copyProperties(feedBackDto, feedBack);
			
			feedBack.setUser(user);
			feedBack.setFeedBackDate(new Date());
			
//			feedBack.setApproved(false);
			
			OrderItem orderItem = orderItemService.findById(feedBackDto.getOrderItemId()).orElse(null);
			
			if(orderItem!=null) {
				
				if(orderItem.getFeedBack()) {
					throw new DuplicateDataException("Sản phẩm này đã được đánh giá!");
				}
				
				orderItem.setFeedBack(true);
				orderItemService.save(orderItem);
				
				Product product = new Product();
				product.setProductId(orderItem.getProductDetails().getProductVersion().getProduct().getProductId());
				feedBack.setProduct(product);
				
				String name = orderItem.getProductDetails().getProductVersion().getVersionName() + " - " +orderItem.getProductDetails().getColor().getName();
				
				feedBack.setProductBought(name);
			}
			
			FeedBack dbFeedBack = feedBackService.save(feedBack);
			
			
			if(feedBackDto.getImages()!=null && feedBackDto.getImages().size() >0) {
				feedBackDto.getImages().stream().forEach(item->{
					
					FeedBackImage feedBackImage = new FeedBackImage();
					
					UUID uuid =UUID.randomUUID();
					
					String name = fileSystemStorageService.getStorageFileName(item, uuid.toString());
					fileSystemStorageService.saveFile(item, name);
					
					feedBackImage.setImage(name);
					feedBackImage.setFeedBack(dbFeedBack);
					
					feedBackImageService.save(feedBackImage);
				});
			}
			
		
			
			
			return ResponseEntity.ok("Success");
		} catch (DuplicateDataException e) {
			throw e;
		}
	}
	
	
//	@GetMapping("getAll")
//	public ResponseEntity<?> 
//	
	
}
