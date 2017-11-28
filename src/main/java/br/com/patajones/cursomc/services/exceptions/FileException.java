package br.com.patajones.cursomc.services.exceptions;

public class FileException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FileException(String arg0) {
		super(arg0);
	}

	public FileException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
