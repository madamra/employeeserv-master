package com.paypal.bfs.test.employeeserv.exception;

public class RepositoryException extends ServiceException {

	/**
	 * Default Serial ID
	 */
	private static final long serialVersionUID = -301711342361248693L;

	public RepositoryException(String message) {
		super(message);
	}

	public RepositoryException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public RepositoryException(String message, Exception e) {
		super(message, e);
	}
}
