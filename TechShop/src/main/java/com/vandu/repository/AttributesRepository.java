package com.vandu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vandu.model.Attributes;

@Repository
public interface AttributesRepository extends JpaRepository<Attributes, Long> {

}
