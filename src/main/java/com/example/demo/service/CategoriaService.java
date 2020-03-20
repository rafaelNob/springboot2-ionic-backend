package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Categoria;
import com.example.demo.dto.CategoriaDTO;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.resources.exceptions.DataIntegrityException;
/**
 * 
 * @author Rafael.Franca
 * @version V	1.0.0
 *
 */
@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

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

	public List<Categoria> findAll() {

		return categoriaRepository.findAll();
	}
	
	public void delete(Integer id) {
		
		this.find(id);
		 
		 try {
			 categoriaRepository.deleteById(id);
			 
		 }catch (DataIntegrityViolationException e) {
			 	throw new DataIntegrityException("Não é possivel excluir uma Categoria que possue Produtos ");// TODO: handle exception
		}
	}
	
	public Page<Categoria> findPage(Integer page,Integer linesPerPage,String orderBy,String diretion){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(diretion), orderBy);
		return  categoriaRepository.findAll(pageRequest);
	}
	
	public Categoria dtoFromCategoriaDTO(CategoriaDTO dto) {
		
		return new Categoria(dto.getId(), dto.getNome());
	}

}
