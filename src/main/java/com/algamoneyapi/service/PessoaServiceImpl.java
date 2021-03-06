package com.algamoneyapi.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algamoneyapi.model.Pessoa;
import com.algamoneyapi.repository.PessoaRepository;

@Service
@Transactional
public class PessoaServiceImpl implements PessoaService {
	
	@Autowired
	private PessoaRepository repository;
	

	@Override
	public List<Pessoa> listarTodos() {
		return repository.findAll();
	}

	@Override
	public Pessoa criar(Pessoa pessoa) {
		return repository.save(pessoa);
	}

	@Override
	public Optional<Pessoa> buscarPorCodigo(Long codigo) {
		
		Optional<Pessoa> pessoaEncontrada = repository.findById(codigo);
		
		if(pessoaEncontrada.isPresent()) {
			return pessoaEncontrada;
		} else {
			throw new EmptyResultDataAccessException(1);
		}
		
	}

	@Override
	public void excluir(Long id) {
		repository.deleteById(id);
	}

	
}
