package com.vandu.controller.admin;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vandu.dto.ProductDiscountDto;
import com.vandu.exception.DiscountException;
import com.vandu.exception.DuplicateDataException;
import com.vandu.model.Discount;
import com.vandu.model.Product;
import com.vandu.model.ProductDiscount;
import com.vandu.service.ProductDiscountService;
import com.vandu.service.ProductService;

@RestController
@RequestMapping("admin/product-discount")
public class ProductDiscountApiController {

	@Autowired
	private ProductDiscountService productDiscountService;
	
	@Autowired
	private ProductService productService;

	@PostMapping("saveOrUpdate")
	public ResponseEntity<ProductDiscountDto> saveOrUpdate(@RequestBody ProductDiscountDto productDiscountDto) {

		try {
			if (productDiscountDto.getProductId() != null && productDiscountDto.getDiscountId() != null) {
				ProductDiscount productDiscount = productDiscountService
						.findByProductAndDiscount(productDiscountDto.getProductId(), productDiscountDto.getDiscountId());
				
				if(productDiscount!=null) {
					throw new DuplicateDataException("Không thể áp dụng 1 mã giảm giá 2 lần!");
				}
			}
			
			if(productDiscountDto.getProductId()!=null) {
				Product dbProduct = productService.findById(productDiscountDto.getProductId()).orElse(null);
				
				if(dbProduct!=null) {
					if(dbProduct.getDiscounts().size()==2) {
						throw new DiscountException("Mỗi sản phẩm chỉ được áp dụng 2 giảm giá!");
					}
				}
			}

			Product product = new Product();
			product.setProductId(productDiscountDto.getProductId());

			Discount discount = new Discount();
			discount.setId(productDiscountDto.getDiscountId());

			ProductDiscount productDiscount = new ProductDiscount();
			productDiscount.setDiscount(discount);
			productDiscount.setProduct(product);

			ProductDiscount entity = productDiscountService.save(productDiscount);

			BeanUtils.copyProperties(entity, productDiscountDto);

			return ResponseEntity.ok(productDiscountDto);
		} catch (DuplicateDataException e) {
			throw e;
		}catch (DiscountException e) {
			throw e;
		}

	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		productDiscountService.deleteById(id);

		return ResponseEntity.ok("Success");
	}

	@DeleteMapping("deleteByProductAndDiscount/{pid}/{did}")
	public ResponseEntity<?> deleteByProductAndDiscount(@PathVariable("pid") Long pid, @PathVariable("did") Long did) {
		productDiscountService.deleteByProductAndDiscount(pid, did);

		return ResponseEntity.ok("Success");
	}

}
