package com.vandu.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.vandu.enums.DiscountType;
import com.vandu.helper.Views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonView(Views.Public.class)
public class ProductVersion implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productVersionId;
	
	@Column(columnDefinition = "nvarchar(500)")
	private String versionName;
	
	private Double price;
	
	
//	private boolean isAvailable;
	
	@ManyToOne
	@JoinColumn(name = "productId")
	@JsonManagedReference
	private Product product;
	
	@OneToMany(mappedBy = "productVersion")
	@JsonBackReference
	private List<ProductDetails> productDetails; 
	
	@OneToMany(mappedBy = "productVersion")
	@JsonManagedReference
	private List<VersionAttributes> versionAttributes;
	
	public Double getDiscountedPrice() {
		Double discountedPrice = this.getPrice(); // Lấy giá gốc của sản phẩm
		LocalDate today = LocalDate.now();
		for (ProductDiscount pd : this.getProduct().getProductDiscounts()) {
			
			if (!today.isBefore(pd.getDiscount().getStartDate()) && !today.isAfter(pd.getDiscount().getEndDate())) {
				
				if (pd.getDiscount().getDiscountType().equals(DiscountType.DISCOUNTBYPERCENT)) {
					discountedPrice = discountedPrice - (discountedPrice * (pd.getDiscount().getDiscountValue() / 100));
					
					
					
				} else {
					discountedPrice = discountedPrice - pd.getDiscount().getDiscountValue();
				}
			}
		}

		return discountedPrice;

	}
	
	public Long getTotalOrder() {
		return this.getProductDetails().stream().mapToLong(ProductDetails::getQuantitySold).sum();
	}
	
	public Long getTotalQuantity() {
		return this.getProductDetails().stream().mapToLong(ProductDetails::getQuantity).sum();
	}

}
