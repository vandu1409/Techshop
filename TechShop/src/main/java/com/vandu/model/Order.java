package com.vandu.model;

import java.util.Date;
import java.util.List;

import com.vandu.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	
	private Date createDate;
	
	private Date updateDate;
	
	private Date deliveryDate;
	
	private Double discount;
	
	private Double totalPrice;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@Column(columnDefinition = "nvarchar(2000)")
	private String notes;
	
	@ManyToOne
	@JoinColumn(name = "voucherId")
	private Voucher voucher;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "addressId")
	private Address address;
	
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
	private List<OrderItem> orderItems;
	
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
	private List<Payments> payments;
	
	
	

}
