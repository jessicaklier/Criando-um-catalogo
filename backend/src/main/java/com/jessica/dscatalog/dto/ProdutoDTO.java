package com.jessica.dscatalog.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.jessica.dscatalog.entities.Categoria;
import com.jessica.dscatalog.entities.Produto;

public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String descricao;
	private Double preco;
	private String imagemUrl;
	private Instant data;
		
	private List<CategoriaDTO> categorias = new ArrayList<>();
	
	public ProdutoDTO() {
		
	}

	public ProdutoDTO(Long id, String nome, String descricao, Double preco, String imagemUrl, Instant data) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.imagemUrl = imagemUrl;
		this.data = data;
	}
	public ProdutoDTO(Produto entity) {
		super();
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.descricao = entity.getDescricao();
		this.preco = entity.getPreco();
		this.imagemUrl = entity.getImagemUrl();
		this.data = entity.getData();
	}
	
	// fazendo uma sobre carga com argumentos diferentes
	public ProdutoDTO(Produto entity, Set<Categoria> categorias) {
		this(entity);
		categorias.forEach(cat -> this.categorias.add(new CategoriaDTO(cat)));
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public String getImagemUrl() {
		return imagemUrl;
	}

	public Instant getData() {
		return data;
	}

	public List<CategoriaDTO> getCategorias() {
		return categorias;
	}
}