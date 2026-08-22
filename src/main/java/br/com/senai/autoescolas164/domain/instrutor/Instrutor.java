package br.com.senai.autoescolas164.domain.instrutor;

import br.com.senai.autoescolas164.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Instrutor")
@Table(name = "instrutores")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Instrutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cnh;
    private boolean ativo = true;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Instrutor(DadosCadastroInstrutor dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cnh = dados.cnh();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizar(DadosAtualizacaoInstrutor dados) {
        if (dados.nome() != null && !dados.nome().isBlank()) {
            this.nome = dados.nome();
        }
        if (dados.email() != null && !dados.email().isBlank()) {
            this.email = dados.email();
        }
        if (dados.telefone() != null && !dados.telefone().isBlank()) {
            this.telefone = dados.telefone();
        }
        if (dados.especialidade() != null) {
            this.especialidade = dados.especialidade();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizar(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}