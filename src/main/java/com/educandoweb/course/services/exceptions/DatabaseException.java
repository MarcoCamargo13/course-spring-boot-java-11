package com.educandoweb.course.services.exceptions;

public class DatabaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DatabaseException(String msg ) { // construtor vaia receber o objeto não encontrado
		super(msg);// texto para extensão
	}
}