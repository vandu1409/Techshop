package com.vandu.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;
	
	@Column(columnDefinition = "nvarchar(500)")
	private String fullname;
	
	private String phone;
	
	@Column(columnDefinition = "nvarchar(500)")
	private String street;
	
	@Column(columnDefinition = "nvarchar(500)")
	private String town;
	
	@Column(columnDefinition = "nvarchar(500)")
	private String district;
	
	@Column(columnDefinition = "nvarchar(500)")
	private String provice;
	
	private Boolean isActive;
	
	private Boolean isDelete;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@JsonIgnore
	@OneToMany(mappedBy = "address")
	private List<Order> orders;
	
	public String toString() {
		
		return street+" - "+phone+ "," + town +","+district+"," + provice;
	}
	
	
}
