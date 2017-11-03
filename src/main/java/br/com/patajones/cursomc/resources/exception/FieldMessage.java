package br.com.patajones.cursomc.resources.exception;

import java.io.Serializable;

public class FieldMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String msg;
	private String fieldName;
	
	public FieldMessage(String msg, String fieldName) {
		super();
		this.msg = msg;
		this.fieldName = fieldName;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}


}
