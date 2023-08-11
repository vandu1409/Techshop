package com.vandu.service;

import java.io.UnsupportedEncodingException;

import org.springframework.http.ResponseEntity;

import com.vandu.dto.CheckoutInfoDto;
import com.vandu.exception.OutOfStockException;
import com.vandu.exception.VoucherNotApplicableException;

import jakarta.servlet.http.HttpServletRequest;

public interface CheckOutService {

	ResponseEntity<?> checkOut(CheckoutInfoDto checkoutInfoDto, HttpServletRequest request,String vnp_TransactionNo) throws VoucherNotApplicableException, UnsupportedEncodingException,OutOfStockException;

}
