package com.itau.TransferApi.controller;

import com.itau.TransferApi.model.Cliente;
import com.itau.TransferApi.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/api/v1/clientes")
@Api(value = "ClienteController", tags = {"Cliente"}, description = "APIs relacionadas a Clientes")

public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @ApiOperation(value = "Cadastra um novo cliente", response = Cliente.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente cadastrado com sucesso"),
            @ApiResponse(code = 400, message = "Erro nos dados de entrada")
    })

    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteService.cadastrarCliente(cliente);
        return ResponseEntity.status(HttpStatus.OK).body(novoCliente);
    }

    @ApiOperation(value = "Lista todos os clientes", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Clientes listados com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }


    @ApiOperation(value = "Busca um cliente pelo número da conta", response = Cliente.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente encontrado"),
            @ApiResponse(code = 404, message = "Cliente não encontrado")
    })
    @GetMapping("/{numeroConta}")
    public ResponseEntity<Cliente> buscarClientePorNumeroConta(@PathVariable String numeroConta) {
        Optional<Cliente> cliente = clienteService.buscarClientePorNumeroConta(numeroConta);
        return cliente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
