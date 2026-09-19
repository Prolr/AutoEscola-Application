

import br.com.senai.autoescolas164.adapter.out.repository.entity.AlunoEntity;
import br.com.senai.autoescolas164.adapter.out.repository.entity.InstrucaoEntity;
import br.com.senai.autoescolas164.adapter.out.repository.entity.InstrutorEntity;
import br.com.senai.autoescolas164.adapter.out.repository.persistence.InstrutorJpaRepository;
import br.com.senai.autoescolas164.shared.vo.endereco.Endereco;
import br.com.senai.autoescolas164.shared.vo.enumeration.Especialidade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class InstrutorJpaRepositoryTest {

    @Autowired
    private InstrutorJpaRepository repository;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    @DisplayName("Deve retornar null quando instrutor não está disponível")
    void escolherInstrutorAleatorioDisponivelCenario1() {

        // Arrange
        LocalDateTime proximaSegundaAs10 = LocalDateTime
                .now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .withHour(10)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);


        // Cadastrar aluno
        AlunoEntity aluno = cadastrarAluno(
                "Aluno Teste",
                "alunoteste@gmail.com",
                "(11) 98765-4321",
                "12345678901"
        );


        // Cadastrar instrutor
        InstrutorEntity instrutor = cadastrarInstrutor(
                "Instrutor Teste",
                "instrutorteste@gmail.com",
                "(11) 99999-9999",
                "123456789",
                Especialidade.MOTOS
        );


        // Agendar instrução
        agendarInstrucao(
                aluno,
                instrutor,
                proximaSegundaAs10
        );


        // Act
        InstrutorEntity instrutorDisponivel =
                repository.escolherInstrutorAleatorioDisponivel(
                        Especialidade.MOTOS,
                        proximaSegundaAs10
                );


        // Assert
        assertThat(instrutorDisponivel).isNull();
    }


    private Endereco dadosEndereco() {

        return new Endereco(
                "Rua Xiter",
                "000",
                "Casa dos fundos",
                "Vila Restian",
                "TestCity",
                "AB",
                "3474020"
        );
    }


    private AlunoEntity cadastrarAluno(
            String nome,
            String email,
            String telefone,
            String cpf
    ) {

        AlunoEntity aluno = new AlunoEntity(
                null,
                nome,
                email,
                telefone,
                cpf,
                dadosEndereco(),
                true
        );

        return entityManager.persistFlushFind(aluno);
    }


    private InstrutorEntity cadastrarInstrutor(
            String nome,
            String email,
            String telefone,
            String cnh,
            Especialidade especialidade
    ) {

        InstrutorEntity instrutor = new InstrutorEntity(
                null,
                nome,
                email,
                telefone,
                cnh,
                true,
                especialidade,
                dadosEndereco()
        );

        return entityManager.persistFlushFind(instrutor);
    }


    private void agendarInstrucao(
            AlunoEntity aluno,
            InstrutorEntity instrutor,
            LocalDateTime dataHora
    ) {

        InstrucaoEntity instrucao = new InstrucaoEntity(
                null,
                aluno,
                instrutor,
                dataHora
        );

        entityManager.persistFlushFind(instrucao);
    }
}