package com.itau.TransferApi.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@ApiModel(description = "Detalhes do Cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "ID único do cliente")
    private Long id;

    @NotBlank
    @ApiModelProperty(notes = "Nome do cliente")
    private String nome;

    @NotBlank
    @Column(unique = true, nullable = false)
    @ApiModelProperty(notes = "Número da conta única do cliente")
    private String numeroConta;

    @NotNull
    @ApiModelProperty(notes = "Saldo da conta do cliente")
    private Double saldo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}
