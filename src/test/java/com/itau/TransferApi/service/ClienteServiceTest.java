package com.itau.TransferApi.service;

import com.itau.TransferApi.model.Cliente;
import com.itau.TransferApi.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarClienteComSucesso() {
        Cliente cliente = new Cliente();
        cliente.setNumeroConta("123456");
        cliente.setNome("João");
        cliente.setSaldo(1000.0);

        when(clienteRepository.findByNumeroConta("123456")).thenReturn(Optional.empty());
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente novoCliente = clienteService.cadastrarCliente(cliente);

        assertNotNull(novoCliente);
        assertEquals("123456", novoCliente.getNumeroConta());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void deveLancarExcecaoQuandoNumeroDaContaJaExiste() {
        Cliente cliente = new Cliente();
        cliente.setNumeroConta("123456");

        when(clienteRepository.findByNumeroConta("123456")).thenReturn(Optional.of(cliente));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            clienteService.cadastrarCliente(cliente);
        });

        assertEquals("Numero da conta ja existe.", exception.getMessage());
        verify(clienteRepository, times(0)).save(cliente);
    }

    @Test
    void deveBuscarClientePorNumeroConta() {
        Cliente cliente = new Cliente();
        cliente.setNumeroConta("123456");

        when(clienteRepository.findByNumeroConta("123456")).thenReturn(Optional.of(cliente));

        Optional<Cliente> clienteEncontrado = clienteService.buscarClientePorNumeroConta("123456");

        assertTrue(clienteEncontrado.isPresent());
        assertEquals("123456", clienteEncontrado.get().getNumeroConta());
    }

    @Test
    void deveRetornarOptionalVazioSeClienteNaoExistir() {
        when(clienteRepository.findByNumeroConta("123456")).thenReturn(Optional.empty());

        Optional<Cliente> clienteEncontrado = clienteService.buscarClientePorNumeroConta("123456");

        assertFalse(clienteEncontrado.isPresent());
    }
}
