package com.udemy.lomonaco.rest.webservices.restful.user;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2639132536102300971L;

	public UserNotFoundException(String arg0) {
		super(arg0);
	}

}
