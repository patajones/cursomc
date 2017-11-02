package br.com.patajones.cursomc.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String arg0) {
		super(arg0);
	}

	public ObjectNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
