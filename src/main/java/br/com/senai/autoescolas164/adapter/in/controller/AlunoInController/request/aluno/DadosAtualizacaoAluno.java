package br.com.senai.autoescolas164.adapter.in.controller.AlunoInController.request.aluno;

import br.com.senai.autoescolas164.shared.vo.endereco.dto.DadosEndereco;
import br.com.senai.autoescolas164.shared.vo.enumeration.Especialidade;

public record DadosAtualizacaoAluno(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        DadosEndereco endereco,
        boolean ativo
){
}