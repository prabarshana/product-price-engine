package io.x.products.exceptions;

public class InvalidUomException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5934070896527634498L;

	public InvalidUomException() {
		super();
	}

	public InvalidUomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidUomException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidUomException(String message) {
		super(message);
	}

	public InvalidUomException(Throwable cause) {
		super(cause);
	}

}
