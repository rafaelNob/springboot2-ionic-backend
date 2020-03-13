package com.example.demo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Cliente;
import com.example.demo.resources.exceptions.ObjectNotFoundException;
import com.example.demo.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		 Cliente obj = service.buscar(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Não encontrado: "+ Cliente.class.getName() );
		}
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "/teste/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> findNomeEmail(@PathVariable Integer id) {
		Cliente obj = service.buscarNOmeEemail(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Não encontrado: "+ Cliente.class.getName() );
		}
		return ResponseEntity.ok().body(obj);
	}

}
