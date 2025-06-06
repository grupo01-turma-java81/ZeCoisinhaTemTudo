package com.generation.zecoisinhatemtudo.model;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "tb_pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UpdateTimestamp
    private ZonedDateTime dataPedido;

    @NotBlank
    private String statusEntrega;

    @NotNull
    private BigDecimal valorTotal;

    private Boolean positivo;

    @ManyToOne
    @JsonIgnoreProperties("pedido")
    private Cliente cliente;

    @ManyToOne
    @JsonIgnoreProperties("pedido")
    private Usuario usuario;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public ZonedDateTime getDataPedido() {
	return dataPedido;
    }

    public void setDataPedido(ZonedDateTime dataPedido) {
	this.dataPedido = dataPedido;
    }

    public String getStatusEntrega() {
	return statusEntrega;
    }

    public void setStatusEntrega(String statusEntrega) {
	this.statusEntrega = statusEntrega;
    }

    public BigDecimal getValorTotal() {
	return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
	this.valorTotal = valorTotal;
    }

    public Boolean getPositivo() {
	return positivo;
    }

    public void setPositivo(Boolean positivo) {
	this.positivo = positivo;
    }

    public Cliente getCliente() {
	return cliente;
    }

    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }
}