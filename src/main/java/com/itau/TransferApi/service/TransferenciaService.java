package com.itau.TransferApi.service;

import com.itau.TransferApi.model.Cliente;
import com.itau.TransferApi.model.Transferencia;
import com.itau.TransferApi.repository.ClienteRepository;
import com.itau.TransferApi.repository.TransferenciaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransferenciaService {

    private static final Logger logger = LoggerFactory.getLogger(TransferenciaService.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    public Transferencia realizarTransferencia(String numeroContaOrigem, String numeroContaDestino, Double valor) {
        logger.info("Iniciando transferencia de {} para {} no valor de R$ {}", numeroContaOrigem, numeroContaDestino, valor);

        Optional<Cliente> clienteOrigemOpt = clienteRepository.findByNumeroConta(numeroContaOrigem);
        Optional<Cliente> clienteDestinoOpt = clienteRepository.findByNumeroConta(numeroContaDestino);

        if (clienteOrigemOpt.isEmpty()) {
            logger.error("Conta de origem nao encontrada: {}", numeroContaOrigem);
            throw new IllegalArgumentException("Conta de origem nao encontrada.");
        }

        if (clienteDestinoOpt.isEmpty()) {
            logger.error("Conta de destino não encontrada: {}", numeroContaDestino);
            throw new IllegalArgumentException("Conta de destino nao encontrada.");
        }

        Cliente clienteOrigem = clienteOrigemOpt.get();
        Cliente clienteDestino = clienteDestinoOpt.get();

        if (clienteOrigem.getSaldo() < valor) {
            logger.error("Saldo insuficiente na conta de origem: {}", numeroContaOrigem);
            throw new IllegalArgumentException("Saldo insuficiente para realizar a transferencia.");
        }

        if (valor > 10000) {
            logger.error("Valor de transferencia excede o limite permitido: R$ {}", valor);
            throw new IllegalArgumentException("Valor de transferencia excede o limite de R$ 10.000,00.");
        }

        // Atualiza os saldos das contas
        clienteOrigem.setSaldo(clienteOrigem.getSaldo() - valor);
        clienteDestino.setSaldo(clienteDestino.getSaldo() + valor);

        clienteRepository.save(clienteOrigem);
        clienteRepository.save(clienteDestino);

        // Cria e salva a transferência
        Transferencia transferencia = new Transferencia();
        transferencia.setContaOrigem(clienteOrigem);
        transferencia.setContaDestino(clienteDestino);
        transferencia.setValor(valor);
        transferencia.setDataHora(LocalDateTime.now());
        transferencia.setSucesso(true);

        Transferencia transferenciaSalva = transferenciaRepository.save(transferencia);
        logger.info("Transferencia realizada com sucesso: {}", transferenciaSalva.getId());

        return transferenciaSalva;
    }

    public List<Transferencia> buscarTransferenciasPorNumeroConta(String numeroConta) {
        logger.info("Buscando transferencias relacionadas a conta: {}", numeroConta);
        return transferenciaRepository.findByContaOrigem_NumeroContaOrContaDestino_NumeroContaOrderByDataHoraDesc(numeroConta, numeroConta);
    }
}
