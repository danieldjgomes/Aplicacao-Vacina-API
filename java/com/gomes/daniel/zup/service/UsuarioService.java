package com.gomes.daniel.zup.service;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gomes.daniel.zup.domain.exception.DadosNaoSatisfatoriosException;
import com.gomes.daniel.zup.domain.exception.DadosUnicosException;
import com.gomes.daniel.zup.domain.model.Usuario;
import com.gomes.daniel.zup.infrastructure.UsuarioRepositoryImpl;
import com.gomes.daniel.zup.utils.AplicacaoVacinaUtils;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepositoryImpl usuarioImpl;
	
	@Autowired 
	AplicacaoVacinaUtils utils;
	

	public Usuario salvar(Usuario usuario) {
		utils.trataCpf(usuario);
			
		try {
			return usuarioImpl.salvar(usuario);
		}
		
		catch (PersistenceException e) {
			throw new DadosUnicosException("Os dados que o usuário inseriu intereferem na qualidade da persistência de dados");
		}
		
		catch(ConstraintViolationException e) {
			throw new DadosNaoSatisfatoriosException("O usuário inseriu dados fora do padrão solicitado");
		}
		
	}
	
	public Usuario atualizar(Usuario usuario) {
		utils.trataCpf(usuario);
			
		try {
			return usuarioImpl.atualizar(usuario);
		}
		
		catch (PersistenceException e) {
			throw new DadosUnicosException("Os dados que o usuário inseriu intereferem na qualidade da persistência de dados");
		}
		
		catch(ConstraintViolationException e) {
			throw new DadosNaoSatisfatoriosException("O usuário inseriu dados fora do padrão solicitado");
		}
		
		
	}
		
	public void remover(Long id) {
			usuarioImpl.remover(id);
		}
		
	
		
		
		
	
	
	
	

}
