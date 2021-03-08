package com.gomes.daniel.zup.domain.exception;

public class DadosUnicosException extends RuntimeException  {

	private static final long serialVersionUID = 1L;
	
	public DadosUnicosException(String mensagem) {
		
		super(mensagem);
		System.out.println(mensagem);
	}
}
