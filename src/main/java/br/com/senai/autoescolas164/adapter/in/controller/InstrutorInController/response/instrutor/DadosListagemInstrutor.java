package br.com.senai.autoescolas164.adapter.in.controller.InstrutorInController.response.instrutor;

import br.com.senai.autoescolas164.shared.vo.enumeration.Especialidade;

public record DadosListagemInstrutor(
        Long id,
        String nome,
        String email,
        Especialidade especialidade) {
}