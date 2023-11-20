package com.jessica.dscatalog.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jessica.dscatalog.dto.CategoriaDTO;
import com.jessica.dscatalog.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	
	// endpoint(rota que vai responder algo).
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll(){ // findAll(Busca todas as categorias)
		List<CategoriaDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> findById(@PathVariable Long id){ // findAll(Busca todas as categorias)
		CategoriaDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
}