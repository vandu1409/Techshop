package com.vandu.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "nvarchar(500)")
	private String categoryCode;
	
	@Column(columnDefinition = "nvarchar(500)")
	private String name;
	
	@Column(columnDefinition = "nvarchar(500)")
	private String image;
	
	@OneToMany(mappedBy = "category")
	@JsonIgnore
	private List<Product> products;
	
	@OneToMany(mappedBy = "category")
	@JsonIgnore
	private List<Brand> brands;
	
	@OneToMany(mappedBy = "category")
	@JsonIgnore
	private List<CategoryAttributes> categoryAttributes;
}
