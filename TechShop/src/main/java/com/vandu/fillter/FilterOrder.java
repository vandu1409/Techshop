package com.vandu.fillter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.vandu.enums.OrderStatus;
import com.vandu.model.Order;

import jakarta.persistence.criteria.Predicate;
import lombok.Data;

public class FilterOrder {

	public static Specification<Order> filterOrder(int status, Date startDate, Date endDate) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			OrderStatus orderStatus = null;

			switch (status) {
			case 0: {
				orderStatus = null;
				break;
			}
			case 1: {
				orderStatus = OrderStatus.PENDING;
				break;
			}
			case 2: {
				orderStatus = OrderStatus.SHIPING;
				break;
			}
			case 3: {
				orderStatus = OrderStatus.DELIVERED;
				break;
			}
			case 4: {
				orderStatus = OrderStatus.CANELED;
				break;
			}

			}

			if (orderStatus != null) {
				predicates.add(criteriaBuilder.equal(root.get("status"), orderStatus));
			}

			if (startDate != null && endDate != null) {
				predicates.add(criteriaBuilder.between(root.get("createDate"), startDate, endDate));
			} else if (startDate != null) {
				predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createDate"), startDate));
			} else if (endDate != null) {
				predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createDate"), endDate));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}

}
