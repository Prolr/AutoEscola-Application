package br.com.senai.autoescolas164.service;

import br.com.senai.autoescolas164.domain.usuario.DadosLogin;
import br.com.senai.autoescolas164.domain.usuario.Usuario;
import br.com.senai.autoescolas164.infra.security.DadosTokenJWT;
import br.com.senai.autoescolas164.infra.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager manager;
    private final TokenService tokenService;

    public DadosTokenJWT efetuarLogin(DadosLogin dados) {

        var token = new UsernamePasswordAuthenticationToken(
                dados.login(),
                dados.senha()
        );

        Authentication authentication = manager.authenticate(token);

        String tokenJWT = tokenService.generateToken(
                (Usuario) authentication.getPrincipal()
        );

        return new DadosTokenJWT(tokenJWT);
    }
}