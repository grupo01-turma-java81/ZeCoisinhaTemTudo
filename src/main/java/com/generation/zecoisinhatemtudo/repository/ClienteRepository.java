package com.generation.zecoisinhatemtudo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.zecoisinhatemtudo.model.Cliente;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

    List<Cliente> findByNomeContainingIgnoreCase(@Param("nome") String nome);

}
