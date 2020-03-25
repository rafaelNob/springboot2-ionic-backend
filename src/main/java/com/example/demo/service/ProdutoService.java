package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Categoria;
import com.example.demo.domain.Produto;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.ProdutoRepository;



@Service
public class ProdutoService {
	
	@Autowired
	private  ProdutoRepository   repo;
	
	@Autowired
	private  CategoriaRepository   categoriaRepository;
	
	public  Produto buscar(Integer id) {
		
		return repo.findById(id).orElse(null);
	}
	
public  List<Produto> findAll() {
		
		return repo.findAll();
	}
	
	/*
	 * public Page<Produto> search(String nome, List<Integer> ids ,Integer
	 * page,Integer linesPerPage,String orderBy,String diretion){ PageRequest
	 * pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(diretion),
	 * orderBy); List<Categoria> categoria = categoriaRepository.findAllById(ids); return
	 * repo.search(nome,categoria,pageRequest); }
	 */
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);	
	}

}
