package com.nelioalves.cursomc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.nelioalves.cursomc.domain.Cep;
import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {
		PedidoResource.consumindoCep();
		Pedido obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	/**
	 * METODO PARA CONSUMIR apis externos
	 */
	private static void consumindoCep() {
		//https://viacep.com.br/ws/01001000/json/
		RestTemplate rt = new RestTemplate();
		 UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("viacep.com.br")
				.path("ws/01001000/json/")
				.build();	
		 
		 ResponseEntity<Cep> forEntity = rt.getForEntity(uri.toUriString(), Cep.class);
		 
		 System.out.println(forEntity.getBody().getLogradouro());
		
		
	}
}