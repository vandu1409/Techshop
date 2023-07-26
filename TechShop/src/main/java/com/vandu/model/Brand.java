package com.vandu.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.vandu.helper.Views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "brands")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long brandId;

	@Column(columnDefinition = "nvarchar(500)")
	private String name;

	@Column(columnDefinition = "nvarchar(500)")
	private String brandCode;

	@Column(columnDefinition = "nvarchar(500)")
	private String image;

	@JsonBackReference
	@OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)

	private List<ProductSeries> productSeries;

	@ManyToOne
	@JoinColumn(name = "categoryId")
	@JsonIgnore
	private Category category;

	public String getBrandName() {
		return this.getName() + " - " + this.getCategory().getName();
	}

//	@JsonView(Views.Public.class)
//	public List<ProductSeries> getSeries(){
//		return this.getProductSeries();
//	}

}
