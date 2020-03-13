package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Pedido;
import com.example.demo.repository.PedidoRepository;



@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository  categoriaRepository;
	
	public Pedido buscar(Integer id) {
		
		return categoriaRepository.findById(id).orElse(null);
	}

}
