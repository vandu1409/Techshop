package com.vandu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vandu.model.FeedBackImage;

@Repository
public interface FeedBackImageRepository extends JpaRepository<FeedBackImage, Long>{

}
