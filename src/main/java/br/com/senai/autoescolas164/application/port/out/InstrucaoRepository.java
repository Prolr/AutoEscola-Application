package br.com.senai.autoescolas164.application.port.out;

import br.com.senai.autoescolas164.application.core.domain.Instrucao;

import java.time.LocalDateTime;

public interface InstrucaoRepository {
    boolean existsByInstrutorIdAndDataHora(Long idInstrutor, LocalDateTime dataHora);

    boolean existsByAlunoIdAndDataHoraBetween(Long idAluno, LocalDateTime inicio, LocalDateTime fim);

    Instrucao save(Instrucao instrucao);
}