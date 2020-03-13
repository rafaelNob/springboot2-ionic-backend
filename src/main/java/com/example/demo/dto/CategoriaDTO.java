package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.domain.Categoria;

public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
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
