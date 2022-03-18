package io.x.products.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GlobalExceptionDto {

	private String errorMessage;
	private HttpStatus httpStatus;
}
