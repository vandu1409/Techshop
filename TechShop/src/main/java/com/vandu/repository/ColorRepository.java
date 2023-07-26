package com.vandu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vandu.model.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

	Page<Color> findByNameContaining(String name,Pageable pageable);
}
