package com.itau.TransferApi.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(description = "Detalhes da Transferência")
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "ID único da transferência")
    private Long id;

    @ManyToOne
    @ApiModelProperty(notes = "Conta de origem da transferência")
    private Cliente contaOrigem;

    @ManyToOne
    @ApiModelProperty(notes = "Conta de destino da transferência")
    private Cliente contaDestino;

    @ApiModelProperty(notes = "Valor da transferência")
    private Double valor;

    @ApiModelProperty(notes = "Data e hora da transferência")
    private LocalDateTime dataHora;

    @ApiModelProperty(notes = "Indica se a transferência foi bem-sucedida")
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