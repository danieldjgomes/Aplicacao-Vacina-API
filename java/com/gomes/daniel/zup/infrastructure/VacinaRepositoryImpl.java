package com.gomes.daniel.zup.infrastructure;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.gomes.daniel.zup.domain.model.Vacina;
import com.gomes.daniel.zup.domain.repository.VacinaRepository;

@Component
public class VacinaRepositoryImpl implements VacinaRepository {

	@PersistenceContext
	EntityManager manager;

	@Override
	public List<Vacina> listar() {
		return manager.createQuery("from Vacina", Vacina.class).getResultList();
	}

	public Vacina buscar(Long id) {
		return manager.find(Vacina.class, id);
	}

	@Override
	@Transactional
	public Vacina salvar(Vacina vacina) {
		return manager.merge(vacina);
	}
	
	@Override
	@Transactional
	public Vacina atualizar(Vacina vacina) {
		return manager.merge(vacina);	
	}

	@Override
	@Transactional
	public void remover(Long id) {
		Vacina vacina = buscar(id);
		manager.remove(vacina);
	}

}
