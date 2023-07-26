package com.vandu.controller.admin;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vandu.dto.ProductDetailsDto;
import com.vandu.model.Color;
import com.vandu.model.ProductDetails;
import com.vandu.model.ProductVersion;
import com.vandu.service.FileSystemStorageService;
import com.vandu.service.ProductDetailsService;

import jakarta.servlet.annotation.MultipartConfig;

@RestController
@RequestMapping("admin/product-details-management")
@MultipartConfig
public class ProductDetailsApiController {

	@Autowired
	private ProductDetailsService productDetailsService;

	@Autowired
	private FileSystemStorageService fileSystemStorageService;

	@PostMapping("saveOrUpdate")
	public ResponseEntity<ProductDetailsDto> saveOrUpdate(@ModelAttribute ProductDetailsDto productDetailsDto) {
		ProductDetails productDetails = new ProductDetails();

		System.err.println(productDetailsDto.toString());
		BeanUtils.copyProperties(productDetailsDto, productDetails);
		productDetails.setCreateDate(new Date());
		Color color = new Color();

		if (productDetailsDto.getFileImgae() != null) {
			System.err.println("đã vào thêm image");
			UUID uuid = UUID.randomUUID();
			String name = fileSystemStorageService.getStorageFileName(productDetailsDto.getFileImgae(),
					uuid.toString());
			
			fileSystemStorageService.saveFile(productDetailsDto.getFileImgae(), name);
			
			productDetails.setImage(name);
		} else {
			if (productDetailsDto.getProductDetailsId() != null) {
				ProductDetails currentProductDetails = productDetailsService
						.findById(productDetailsDto.getProductDetailsId()).orElse(null);
				
				if(currentProductDetails!=null) {
					productDetails.setImage(currentProductDetails.getImage());
				}
			}
		}

		if (productDetailsDto.getProductDetailsId() != null) {
			productDetails.setUpdateDate(new Date());
		}

		if (productDetailsDto.getColorId() != null) {
			color.setId(productDetailsDto.getColorId());
			System.err.println(color.getId() + "color Id");
			productDetails.setColor(color);
		} else {

			if (productDetailsDto.getProductDetailsId() != null) {
				ProductDetails currentProductDetails = productDetailsService
						.findById(productDetailsDto.getProductDetailsId()).orElse(null);

				if (currentProductDetails != null) {
					color.setId(currentProductDetails.getColor().getId());
					productDetails.setColor(color);
				}
			}

		}

		if (productDetailsDto.getProductVersionId() != null) {
			ProductVersion productVersion = new ProductVersion();
			productVersion.setProductVersionId(productDetailsDto.getProductVersionId());

			productDetails.setProductVersion(productVersion);
		}

		ProductDetails entity = productDetailsService.save(productDetails);

		BeanUtils.copyProperties(entity, productDetailsDto);

		return ResponseEntity.ok(productDetailsDto);

	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		ProductDetails productDetails = productDetailsService.findById(id).orElse(new ProductDetails());

		if (productDetails != null) {
			productDetailsService.delete(productDetails);
		}

		return ResponseEntity.ok("Success");
	}

	@GetMapping("find/{id}")
	public ResponseEntity<ProductDetailsDto> findById(@PathVariable("id") Long id) {
		ProductDetails productDetails = productDetailsService.findById(id).orElse(new ProductDetails());

		ProductDetailsDto productDetailsDto = new ProductDetailsDto();

		BeanUtils.copyProperties(productDetails, productDetailsDto);

		return ResponseEntity.ok(productDetailsDto);
	}

	@GetMapping("getAll/{versionId}")
	public ResponseEntity<Page<ProductDetails>> getAll(@PathVariable("versionId") Long id,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
			@RequestParam("keyword") String keyword) {

		System.err.println("ddda vào" + id);

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(3);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);

		Page<ProductDetails> list = keyword.equals("") ? productDetailsService.findByVersion(id, pageable)
				: productDetailsService.findByVersion(id, keyword, pageable);

		list.stream().forEach(item -> {
			System.err.println(item.getPrice());
		});

		return ResponseEntity.ok(list);

	}

}
