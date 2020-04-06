package com.nelioalves.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.dto.CidadeDTO;
import com.nelioalves.cursomc.dto.EstadoDTO;
import com.nelioalves.cursomc.services.CidadeService;

@RestController
@RequestMapping("/estados")
public class CidadeResource {
	
	
	@Autowired
	private CidadeService service;
	
	@GetMapping("/{estadoId}/cidades")
	public ResponseEntity<List<CidadeDTO>> litaEstado(@PathVariable("estadoId") Integer estadoId){
		
		List<CidadeDTO> dto = service.findAllEstados(estadoId).stream().map(CidadeDTO::new).collect( Collectors.toList());
		
		return ResponseEntity.ok().body(dto);
		
		
	}

}
