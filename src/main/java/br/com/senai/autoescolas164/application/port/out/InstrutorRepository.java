package br.com.senai.autoescolas164.application.port.out;

import br.com.senai.autoescolas164.application.core.domain.Instrutor;
import br.com.senai.autoescolas164.shared.vo.enumeration.Especialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface InstrutorRepository extends JpaRepository<Instrutor, Long> {
    Page<Instrutor> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
                SELECT i FROM Instrutor i
                WHERE
                i.ativo = TRUE
                AND
                i.especialidade = :especialidade
                AND
                i.id NOT IN(
                        SELECT a.instrutor.id FROM Instrucao a
                        WHERE
                        a.dataHora = :dataHora
                    )
                    ORDER BY rand()
                    LIMIT 1
            """)
    Instrutor escolherInstrutorAleatorioDisponivel(Especialidade especialidade, LocalDateTime dataHora);

    boolean existsByIdAndAtivoFalse(Long id);
}