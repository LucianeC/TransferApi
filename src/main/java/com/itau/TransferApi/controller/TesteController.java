package com.itau.TransferApi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {

    @GetMapping("/Teste")
    public String home() {
        return "8080 - Started";
    }
}