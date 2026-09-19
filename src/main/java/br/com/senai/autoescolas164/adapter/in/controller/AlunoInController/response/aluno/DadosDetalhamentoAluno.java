package br.com.senai.autoescolas164.adapter.in.controller.AlunoInController.response.aluno;
import br.com.senai.autoescolas164.shared.vo.endereco.dto.DadosEndereco;


public record DadosDetalhamentoAluno(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        DadosEndereco endereco,
        boolean ativo) {
}