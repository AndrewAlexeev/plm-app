package com.mai.projects.plm.security.exception;

import javax.naming.AuthenticationException;

public class InvalidTokenException extends AuthenticationException {
	public InvalidTokenException(String msg) {
		super(msg);
	}
}
