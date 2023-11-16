package com.jessica.dscatalog.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jessica.dscatalog.entities.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	// endpoint(rota que vai responder algo).
	
	@GetMapping
	public ResponseEntity<List<Categoria>> findAll(){ // findAll(Busca todas as categorias)
		List<Categoria> list = new ArrayList<>();
		list.add(new Categoria(1L, "Livros"));
		list.add(new Categoria(2L, "Eletronicos"));
		return ResponseEntity.ok().body(list);
	}
}
