package com.gomes.daniel.zup.infrastructure;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.gomes.daniel.zup.domain.model.Usuario;
import com.gomes.daniel.zup.domain.repository.UsuarioRepository;

@Component
public class UsuarioRepositoryImpl implements UsuarioRepository {
	
	@PersistenceContext
	EntityManager manager;

	@Override
	public List<Usuario> listar() {
		return manager.createQuery("from Usuario", Usuario.class).getResultList();
	}

	@Override
	public Usuario buscar(Long id) {
		return manager.find(Usuario.class, id);
	}

	@Override
	@Transactional
	public Usuario salvar(Usuario usuario) {
		return manager.merge(usuario);
	}
	
	@Override
	@Transactional
	public Usuario atualizar(Usuario usuario) {
		return manager.merge(usuario);	
	}

	@Override
	@Transactional
	public void remover(Long id) {
		Usuario usuario = buscar(id);
		manager.remove(usuario);
	}

}
