package br.com.senai.autoescolas164.application.port.out;

import br.com.senai.autoescolas164.adapter.out.repository.entity.InstrucaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface InstrucaoRepository extends JpaRepository<InstrucaoEntity, Long> {

    boolean existsByInstrutorIdAndDataHora(
            Long idInstrutor,
            LocalDateTime dataHora
    );

    boolean existsByAlunoIdAndDataHoraBetween(
            Long idAluno,
            LocalDateTime inicio,
            LocalDateTime fim
    );
}