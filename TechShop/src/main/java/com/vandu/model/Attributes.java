package com.vandu.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Attributes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "nvarchar(500)")
	private String name;

	@OneToMany(mappedBy = "attributes")
	@JsonIgnore
	private List<CategoryAttributes> categoryAttributes;
	
	@OneToMany(mappedBy = "attributes")
	@JsonIgnore
	private List<VersionAttributes> versionAttributes;
	
	@OneToMany(mappedBy = "attributes")
	@JsonIgnore
	private List<ProductAttributes> productAttributes;

}
