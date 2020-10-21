package com.algamoneyapi.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algamoneyapi.event.RecursoCriadoEvent;
import com.algamoneyapi.model.Pessoa;
import com.algamoneyapi.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	private PessoaService service;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	public PessoaController(PessoaService service) {
		this.service = service;
	}

	@GetMapping
	public List<Pessoa> listaTodos() {
		return service.listarTodos();
	}

	@PostMapping
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {

		Pessoa pessoaSalva = service.criar(pessoa);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));

		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);

	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable Long codigo) {

		Optional<Pessoa> pessoaEncontrada = service.buscarPorCodigo(codigo);

		return ResponseEntity.ok(pessoaEncontrada.get());
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Pessoa> remover(@PathVariable Long codigo){
		
		service.excluir(codigo);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{codigo}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo,@Valid @RequestBody Pessoa pessoa){
	
		Pessoa pessoaSalva = service.buscarPorCodigo(codigo).get();

		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
		service.criar(pessoaSalva);
		
		return ResponseEntity.ok(pessoaSalva);
	}
	
}
