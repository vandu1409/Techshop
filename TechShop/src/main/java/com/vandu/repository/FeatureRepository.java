package com.vandu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vandu.model.Feature;

public interface FeatureRepository extends JpaRepository<Feature,Long >{

}
