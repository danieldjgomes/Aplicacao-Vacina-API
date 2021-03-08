package com.gomes.daniel.zup.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name="tb_vacinas")
public class Vacina {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		
		@Column(nullable = false)
		private String nome;
				
		@Email(regexp = ".+@.+\\..+")
		@Column(unique = true, nullable = false)
		private String email;
		
		@Column(nullable = false)
		private Date dataVacinacao;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Date getDataVacinacao() {
			return dataVacinacao;
		}

		public void setDataVacinacao(Date dataVacinacao) {
			this.dataVacinacao = dataVacinacao;
		}

		public Vacina() {
			super();
		}

		public Vacina(long id, String nome, String email, Date dataVacinacao) {
			super();
			this.id = id;
			this.nome = nome;
			this.email = email;
			this.dataVacinacao = dataVacinacao;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (id ^ (id >>> 32));
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Vacina other = (Vacina) obj;
			if (id != other.id)
				return false;
			return true;
		}

	
		
}









