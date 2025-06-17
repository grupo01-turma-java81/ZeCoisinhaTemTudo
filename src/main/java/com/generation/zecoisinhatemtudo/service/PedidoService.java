package com.generation.zecoisinhatemtudo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.zecoisinhatemtudo.model.Cliente;
import com.generation.zecoisinhatemtudo.model.Pedido;

import com.generation.zecoisinhatemtudo.repository.ClienteRepository;
import com.generation.zecoisinhatemtudo.repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public List<Pedido> buscarPorStatus(String statusEntrega) {
        return pedidoRepository.findAllByStatusEntregaContainingIgnoreCase(statusEntrega);
    }

    public Pedido criarPedido(Pedido pedido) {
        Optional.ofNullable(pedido.getCliente())
                .map(Cliente::getId)
                .filter(cliente -> clienteRepository.existsById(cliente))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não existe!", null));

        return pedidoRepository.save(pedido);
    }

    public Pedido atualizarPedido(Pedido pedido) {
        if (!pedidoRepository.existsById(pedido.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado!");
        }

        if (!clienteRepository.existsById(pedido.getCliente().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não existe!");
        }

        return pedidoRepository.save(pedido);
    }

    public void deletarPedido(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);

        if (pedido.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado!");
        }

        pedidoRepository.deleteById(id);
    }
}
