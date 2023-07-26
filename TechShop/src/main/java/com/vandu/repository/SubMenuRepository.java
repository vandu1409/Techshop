package com.vandu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vandu.model.SubMenu;

@Repository
public interface SubMenuRepository extends JpaRepository<SubMenu, Long>{

}
