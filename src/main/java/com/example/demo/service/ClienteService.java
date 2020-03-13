package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Cliente;
import com.example.demo.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente buscar(Integer id) {

		return clienteRepository.findById(id).orElse(null);
	}

	public Cliente buscarNOmeEemail(Integer id) {

		return clienteRepository.encontrePorNomeEemail(id);
	}

}
