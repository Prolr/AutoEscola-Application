package br.com.senai.autoescolas164.controller;

import br.com.senai.autoescolas164.domain.usuario.DadosLogin;
import br.com.senai.autoescolas164.infra.security.DadosTokenJWT;
import br.com.senai.autoescolas164.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService service;

    @PostMapping
    public ResponseEntity<DadosTokenJWT> efetuarLogin(
            @RequestBody @Valid DadosLogin dados) {

        return ResponseEntity.ok(
                service.efetuarLogin(dados)
        );
    }
}