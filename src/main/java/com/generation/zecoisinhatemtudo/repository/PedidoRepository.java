package com.generation.zecoisinhatemtudo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.zecoisinhatemtudo.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findAllByStatusEntregaContainingIgnoreCase(String statusEntrega);
}