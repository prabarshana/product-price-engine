package io.x.products.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = ProductNotFoundException.class)
	public ResponseEntity<GlobalExceptionDto> handleProductNotFoundException(ProductNotFoundException exception) {
		return new ResponseEntity<GlobalExceptionDto>(GlobalExceptionDto.builder().errorMessage(exception.getMessage()).httpStatus(HttpStatus.NOT_FOUND).build(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = InvalidProductException.class)
	public ResponseEntity<GlobalExceptionDto> handleInvalidProductException(InvalidProductException exception) {
		return new ResponseEntity<GlobalExceptionDto>(GlobalExceptionDto.builder().errorMessage(exception.getMessage()).httpStatus(HttpStatus.CONFLICT).build(), HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = UOMNotFoundException.class)
	public ResponseEntity<GlobalExceptionDto> handleUOMNotFoundException(UOMNotFoundException exception) {
		return new ResponseEntity<GlobalExceptionDto>(GlobalExceptionDto.builder().errorMessage(exception.getMessage()).httpStatus(HttpStatus.NOT_FOUND).build(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = InvalidUomException.class)
	public ResponseEntity<GlobalExceptionDto> handleInvalidUomException(InvalidUomException exception) {
		return new ResponseEntity<GlobalExceptionDto>(GlobalExceptionDto.builder().errorMessage(exception.getMessage()).httpStatus(HttpStatus.CONFLICT).build(), HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = InvalidQtyException.class)
	public ResponseEntity<GlobalExceptionDto> handleInvalidQtyException(InvalidQtyException exception) {
		return new ResponseEntity<GlobalExceptionDto>(GlobalExceptionDto.builder().errorMessage(exception.getMessage()).httpStatus(HttpStatus.CONFLICT).build(), HttpStatus.CONFLICT);
	}
	
}
