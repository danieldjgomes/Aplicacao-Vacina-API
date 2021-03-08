package com.gomes.daniel.zup.utils;

import org.springframework.stereotype.Service;

import com.gomes.daniel.zup.domain.model.Usuario;

@Service
public class AplicacaoVacinaUtils {
	


		public void trataCpf(Usuario usuario) {
			String cpf = usuario.getCpf();
			usuario.setCpf(cpf.replaceAll("[^a-zA-Z0-9]", ""));
		}
		
		
}
