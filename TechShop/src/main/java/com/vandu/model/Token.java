package com.vandu.model;

import java.util.Date;

import com.vandu.enums.TokenType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tokenId;
	
	@Column(columnDefinition = "nvarchar(500)")
	private String token;
	
	@Enumerated(EnumType.STRING)
	private TokenType tokenType;
	
//	@Temporal(TemporalType.TIME)
	private Date createDate;
	
//	@Temporal(TemporalType.TIME)
	private Date expiredDate;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

}
