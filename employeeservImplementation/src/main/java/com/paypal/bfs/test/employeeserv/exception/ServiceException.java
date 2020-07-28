package com.paypal.bfs.test.employeeserv.exception;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -4497393969705022292L;

	public ServiceException(Throwable throwable) {
		super(throwable);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
