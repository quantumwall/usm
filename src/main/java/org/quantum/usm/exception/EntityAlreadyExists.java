package org.quantum.usm.exception;

public class EntityAlreadyExists extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntityAlreadyExists(String message) {
		super(message);
	}

	public EntityAlreadyExists(Throwable e) {
		super(e);
	}

}
