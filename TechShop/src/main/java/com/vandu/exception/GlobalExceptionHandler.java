package com.vandu.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(OutOfStockException.class)
	public ResponseEntity<Object> handleOutOfStockException(OutOfStockException ex, WebRequest request) {
		String message = ex.getMessage();
		ErrorDetails errorDetails = new ErrorDetails(new Date(), message, request.getDescription(false),4041);
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException ex,WebRequest request){
		String message = "Không tìm thấy sản phẩm vui lòng kiểm tra lại!";
		
		ErrorDetails errorDetails= new ErrorDetails(new Date(),message,request.getDescription(false),4042);
		
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NotLoggedInException.class)
	public ResponseEntity<?> handleNotLoggedInException(NotLoggedInException ex,WebRequest request){
		String message = "Vui lòng đăng nhập để thực hiện chức năng!";
		
		ErrorDetails errorDetails= new ErrorDetails(new Date(),message,request.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(DuplicateDataException.class)
	public ResponseEntity<?> handleDuplicateDataException(DuplicateDataException ex,WebRequest request){
		String message = ex.getMessage();
		
		ErrorDetails errorDetails= new ErrorDetails(new Date(),message,request.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(VoucherNotApplicableException.class)
	public ResponseEntity<?> handleVoucherNotApplicableException(VoucherNotApplicableException ex,WebRequest request){
		String message = ex.getMessage();
		
		ErrorDetails errorDetails= new ErrorDetails(new Date(),message,request.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(DiscontinuedProductException.class)
	public ResponseEntity<?> handleDiscontinuedProductException(DiscontinuedProductException ex,WebRequest request){
		String message = ex.getMessage();
		
		ErrorDetails errorDetails= new ErrorDetails(new Date(),message,request.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex,WebRequest request){
		String message = ex.getMessage();
		System.err.println(message);
		ErrorDetails errorDetails= new ErrorDetails(new Date(),message,request.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TokenException.class)
	public ResponseEntity<?> handleTokenException(TokenException ex,WebRequest request){
		String message = ex.getMessage();
		System.err.println(message);
		ErrorDetails errorDetails= new ErrorDetails(new Date(),message,request.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PasswordMismatchException.class)
	public ResponseEntity<?> handlePasswordMismatchException(PasswordMismatchException ex,WebRequest request){
		String message = ex.getMessage();
		System.err.println(message);
		ErrorDetails errorDetails= new ErrorDetails(new Date(),message,request.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DiscountException.class)
	public ResponseEntity<?> handleDiscountException(DiscountException ex,WebRequest request){
		String message = ex.getMessage();
		System.err.println(message);
		ErrorDetails errorDetails= new ErrorDetails(new Date(),message,request.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
	}
	@ExceptionHandler(RegistrationException.class)
	public ResponseEntity<?> handleRegistrationException(RegistrationException ex,WebRequest request){
		String message = ex.getMessage();
		System.err.println(message);
		ErrorDetails errorDetails= new ErrorDetails(new Date(),message,request.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
	}
	
	
	
}
