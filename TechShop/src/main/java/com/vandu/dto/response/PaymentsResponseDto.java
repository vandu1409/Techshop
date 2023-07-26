package com.vandu.dto.response;

import java.time.LocalDate;

import com.vandu.enums.PaymentMethod;
import com.vandu.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentsResponseDto {

	private LocalDate createDate;

	private Double totalPrice;

	private PaymentMethod paymentMethod;

	private PaymentStatus paymentStatus;
}
