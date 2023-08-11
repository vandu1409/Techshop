package com.vandu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vandu.model.Conversations;

@Repository
public interface ConversationRespository extends JpaRepository<Conversations, Long> {

}
