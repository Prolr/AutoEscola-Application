package br.com.senai.autoescolas164.adapter.in.controller.response.instrutor;

import br.com.senai.autoescolas164.application.core.domain.Instrutor;
import br.com.senai.autoescolas164.shared.vo.endereco.Endereco;
import br.com.senai.autoescolas164.shared.vo.enumeration.Especialidade;

public record DadosDetalhamentoInstrutor(
        Long id,
        String nome,
        String email,
        String telefone,
        String cnh,
        Especialidade especialidade,
        Endereco endereco,
        boolean ativo) {
    public DadosDetalhamentoInstrutor(Instrutor instrutor) {
        this(
                instrutor.getId(),
                instrutor.getNome(),
                instrutor.getEmail(),
                instrutor.getTelefone(),
                instrutor.getCnh(),
                instrutor.getEspecialidade(),
                instrutor.getEndereco(),
                instrutor.isAtivo()
        );
    }
}