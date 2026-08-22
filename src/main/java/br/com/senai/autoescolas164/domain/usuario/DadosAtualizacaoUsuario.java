package br.com.senai.autoescolas164.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUsuario(
        @NotNull
        Role perfil
) {
}