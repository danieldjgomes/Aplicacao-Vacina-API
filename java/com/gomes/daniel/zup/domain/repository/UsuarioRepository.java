package com.gomes.daniel.zup.domain.repository;

import java.util.List;

import com.gomes.daniel.zup.domain.model.Usuario;

public interface UsuarioRepository {
	
	public List<Usuario> listar();
	public Usuario buscar(Long id);
	public Usuario salvar(Usuario usuario);
	public Usuario atualizar(Usuario usuario);
	public void remover(Long id);
	
	
}
 





