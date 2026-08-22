package br.com.senai.autoescolas164.service;

import br.com.senai.autoescolas164.domain.instrutor.DadosAtualizacaoInstrutor;
import br.com.senai.autoescolas164.domain.instrutor.DadosCadastroInstrutor;
import br.com.senai.autoescolas164.domain.instrutor.DadosDetalhamentoInstrutor;
import br.com.senai.autoescolas164.domain.instrutor.DadosListagemInstrutor;
import br.com.senai.autoescolas164.domain.instrutor.Instrutor;
import br.com.senai.autoescolas164.domain.instrutor.InstrutorRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InstrutorService {

    private final InstrutorRepository repository;

    @Transactional
    public DadosDetalhamentoInstrutor cadastrarInstrutor(
            DadosCadastroInstrutor dados) {

        Instrutor instrutor = new Instrutor(dados);

        Instrutor salvo = repository.save(instrutor);

        return new DadosDetalhamentoInstrutor(salvo);
    }

    @Transactional(readOnly = true)
    public Page<DadosListagemInstrutor> listarInstrutores(
            Pageable pageable) {

        return repository
                .findAllByAtivoTrue(pageable)
                .map(DadosListagemInstrutor::new);
    }

    @Transactional(readOnly = true)
    public DadosDetalhamentoInstrutor detalharInstrutor(Long id) {

        Instrutor instrutor = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Id não informado não existe"
                        )
                );

        return new DadosDetalhamentoInstrutor(instrutor);
    }

    @Transactional
    public @Nullable DadosDetalhamentoInstrutor atualizarInstrutor(
            DadosAtualizacaoInstrutor dados) {

        Instrutor instrutor = repository.findById(dados.id())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Id não informado não existe"
                        )
                );

        instrutor.atualizar(dados);

        Instrutor salvo = repository.save(instrutor);

        return new DadosDetalhamentoInstrutor(salvo);
    }

    @Transactional
    public void excluirInstrutor(Long id) {

        Instrutor instrutor = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Id não informado não existe"
                        )
                );

        instrutor.excluir();

        repository.save(instrutor);
    }
}