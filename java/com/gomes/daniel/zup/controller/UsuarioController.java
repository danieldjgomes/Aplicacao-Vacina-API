package com.gomes.daniel.zup.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gomes.daniel.zup.domain.exception.DadosNaoSatisfatoriosException;
import com.gomes.daniel.zup.domain.exception.DadosUnicosException;
import com.gomes.daniel.zup.domain.model.Usuario;
import com.gomes.daniel.zup.infrastructure.UsuarioRepositoryImpl;
import com.gomes.daniel.zup.service.UsuarioService;
import com.gomes.daniel.zup.utils.AplicacaoVacinaUtils;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepositoryImpl usuarioImpl;
	
	@Autowired
	AplicacaoVacinaUtils utils;
	
	
	@GetMapping
	ResponseEntity<List<Usuario>> listar(){
		List<Usuario> usuarios = usuarioImpl.listar();
		return ResponseEntity.ok(usuarios);
	}
	
	@GetMapping("/{usuarioId}")
	ResponseEntity<Usuario> buscar(@PathVariable Long usuarioId){
		Usuario usuario = usuarioImpl.buscar(usuarioId);
		if (usuario != null) {
			return ResponseEntity.ok(usuario);
		}
		else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PostMapping
	ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
		try{
			usuarioService.salvar(usuario);
		}
		catch (DadosNaoSatisfatoriosException e) {
			return ResponseEntity.badRequest().build();
		}
		
		catch (DadosUnicosException e) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	@PutMapping("/{usuarioId}")
	ResponseEntity<Usuario> atualizar(@PathVariable Long usuarioId, @RequestBody Usuario usuario){
		Usuario usuarioAlterado = usuarioImpl.buscar(usuarioId);
		
		if(usuarioAlterado != null) {
			BeanUtils.copyProperties(usuario, usuarioAlterado,"id");
			
			try{
				usuarioAlterado = usuarioImpl.atualizar(usuarioAlterado);
			}
			catch (DadosNaoSatisfatoriosException e) {
				return ResponseEntity.badRequest().build();
			}
			
			catch (DadosUnicosException e) {
				return ResponseEntity.badRequest().build();
			}
				
			return ResponseEntity.ok(usuarioAlterado);	
		}
			return ResponseEntity.notFound().build();	
	}
	
	@DeleteMapping("/{usuarioId}")
	ResponseEntity<Usuario> remover(@PathVariable Long usuarioId){
		if (usuarioImpl.buscar(usuarioId) != null) {
			usuarioService.remover(usuarioId);	
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
			
	}
		
	
}
	

