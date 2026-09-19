package br.com.senai.autoescolas164.adapter.out.repository.mapper;

import br.com.senai.autoescolas164.adapter.out.repository.entity.InstrucaoEntity;
import br.com.senai.autoescolas164.application.core.domain.Instrucao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstrucaoEntityMapper {
    private final InstrutorEntityMapper instrutorEntityMapper;
    private final AlunoEntityMapper alunoEntityMapper;

    public Instrucao toDomain(InstrucaoEntity entity) {
        return new Instrucao(
                entity.getId(),
                alunoEntityMapper.toDomain(entity.getAluno()),
                instrutorEntityMapper.toDomain(entity.getInstrutor()),
                entity.getDataHora()
        );
    }

    public InstrucaoEntity toEntity(Instrucao domain) {
        return new InstrucaoEntity(
                domain.getId(),
                alunoEntityMapper.toEntity(domain.getAluno()),
                instrutorEntityMapper.toEntity(domain.getInstrutor()),
                domain.getDataHora()
        );
    }
}