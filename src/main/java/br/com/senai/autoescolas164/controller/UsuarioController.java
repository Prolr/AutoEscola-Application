package br.com.senai.autoescolas164.controller;

import br.com.senai.autoescolas164.domain.usuario.DadosAlteracaoSenha;
import br.com.senai.autoescolas164.domain.usuario.DadosAtualizacaoUsuario;
import br.com.senai.autoescolas164.domain.usuario.DadosCadastroUsuario;
import br.com.senai.autoescolas164.domain.usuario.DadosDetalhamentoUsuario;
import br.com.senai.autoescolas164.domain.usuario.DadosListagemUsuario;
import br.com.senai.autoescolas164.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoUsuario> cadastrar(
            @RequestBody @Valid DadosCadastroUsuario dados) {

        return ResponseEntity.ok(
                service.cadastrar(dados)
        );
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemUsuario>> listar() {

        return ResponseEntity.ok(
                service.listar()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoUsuario> detalhar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.detalhar(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoUsuario> atualizarPerfil(
            @PathVariable Long id,
            @RequestBody @Valid DadosAtualizacaoUsuario dados) {

        return ResponseEntity.ok(
                service.atualizarPerfil(id, dados)
        );
    }

    @PutMapping("/{id}/senha")
    public ResponseEntity<Void> alterarSenha(
            @PathVariable Long id,
            @RequestBody @Valid DadosAlteracaoSenha dados) {

        service.alterarSenha(id, dados);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        service.excluir(id);

        return ResponseEntity.noContent().build();
    }
}