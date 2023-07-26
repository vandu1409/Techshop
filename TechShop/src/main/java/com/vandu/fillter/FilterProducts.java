package com.vandu.fillter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

import com.vandu.model.Product;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;

public class FilterProducts {
	
	public static Specification<Product> filterProducts(String categoryCode,String brandCodeList, String seriesCode, Double minPrice,
			Double maxPrice,String featureList) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			
			 if (brandCodeList != null && !brandCodeList.isEmpty()) {
			      // Tách chuỗi brandIds thành một mảng các giá trị Long
			      List<String> brandIdList = Arrays.stream(brandCodeList.split(",")).toList();
			       
			      for (String b : brandIdList) {
					System.err.println(b);
				}
			                                     
			      // Thêm điều kiện cho danh sách brandId
			      predicates.add(root.get("productSeries").get("brand").get("brandCode").in(brandIdList));
			    }
			


			// Thêm điều kiện cho product series
			if (seriesCode != null) {
				predicates.add(criteriaBuilder.equal(root.get("productSeries").get("seriesCode"), seriesCode));
			}
			
			if(categoryCode!=null) {
				predicates.add(criteriaBuilder.equal(root.get("category").get("categoryCode"), categoryCode));
			}

			// Thêm điều kiện cho giá sản phẩm
			if (minPrice != null && maxPrice != null) {
				predicates.add(criteriaBuilder.between(root.get("price"), minPrice, maxPrice));
			} else if (minPrice != null) {
				predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
			} else if (maxPrice != null) {
				predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
			}
			
			 if (featureList != null && !featureList.isEmpty()) {
			      // Tách chuỗi brandIds thành một mảng các giá trị Long
			      List<String> featureNameList = Arrays.stream(featureList.split(",")).toList();
			 
			                                     
			      // Thêm điều kiện cho danh sách brandId
			      predicates.add(root.get("feature").get("value").in(featureNameList));
			    }

			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}

}
