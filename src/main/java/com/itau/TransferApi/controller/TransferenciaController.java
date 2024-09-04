package com.itau.TransferApi.controller;

import com.itau.TransferApi.model.Transferencia;
import com.itau.TransferApi.service.TransferenciaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transferencias")
@Api(value = "TransferenciaController", tags = {"Transferência"}, description = "APIs relacionadas a Transferências")

public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @ApiOperation(value = "Realiza uma transferência", response = Transferencia.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Transferência realizada com sucesso"),
            @ApiResponse(code = 400, message = "Erro nos dados de entrada")
    })
    @PostMapping
    public ResponseEntity<Transferencia> realizarTransferencia(
            @RequestParam String numeroContaOrigem,
            @RequestParam String numeroContaDestino,
            @RequestParam Double valor) {

        try {
            Transferencia transferencia = transferenciaService.realizarTransferencia(numeroContaOrigem, numeroContaDestino, valor);
            return ResponseEntity.status(HttpStatus.CREATED).body(transferencia);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @ApiOperation(value = "Lista transferências relacionadas a uma conta", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Transferências listadas com sucesso")
    })
    @GetMapping("/{numeroConta}")
    public ResponseEntity<List<Transferencia>> listarTransferenciasPorConta(@PathVariable String numeroConta) {
        List<Transferencia> transferencias = transferenciaService.buscarTransferenciasPorNumeroConta(numeroConta);
        return ResponseEntity.ok(transferencias);
    }
}
