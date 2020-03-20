package com.example.demo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.hibernate.cfg.beanvalidation.BeanValidationEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.Categoria;
import com.example.demo.dto.CategoriaDTO;
import com.example.demo.resources.exceptions.ObjectNotFoundException;
import com.example.demo.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	/***
	 * lista com DTO para retorno de lista
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> obj = service.findAll();
		
		List<CategoriaDTO> listaDto = obj.stream().map(CategoriaDTO::new).collect(Collectors.toList());
		
		if (listaDto == null) {
			throw new ObjectNotFoundException("Não encontrado " + obj);
		}
		return ResponseEntity.ok().body(listaDto);
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria obj = service.find(id);

		if (obj == null) {
			throw new ObjectNotFoundException("Não encontrado " + obj);
		}
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Categoria> insert(@Valid @RequestBody CategoriaDTO objDto) {
		  Categoria dtoFromCategoriaDTO = service.dtoFromCategoriaDTO(objDto);
		  Categoria obj = service.inserir(dtoFromCategoriaDTO);
		/**
		 * URI devolve a url nova
		 */
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();

	}

	@PutMapping
	@RequestMapping("/{id}")
	public ResponseEntity<Categoria> update(@RequestBody Categoria obj, @PathVariable Integer id) {
		obj.setId(id);
		service.update(obj); 

		return ResponseEntity.noContent().build();

	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Categoria> delete(@PathVariable Integer id) {
		service.delete(id); 
		return ResponseEntity.noContent().build();

	}
	
	/**
	 * PAGINAÇÃOO 
	 */
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<CategoriaDTO>> findAllPagination(
			@RequestParam(value = "page",defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage",defaultValue = "10") Integer linesPerPage,
			@RequestParam(value = "orderBy",defaultValue = "nome") String orderBy,
			@RequestParam(value = "diretion",defaultValue = "ASC") String diretion) {
		
		 Page<Categoria> findPage = service.findPage(page, linesPerPage, orderBy, diretion);
		 Page<CategoriaDTO> listaDto = findPage.map(CategoriaDTO::new);

		
		if (listaDto == null) {
			throw new ObjectNotFoundException("Nenhuma lista Encontrada:  " + findPage);
		}
		return ResponseEntity.ok().body(listaDto);
	}


}
