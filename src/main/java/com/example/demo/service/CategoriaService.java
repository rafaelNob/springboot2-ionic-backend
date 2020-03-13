package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Categoria;
import com.example.demo.repository.CategoriaRepository;



@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository  categoriaRepository;
	
	public Categoria find(Integer id) {
		
		return categoriaRepository.findById(id).orElse(null);
	}
	
	
	public Categoria inserir(Categoria categoria) {
		
		return categoriaRepository.save(categoria);
	}
	
public Categoria update(Categoria categoria) {
		find(categoria.getId());
		return categoriaRepository.save(categoria);
	}

}
