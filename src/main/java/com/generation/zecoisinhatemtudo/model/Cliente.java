package com.generation.zecoisinhatemtudo.model;

import java.time.LocalDate;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_cliente")
public class Cliente {
    
    @Id
    @Size(min = 10, max = 11,message = "O atributo cpf deve conter 11 caracteres")
    private String cpf;
    
    @NotBlank(message = "O atributo nome não pode nulo e conter espaços em branco")
    @Size(min = 5, message = "O atributo nome precisa ter no minimo 5 caracteres")
    private String nome;
    
    @NotBlank(message = "O atributo telefone não pode nulo e conter espaços em branco")
    @Size(max = 14, message = "O atributo telefone precisa ter no minimo 14 caracteres")
    private String telefone;
    
    @NotBlank(message = "O atributo endereço não pode nulo e conter espaços em branco")
    @Size@Size(min = 8, message = "O atributo nome precisa ter no minimo 8 caracteres")
    private String endereco;
    
    @UpdateTimestamp
    private LocalDate dataCadastro;
    

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
}
