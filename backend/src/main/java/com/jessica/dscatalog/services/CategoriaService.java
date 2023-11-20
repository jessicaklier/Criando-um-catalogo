package com.jessica.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jessica.dscatalog.dto.CategoriaDTO;
import com.jessica.dscatalog.entities.Categoria;
import com.jessica.dscatalog.repositories.CategoriaRepository;
import com.jessica.dscatalog.services.exceptions.EntityNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	public Object insert;
	
	@Transactional(readOnly = true)
	public List<CategoriaDTO> findAll(){
		List<Categoria> list = repository.findAll();

		return list.stream().map(x -> new CategoriaDTO(x)).collect(Collectors.toList());
		
	}

	@Transactional(readOnly = true)
	public CategoriaDTO findById(Long id) {
		Optional<Categoria> obj = repository.findById(id);
		Categoria entity = obj.orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));
		return new CategoriaDTO(entity);
	}
	
	@Transactional
	public CategoriaDTO insert(CategoriaDTO dto) {
		Categoria entity = new Categoria();
		entity.setNome(dto.getNome());
		entity = repository.save(entity);
		return new CategoriaDTO(entity);
	}
}
