package com.itau.TransferApi.controller;

import com.itau.TransferApi.model.Cliente;
import com.itau.TransferApi.model.Transferencia;
import com.itau.TransferApi.service.ClienteService;
import com.itau.TransferApi.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/api/v1/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;


    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteService.cadastrarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{numeroConta}")
    public ResponseEntity<Cliente> buscarClientePorNumeroConta(@PathVariable String numeroConta) {
        Optional<Cliente> cliente = clienteService.buscarClientePorNumeroConta(numeroConta);
        return cliente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
