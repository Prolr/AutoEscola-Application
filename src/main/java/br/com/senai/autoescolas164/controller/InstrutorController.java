package br.com.senai.autoescolas164.controller;

import br.com.senai.autoescolas164.domain.instrutor.DadosAtualizacaoInstrutor;
import br.com.senai.autoescolas164.domain.instrutor.DadosCadastroInstrutor;
import br.com.senai.autoescolas164.domain.instrutor.DadosDetalhamentoInstrutor;
import br.com.senai.autoescolas164.domain.instrutor.DadosListagemInstrutor;
import br.com.senai.autoescolas164.service.InstrutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/instrutores")
@RequiredArgsConstructor
public class InstrutorController {

    private final InstrutorService service;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoInstrutor> cadastrarInstrutor(
            @RequestBody @Valid DadosCadastroInstrutor dados,
            UriComponentsBuilder uriBuilder) {

        DadosDetalhamentoInstrutor dto = service.cadastrarInstrutor(dados);

        URI uri = uriBuilder
                .path("/instrutores/{id}")
                .buildAndExpand(dto.id())
                .toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemInstrutor>> listarInstrutores(
            @PageableDefault(size = 10, sort = "nome")
            Pageable pageable) {

        Page<DadosListagemInstrutor> page =
                service.listarInstrutores(pageable);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoInstrutor> detalharInstrutor(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.detalharInstrutor(id)
        );
    }

    @PutMapping
    public ResponseEntity<DadosDetalhamentoInstrutor> atualizarInstrutor(
            @RequestBody @Valid DadosAtualizacaoInstrutor dados) {

        return ResponseEntity.ok(
                service.atualizarInstrutor(dados)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirInstrutor(
            @PathVariable Long id) {

        service.excluirInstrutor(id);

        return ResponseEntity.noContent().build();
    }
}