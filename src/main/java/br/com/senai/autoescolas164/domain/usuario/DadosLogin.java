package br.com.senai.autoescolas164.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosLogin(
        @NotBlank
        String login,

        @NotBlank
        String senha) {
}