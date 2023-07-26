package com.vandu.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vandu.enums.AuthenticationType;
import com.vandu.enums.TokenType;
import com.vandu.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);
	
	Page<User> findByUsernameContaining(String username,Pageable pageable);
	
	Optional<User> findByUsernameAndAuthenticationType(String username,AuthenticationType authenticationType);
	
	Optional<User> findByEmailAndAuthenticationType(String email,AuthenticationType authenticationType);
	Optional<User> findByEmailAndUsernameAndAuthenticationType(String email,String username,AuthenticationType authenticationType);
	
	Page<User> findByUsernameContainingAndIsDeleted(String username,boolean isDelete,Pageable pageable);
	
	@Query("select u from User u where u.isDeleted = :isDelete")
	Page<User> findAll(boolean isDelete ,Pageable pageable);
	
}
