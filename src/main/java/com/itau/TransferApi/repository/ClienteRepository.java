package com.itau.TransferApi.repository;

import com.itau.TransferApi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByNumeroConta(String numeroConta);
}
