package com.gomes.daniel.zup.domain.repository;

import java.util.List;

import com.gomes.daniel.zup.domain.model.Vacina;

public interface VacinaRepository {
	
	public List<Vacina> listar();
	public Vacina buscar(Long id);
	public Vacina salvar(Vacina vacina);
	public Vacina atualizar(Vacina vacina);
	public void remover(Long id);
}
