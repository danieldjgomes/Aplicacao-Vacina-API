package com.gomes.daniel.zup.domain.exception;

public class DadosNaoSatisfatoriosException extends RuntimeException  {

	private static final long serialVersionUID = 1L;
	
	public DadosNaoSatisfatoriosException(String mensagem) {
		
		super(mensagem);
		System.out.println(mensagem);
	}
}
