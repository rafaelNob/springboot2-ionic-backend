package com.example.demo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Produto;
import com.example.demo.dto.ProdutoDTO;
import com.example.demo.resources.exceptions.ObjectNotFoundException;
import com.example.demo.resources.utils.URL;
import com.example.demo.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Produto obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> findAll() {
		List<Produto> findAll = service.findAll();
		return ResponseEntity.ok().body(findAll);
	}

	/*
	 * @GetMapping public ResponseEntity<Page<ProdutoDTO>> findAllPagination(
	 * 
	 * @RequestParam(value = "nome",defaultValue = "") String nome,
	 * 
	 * @RequestParam(value = "categorias",defaultValue = "") String categorias,
	 * 
	 * @RequestParam(value = "page",defaultValue = "0") Integer page,
	 * 
	 * @RequestParam(value = "linesPerPage",defaultValue = "10") Integer
	 * linesPerPage,
	 * 
	 * @RequestParam(value = "orderBy",defaultValue = "nome") String orderBy,
	 * 
	 * @RequestParam(value = "diretion",defaultValue = "ASC") String diretion) {
	 * 
	 *//**
		 * NA URL VEM CATEGORIA= 1,2,3 e a função serve para converter para array de
		 * Integer
		 */

	/*
	 * 
	 * List<Integer> ids = URL.decodeIntList(categorias);
	 * 
	 *//**
		 * NA URL VEM nome para converter espaços
		 *//*
			 * String nomeDecode = URL.decodeParam(nome); Page<Produto> findPage =
			 * service.search(nomeDecode,ids,page, linesPerPage, orderBy, diretion);
			 * Page<ProdutoDTO> listaDto = findPage.map(ProdutoDTO::new);
			 * 
			 * 
			 * if (listaDto == null) { throw new
			 * ObjectNotFoundException("Nenhuma lista Encontrada:  " + findPage); } return
			 * ResponseEntity.ok().body(listaDto); }
			 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}

}
