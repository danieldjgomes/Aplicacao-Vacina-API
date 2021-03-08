package com.gomes.daniel.zup.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gomes.daniel.zup.domain.exception.DadosNaoSatisfatoriosException;
import com.gomes.daniel.zup.domain.exception.DadosUnicosException;
import com.gomes.daniel.zup.domain.model.Vacina;
import com.gomes.daniel.zup.infrastructure.VacinaRepositoryImpl;

@Service
public class VacinaService{

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	VacinaRepositoryImpl vacinaImpl;
	

	public Vacina salvar(Vacina vacina) {
			
		try {
			return vacinaImpl.salvar(vacina);
		}
		
		catch (PersistenceException e) {
			throw new DadosUnicosException("Os dados que o usuário inseriu que intereferem na qualidade da persistência de dados");
		}
		
		catch(ConstraintViolationException e) {
			throw new DadosNaoSatisfatoriosException("O usuário inseriu dados fora do padrão solicitado");
		}
		
	}
	
	public Vacina atualizar(Vacina vacina) {
		
		try {
			return vacinaImpl.atualizar(vacina);
		}
		
		catch (PersistenceException e) {
			throw new DadosUnicosException("Os dados que o usuário inseriu que intereferem na qualidade da persistência de dados");
		}
		
		catch(ConstraintViolationException e) {
			throw new DadosNaoSatisfatoriosException("O usuário inseriu dados fora do padrão solicitado");
		}
		
	}
	
		public void remover(Long id) {
			vacinaImpl.remover(id);
		}
	

}
