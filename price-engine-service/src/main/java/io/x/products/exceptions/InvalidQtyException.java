package io.x.products.exceptions;

public class InvalidQtyException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4646502219777319046L;

	public InvalidQtyException() {
		super();
	}

	public InvalidQtyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidQtyException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidQtyException(String message) {
		super(message);
	}

	public InvalidQtyException(Throwable cause) {
		super(cause);
	}

}
