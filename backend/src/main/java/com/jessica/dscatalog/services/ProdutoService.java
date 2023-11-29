package com.jessica.dscatalog.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jessica.dscatalog.dto.CategoriaDTO;
import com.jessica.dscatalog.dto.ProdutoDTO;
import com.jessica.dscatalog.entities.Categoria;
import com.jessica.dscatalog.entities.Produto;
import com.jessica.dscatalog.repositories.CategoriaRepository;
import com.jessica.dscatalog.repositories.ProdutoRepository;
import com.jessica.dscatalog.services.exceptions.DatabaseException;
import com.jessica.dscatalog.services.exceptions.ResourceNotFoundException;


@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	public Object insert;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Transactional(readOnly = true)
	public Page<ProdutoDTO> findAllPaged(Pageable pageable){
		Page<Produto> list = repository.findAll(pageable);
		return list.map(x -> new ProdutoDTO(x));
		
	}

	@Transactional(readOnly = true)
	public ProdutoDTO findById(Long id) {
		Optional<Produto> obj = repository.findById(id);
		Produto entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada"));
		return new ProdutoDTO(entity, entity.getCategorias());
	}
	
	@Transactional
	public ProdutoDTO insert(ProdutoDTO dto) {
		Produto entity = new Produto();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ProdutoDTO(entity);
	}

	@Transactional
	public ProdutoDTO update(Long id, ProdutoDTO dto) {
		try {
		Produto entity = repository.getOne(id);
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ProdutoDTO(entity);
	}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não encontrado " + id);
		}
	}

	public void delete(Long id) {
		try {
		repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não encontrado " + id);
		}
		catch(DataIntegrityViolationException e) {
		throw new DatabaseException("Violação de Integridade");	
		}
	}
	
	private void copyDtoToEntity(ProdutoDTO dto, Produto entity) {
		entity.setNome(dto.getNome());
		entity.setDescricao(dto.getDescricao());
		entity.setData(dto.getData());
		entity.setImagemUrl(dto.getImagemUrl());
		entity.setPreco(dto.getPreco());
		
		entity.getCategorias().clear();
		for (CategoriaDTO catDto : dto.getCategorias()) {
			Categoria categoria = categoriaRepository.getOne(catDto.getId());
			entity.getCategorias().add(categoria);
		}
	}
}