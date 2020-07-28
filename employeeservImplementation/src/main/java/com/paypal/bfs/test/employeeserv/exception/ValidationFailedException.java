package com.paypal.bfs.test.employeeserv.exception;

public class ValidationFailedException extends ServiceException {

	private static final long serialVersionUID = -5180301071374379180L;

	public ValidationFailedException(String message) {
		super(message);
	}
	
	public ValidationFailedException(Throwable throwable) {
		super(throwable);
	}

	public ValidationFailedException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
