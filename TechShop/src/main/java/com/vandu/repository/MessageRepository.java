package com.vandu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vandu.model.Conversations;
import com.vandu.model.Message;
import com.vandu.model.User;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
	List<Message> findByUserOrderByCreateAtDesc(User user);


    @Query("select m.conversations from Message m where m.user.userId = :userId")
    Optional<Conversations> findConversationsByUser(Long userId);
}
