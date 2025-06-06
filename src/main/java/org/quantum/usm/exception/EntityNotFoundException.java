package org.quantum.usm.exception;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String message) {
		super(message);
	}

	public EntityNotFoundException(Throwable e) {
		super(e);
	}

}
