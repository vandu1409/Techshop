package com.vandu.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonView(Views.Public.class)
public class Product implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;

	@Column(columnDefinition = "nvarchar(500)")
	private String name;

	private Double price;

	private Date createDate;

	private Date updateDate;

	@Column(columnDefinition = "nvarchar(500)")
	private String image;

	private boolean isAvailable;

	@Column(columnDefinition = "nvarchar(500)", unique = true)
	private String productCode;

	@ManyToOne
	@JoinColumn(name = "productSeriesId")
	@JsonIgnore
	private ProductSeries productSeries;

	@OneToMany(mappedBy = "product")
	@JsonBackReference
	List<ProductVersion> productVersions;

	@OneToMany(mappedBy = "product")
	@JsonIgnore
	private List<ProductDiscount> productDiscounts;

	@OneToMany(mappedBy = "product")
//	@JsonIgnore
	private List<ProductImage> productImages;

	@ManyToOne
	@JoinColumn(name = "featureId")
	private Feature feature;

	@ManyToOne
	@JoinColumn(name = "categoryId")
	@JsonIgnore
	private Category category;

	@OneToMany(mappedBy = "product")
	@JsonIgnore
	private List<WishList> wishLists;

	@OneToMany(mappedBy = "product")
	private List<ProductAttributes> productAttributes;

	@OneToMany(mappedBy = "product")
	private List<Description> descriptions;

	public String getCategoryCode() {
		return this.getCategory().getCategoryCode();
	}

	public Double getDiscountedPrice() {
		Double discountedPrice = this.getPrice(); // Lấy giá gốc của sản phẩm
		LocalDate today = LocalDate.now();
		for (ProductDiscount pd : this.getProductDiscounts()) {

			if (!today.isBefore(pd.getDiscount().getStartDate()) && !today.isAfter(pd.getDiscount().getEndDate())) {
				if (this.getPrice() >= pd.getDiscount().getAmountApplied()) {
					Double discountAmount = 0.0;

					if (pd.getDiscount().getDiscountType().equals(DiscountType.DISCOUNTBYPERCENT)) {
						discountAmount = discountedPrice * (pd.getDiscount().getDiscountValue() / 100);

					} else {
						discountAmount = pd.getDiscount().getDiscountValue();
					}

					if (discountAmount > pd.getDiscount().getMaxValue()) {
						discountAmount = pd.getDiscount().getMaxValue();
					}

					discountedPrice = discountedPrice - discountAmount;
				}
			}
		}

		return discountedPrice;

	}

	public List<Discount> getDiscounts() {
		List<Discount> list = new ArrayList<>();

		for (ProductDiscount pd : this.getProductDiscounts()) {
			Discount discount = pd.getDiscount();
			Double discountAmount = 0.0;

			if (this.getPrice() >= discount.getAmountApplied()) {
				if (pd.getDiscount().getDiscountType().equals(DiscountType.DISCOUNTBYPERCENT)) {
					discountAmount = this.getPrice() * (discount.getDiscountValue() / 100);
				} else {
					discountAmount = discount.getDiscountValue();
				}
			}

			if (discountAmount > discount.getMaxValue()) {
				discountAmount = discount.getMaxValue();
			}

			discount.setDiscountAmount(discountAmount);

			list.add(pd.getDiscount());

		}

		return list;
	}

	public Long getTotalOrder() {
		
		if(this.getProductVersions()!=null) {
			return this.getProductVersions().stream().mapToLong(ProductVersion::getTotalOrder).sum();
		}
		
		
		return 0l;
	}

	public Long getTotalQuantity() {
		if(this.getProductVersions()!=null) {
			return this.getProductVersions().stream().mapToLong(ProductVersion::getTotalQuantity).sum();
		}
//		
		
		return 0l;
	}

	
}
