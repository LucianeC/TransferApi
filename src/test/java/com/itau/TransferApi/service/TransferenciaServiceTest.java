package com.itau.TransferApi.service;

import com.itau.TransferApi.model.Cliente;
import com.itau.TransferApi.model.Transferencia;
import com.itau.TransferApi.repository.ClienteRepository;
import com.itau.TransferApi.repository.TransferenciaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransferenciaServiceTest {

    @InjectMocks
    private TransferenciaService transferenciaService;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private TransferenciaRepository transferenciaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRealizarTransferenciaComSucesso() {
        Cliente clienteOrigem = new Cliente();
        clienteOrigem.setNumeroConta("123456");
        clienteOrigem.setSaldo(2000.0);

        Cliente clienteDestino = new Cliente();
        clienteDestino.setNumeroConta("654321");
        clienteDestino.setSaldo(1000.0);

        when(clienteRepository.findByNumeroConta("123456")).thenReturn(Optional.of(clienteOrigem));
        when(clienteRepository.findByNumeroConta("654321")).thenReturn(Optional.of(clienteDestino));
        when(transferenciaRepository.save(any(Transferencia.class))).thenReturn(new Transferencia());

        Transferencia transferencia = transferenciaService.realizarTransferencia("123456", "654321", 500.0);

        assertNotNull(transferencia);
        assertTrue(transferencia.isSucesso());
        verify(clienteRepository, times(1)).save(clienteOrigem);
        verify(clienteRepository, times(1)).save(clienteDestino);
        verify(transferenciaRepository, times(1)).save(any(Transferencia.class));
    }

    @Test
    void deveLancarExcecaoSeSaldoInsuficiente() {
        Cliente clienteOrigem = new Cliente();
        clienteOrigem.setNumeroConta("123456");
        clienteOrigem.setSaldo(200.0);

        Cliente clienteDestino = new Cliente();
        clienteDestino.setNumeroConta("654321");

        when(clienteRepository.findByNumeroConta("123456")).thenReturn(Optional.of(clienteOrigem));
        when(clienteRepository.findByNumeroConta("654321")).thenReturn(Optional.of(clienteDestino));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transferenciaService.realizarTransferencia("123456", "654321", 500.0);
        });

        assertEquals("Saldo insuficiente para realizar a transferencia.", exception.getMessage());
        verify(clienteRepository, times(0)).save(clienteOrigem);
        verify(clienteRepository, times(0)).save(clienteDestino);
        verify(transferenciaRepository, times(1)).save(any(Transferencia.class));  // A transferência falhada foi registrada
    }

    @Test
    void deveLancarExcecaoSeContaOrigemNaoExistir() {
        when(clienteRepository.findByNumeroConta("123456")).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transferenciaService.realizarTransferencia("123456", "654321", 500.0);
        });

        assertEquals("Conta de origem não encontrada.", exception.getMessage());
        verify(transferenciaRepository, times(1)).save(any(Transferencia.class));  // A transferência falhada foi registrada
    }

    @Test
    void deveLancarExcecaoSeContaDestinoNaoExistir() {
        Cliente clienteOrigem = new Cliente();
        clienteOrigem.setNumeroConta("123456");

        when(clienteRepository.findByNumeroConta("123456")).thenReturn(Optional.of(clienteOrigem));
        when(clienteRepository.findByNumeroConta("654321")).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transferenciaService.realizarTransferencia("123456", "654321", 500.0);
        });

        assertEquals("Conta de destino nao encontrada.", exception.getMessage());
        verify(transferenciaRepository, times(1)).save(any(Transferencia.class));  // A transferência falhada foi registrada
    }
}
