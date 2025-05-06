package com.generation.zecoisinhatemtudo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.zecoisinhatemtudo.model.Cliente;
import com.generation.zecoisinhatemtudo.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    
    public ResponseEntity<List<Cliente>> buscarTodosClientes() {
	return ResponseEntity.status(HttpStatus.OK)
		.body(clienteRepository.findAll());
    }
    

    public ResponseEntity<Cliente> buscarPorId(String cpf) {
	return clienteRepository.findById(cpf)
		.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
		.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    public ResponseEntity<List<Cliente>> buscarPorNome(String nome) {
	return ResponseEntity.status(HttpStatus.OK)
		.body(clienteRepository.findByNomeContainingIgnoreCase(nome));
    }
    
    public ResponseEntity<Cliente> cadastrarCliente(Cliente cliente) {
	return ResponseEntity.status(HttpStatus.CREATED)
		.body(clienteRepository.save(cliente));
    }
    
    public ResponseEntity<Cliente> atualizarCliente(Cliente cliente) {
	if (clienteRepository.existsById(cliente.getCpf())) {
	    return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(cliente));
	}
	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
    public void excluirCliente(String cpf) {
	Optional<Cliente> cliente = clienteRepository.findById(cpf);
	
	if (cliente.isEmpty()) {
	    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Clinte não existe!", null);
	}
	
	clienteRepository.deleteById(String.valueOf(cpf));
    }
}
