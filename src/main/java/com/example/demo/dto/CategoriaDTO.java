package com.example.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.example.demo.domain.Categoria;

public class CategoriaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	
	@NotEmpty(message = "Preenhimento Obrigatório")
	@Length(min = 5, max = 80,message = "o tamanho tem q ser entre")
	private String nome;

	public CategoriaDTO() {
	}

	public CategoriaDTO(Categoria dto) {
		this.id = dto.getId();
		this.nome = dto.getNome();
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

}
