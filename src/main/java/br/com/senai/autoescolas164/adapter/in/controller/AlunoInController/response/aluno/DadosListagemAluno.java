package br.com.senai.autoescolas164.adapter.in.controller.AlunoInController.response.aluno;

public record DadosListagemAluno(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        boolean ativo
) {
}
