package com.vandu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vandu.model.Description;
@Repository
public interface DescriptionRespository extends JpaRepository<Description, Long>{

}
