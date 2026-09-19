package br.com.senai.autoescolas164.application.port.out;

import br.com.senai.autoescolas164.application.core.domain.Aluno;
import br.com.senai.autoescolas164.application.core.domain.Instrucao;
import br.com.senai.autoescolas164.application.core.domain.Instrutor;
import br.com.senai.autoescolas164.shared.vo.enumeration.Especialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AlunoRepository {
    boolean existsByIdAndAtivoFalse(Long id);

    boolean existsById(Long id);

    Aluno getReferenceById(Long id);
    Aluno save(Aluno aluno);
    Page<Aluno> findAllByAtivoTrue(Pageable paginacao);
    Optional<Aluno> findById(Long id);

}