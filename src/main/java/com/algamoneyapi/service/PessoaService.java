package com.algamoneyapi.service;

import java.util.List;
import java.util.Optional;

import com.algamoneyapi.model.Pessoa;

public interface PessoaService {

	List<Pessoa> listarTodos();

	Pessoa criar(Pessoa pessoa);

	Optional<Pessoa> buscarPorCodigo(Long codigo);
	
	void excluir(Long id);
}
