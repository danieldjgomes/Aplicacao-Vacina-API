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
import com.gomes.daniel.zup.domain.model.Vacina;
import com.gomes.daniel.zup.infrastructure.VacinaRepositoryImpl;
import com.gomes.daniel.zup.service.VacinaService;

@RestController
@RequestMapping("/vacinas")
public class VacinaController {
	
	@Autowired
	private VacinaService vacinaService;
	
	@Autowired
	private VacinaRepositoryImpl vacinaImpl;
	
	
	@GetMapping
	ResponseEntity<List<Vacina>> listar(){
		List<Vacina> vacina = vacinaImpl.listar();
		return ResponseEntity.ok(vacina);
	}
	
	@GetMapping("/{vacinaId}")
	ResponseEntity<Vacina> buscar(@PathVariable Long vacinaId){
		Vacina vacina = vacinaImpl.buscar(vacinaId);
		if (vacina != null) {
			return ResponseEntity.ok(vacina);
		}
		else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PostMapping
	ResponseEntity<Vacina> salvar(@RequestBody Vacina vacina){
		try{
			vacinaService.salvar(vacina);
		}
		catch (DadosNaoSatisfatoriosException e) {
			return ResponseEntity.badRequest().build();
		}
		
		catch (DadosUnicosException e) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/{vacinaId}")
	ResponseEntity<Vacina> atualizar(@PathVariable Long vacinaId, @RequestBody Vacina vacina){
		Vacina vacinaAlterada = vacinaImpl.buscar(vacinaId);
		
		if(vacinaAlterada != null) {
			BeanUtils.copyProperties(vacina, vacinaAlterada,"id");
			try {
			vacinaAlterada = vacinaImpl.atualizar(vacinaAlterada);
			}
			catch (DadosNaoSatisfatoriosException e) {
				return ResponseEntity.badRequest().build();
			}
			
			catch (DadosUnicosException e) {
				return ResponseEntity.badRequest().build();
			}
			return ResponseEntity.ok(vacinaAlterada);	
		}
		
			return ResponseEntity.notFound().build();	
	}
	
	
	@DeleteMapping("/{vacinaId}")
	ResponseEntity<Vacina> remover(@PathVariable Long vacinaId){
		if (vacinaImpl.buscar(vacinaId) != null) {
			vacinaService.remover(vacinaId);	
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
			
	}
		
			
	}


