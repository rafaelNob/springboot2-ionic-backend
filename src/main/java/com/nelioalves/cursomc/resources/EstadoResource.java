package com.nelioalves.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.dto.EstadoDTO;
import com.nelioalves.cursomc.services.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoResource {
	
	
	@Autowired
	private EstadoService service;
	
	@GetMapping
	public ResponseEntity<List<EstadoDTO>> litaEstado(){
		
		List<EstadoDTO> dto = service.findAllEstados().stream().map(EstadoDTO::new).collect( Collectors.toList());
		
		return ResponseEntity.ok().body(dto);
		
		
	}

}
