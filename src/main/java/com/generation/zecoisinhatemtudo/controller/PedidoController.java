package com.generation.zecoisinhatemtudo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.generation.zecoisinhatemtudo.model.Pedido;
import com.generation.zecoisinhatemtudo.service.PedidoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> getAll() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getById(@PathVariable Long id) {
        return pedidoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/status/{statusEntrega}")
    public ResponseEntity<List<Pedido>> getByStatus(@PathVariable String statusEntrega) {
        return ResponseEntity.ok(pedidoService.buscarPorStatus(statusEntrega));
    }

    @PostMapping
    public ResponseEntity<Pedido> post(@Valid @RequestBody Pedido pedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.criarPedido(pedido));
    }

    @PutMapping
    public ResponseEntity<Pedido> put(@Valid @RequestBody Pedido pedido) {
        return ResponseEntity.ok(pedidoService.atualizarPedido(pedido));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        pedidoService.deletarPedido(id);
    }
}