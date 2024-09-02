package com.itau.TransferApi.controller;

import com.itau.TransferApi.model.Transferencia;
import com.itau.TransferApi.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transferencias")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;


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

    @GetMapping("/{numeroConta}")
    public ResponseEntity<List<Transferencia>> listarTransferenciasPorConta(@PathVariable String numeroConta) {
        List<Transferencia> transferencias = transferenciaService.buscarTransferenciasPorNumeroConta(numeroConta);
        return ResponseEntity.ok(transferencias);
    }
}
