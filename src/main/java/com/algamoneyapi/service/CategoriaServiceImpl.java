package com.algamoneyapi.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algamoneyapi.model.Categoria;
import com.algamoneyapi.repository.CategoriaRepository;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	

	@Override
	public List<Categoria> listarTodos() {
		return repository.findAll();
	}

	@Override
	public Categoria criar(Categoria categoria) {
		return repository.save(categoria);	
	}

	@Override
	public Optional<Categoria> buscarPorCodigo(Long codigo) {
		return repository.findById(codigo);
	}

	
	
}
