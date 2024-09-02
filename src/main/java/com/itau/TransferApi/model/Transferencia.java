package com.itau.TransferApi.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente contaOrigem;

    @ManyToOne
    private Cliente contaDestino;

    private Double valor;

    private LocalDateTime dataHora;

    private boolean sucesso;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(Cliente contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public Cliente getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(Cliente contaDestino) {
        this.contaDestino = contaDestino;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }


}
