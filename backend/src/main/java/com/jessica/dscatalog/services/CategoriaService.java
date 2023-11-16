package com.jessica.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jessica.dscatalog.entities.Categoria;
import com.jessica.dscatalog.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	@Transactional(readOnly = true)
	public List<Categoria> findAll(){
		return repository.findAll();
		
	}
}
