package com.vandu.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vandu.enums.AuthenticationType;
import com.vandu.enums.Role;
import com.vandu.service.UserService;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(columnDefinition = "nvarchar(500)")
	private String username;
	
	@Column(columnDefinition = "nvarchar(500)")
	private String password;

	@Column(columnDefinition = "nvarchar(500)")
	private String email;

	@Column(columnDefinition = "nvarchar(500)")
	private String fullname;

	private String phone;
	
	@Enumerated(EnumType.STRING)
	private AuthenticationType authenticationType;
	
	@Column(columnDefinition = "nvarchar(1000)")
	private String avatar;

	private Date createDate;

	private Date updateDate;

	private boolean isActive;

	private boolean isDeleted;
	
	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Address> addresses;

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Cart> carts;

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Token> tokens;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<WishList> wishLists;



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(this.getRole().toString()));
		
		return authorities;
		
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return this.isActive();
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


}
