package com.vandu.fillter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.vandu.model.Product;


import jakarta.persistence.criteria.Predicate;

public class FillterProductsAdmin {
	public static Specification<Product> filterProducts(Long categoryId,String keyword) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			

			if(categoryId!=null) {
				predicates.add(criteriaBuilder.equal(root.get("category").get("id"), categoryId));
			}
			
			if(keyword!=null) {
			String search = "%"+keyword+"%";
				    predicates.add(criteriaBuilder.like(root.get("name"), search));
			}
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}
}
