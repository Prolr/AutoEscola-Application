package br.com.senai.autoescolas164.adapter.in.controller.UsuarioInController.request.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosLogin(
        @NotBlank
        String login,

        @NotBlank
        String senha) {
}