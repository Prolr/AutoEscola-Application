package br.com.senai.autoescolas164.application.core.domain;

import java.time.LocalDateTime;

public class Instrucao {
    private Long id;
    private Aluno aluno;
    private Instrutor instrutor;
    private LocalDateTime dataHora;

    public Instrucao() {
    }

    public Instrucao(Long id, Aluno aluno, Instrutor instrutor, LocalDateTime dataHora) {
        this.id = id;
        this.aluno = aluno;
        this.instrutor = instrutor;
        this.dataHora = dataHora;
    }

    public Long getId() {
        return id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Instrutor getInstrutor() {
        return instrutor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
}