package com.vandu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vandu.enums.TokenType;
import com.vandu.model.Token;
import com.vandu.model.User;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long>{

	@Query("select t.user from Token t where t.token = :token and t.tokenType = :tokenType")
	Optional<User> findUserByToken(String token, TokenType tokenType);
	
	Optional<Token> findByTokenAndTokenType(String token,TokenType tokenType);
}
