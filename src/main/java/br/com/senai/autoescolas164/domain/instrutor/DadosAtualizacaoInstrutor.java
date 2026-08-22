package br.com.senai.autoescolas164.domain.instrutor;

import br.com.senai.autoescolas164.domain.endereco.DadosEndereco;

public record DadosAtualizacaoInstrutor(
        Long id,
        String nome,
        String email,
        String telefone,
        Especialidade especialidade,
        DadosEndereco endereco) {
}