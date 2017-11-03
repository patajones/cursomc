package br.com.patajones.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> fieldErrors = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		
	}

	public List<FieldMessage> getFieldErrors() {
		return fieldErrors;
	}

	public void addFieldError(String msg, String fieldName) {
		this.fieldErrors.add(new FieldMessage(msg, fieldName));
	}

}
