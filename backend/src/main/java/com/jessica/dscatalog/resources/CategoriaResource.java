package com.jessica.dscatalog.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jessica.dscatalog.entities.Categoria;
import com.jessica.dscatalog.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	
	// endpoint(rota que vai responder algo).
	@GetMapping
	public ResponseEntity<List<Categoria>> findAll(){ // findAll(Busca todas as categorias)
		List<Categoria> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}
