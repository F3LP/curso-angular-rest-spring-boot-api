package com.algamoneyapi.service;

import java.util.List;
import java.util.Optional;

import com.algamoneyapi.model.Categoria;

public interface CategoriaService {

	List<Categoria> listarTodos();
	
	Categoria criar(Categoria categoria);
	
	Optional<Categoria> buscarPorCodigo(Long codigo);
}
