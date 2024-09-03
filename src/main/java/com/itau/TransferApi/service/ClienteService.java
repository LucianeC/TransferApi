package com.itau.TransferApi.service;

import com.itau.TransferApi.model.Cliente;
import com.itau.TransferApi.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente cadastrarCliente(Cliente cliente) {
        logger.info("Cadastrando cliente com conta: {}", cliente.getNumeroConta());

        // Verifica se o número da conta já existe
        Optional<Cliente> clienteExistente = clienteRepository.findByNumeroConta(cliente.getNumeroConta());
        if (clienteExistente.isPresent()) {
            logger.error("Erro ao cadastrar cliente: Numero da conta {} ja existe.", cliente.getNumeroConta());
            throw new IllegalArgumentException("Numero da conta ja existe.");
        }

        Cliente clienteSalvo = clienteRepository.save(cliente);
        logger.info("Cliente cadastrado com sucesso: {}", clienteSalvo.getId());
        return clienteSalvo;
    }

    public List<Cliente> listarClientes() {
        logger.info("Listando todos os clientes.");
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarClientePorNumeroConta(String numeroConta) {
        logger.info("Buscando cliente com conta: {}", numeroConta);
        Optional<Cliente> clienteOpt = clienteRepository.findByNumeroConta(numeroConta);
        if (clienteOpt.isPresent()) {
            logger.info("Cliente encontrado: {}", clienteOpt.get().getId());
        } else {
            logger.warn("Cliente com conta {} nao encontrado.", numeroConta);
        }
        return clienteOpt;
    }
}
