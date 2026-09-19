package br.com.senai.autoescolas164.adapter.out.repository.persistence;

import br.com.senai.autoescolas164.adapter.out.repository.entity.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoJpaRepository extends JpaRepository<AlunoEntity, Long> {
    boolean existsByIdAndAtivoFalse(Long id);
}