package com.itau.TransferApi.repository;

import com.itau.TransferApi.model.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    List<Transferencia> findByContaOrigem_NumeroContaOrContaDestino_NumeroContaOrderByDataHoraDesc(String numeroContaOrigem, String numeroContaDestino);
}
