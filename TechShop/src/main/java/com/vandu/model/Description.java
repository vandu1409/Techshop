package com.vandu.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Description {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "nvarchar(4000)")
	private String title;
	
	@Column(columnDefinition = "nvarchar(4000)")
	private String description;
	
	@Column(columnDefinition = "nvarchar(1000)")
	private String image;
	
	@ManyToOne
	@JoinColumn(name = "productId")
	private Product product;
}
