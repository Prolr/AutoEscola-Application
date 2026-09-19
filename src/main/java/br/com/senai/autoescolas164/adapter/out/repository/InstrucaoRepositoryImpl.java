package br.com.senai.autoescolas164.adapter.out.repository;

import br.com.senai.autoescolas164.adapter.out.repository.entity.InstrucaoEntity;
import br.com.senai.autoescolas164.adapter.out.repository.mapper.InstrucaoEntityMapper;
import br.com.senai.autoescolas164.adapter.out.repository.persistence.InstrucaoJpaRepository;
import br.com.senai.autoescolas164.application.core.domain.Instrucao;
import br.com.senai.autoescolas164.application.port.out.InstrucaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InstrucaoRepositoryImpl implements InstrucaoRepository {
    private final InstrucaoJpaRepository jpaRepository;
    private final InstrucaoEntityMapper entityMapper;

    @Override
    public boolean existsByInstrutorIdAndDataHora(Long idInstrutor, LocalDateTime dataHora) {
        return jpaRepository.existsByInstrutorIdAndDataHora(idInstrutor, dataHora);
    }

    @Override
    public boolean existsByAlunoIdAndDataHoraBetween(Long idAluno, LocalDateTime inicio, LocalDateTime fim) {
        return jpaRepository.existsByAlunoIdAndDataHoraBetween(idAluno, inicio, fim);
    }

    @Override
    public Instrucao save(Instrucao instrucao) {
        InstrucaoEntity entity = entityMapper.toEntity(instrucao);
        InstrucaoEntity salva = jpaRepository.save(entity);
        return entityMapper.toDomain(salva);
    }
}