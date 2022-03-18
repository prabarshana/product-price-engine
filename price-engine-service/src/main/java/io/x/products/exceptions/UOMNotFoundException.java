package io.x.products.exceptions;

public class UOMNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1992696062969154815L;

	public UOMNotFoundException() {
		super();
	}

	public UOMNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UOMNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UOMNotFoundException(String message) {
		super(message);
	}

	public UOMNotFoundException(Throwable cause) {
		super(cause);
	}

}
