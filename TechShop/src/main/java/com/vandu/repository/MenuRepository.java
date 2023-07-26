package com.vandu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vandu.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

}
