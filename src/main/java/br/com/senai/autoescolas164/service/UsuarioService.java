package br.com.senai.autoescolas164.service;

import br.com.senai.autoescolas164.domain.usuario.*;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public DadosDetalhamentoUsuario cadastrar(
            DadosCadastroUsuario dados) {

        if (repository.existsByLogin(dados.login())) {
            throw new RuntimeException("Login já cadastrado");
        }

        String senhaCriptografada =
                passwordEncoder.encode(dados.senha());

        Usuario usuario = new Usuario(
                dados,
                senhaCriptografada
        );

        Usuario salvo = repository.save(usuario);

        return new DadosDetalhamentoUsuario(salvo);
    }

    @Transactional(readOnly = true)
    public List<DadosListagemUsuario> listar() {

        return repository.findAll()
                .stream()
                .map(DadosListagemUsuario::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public DadosDetalhamentoUsuario detalhar(Long id) {

        Usuario usuario = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Id não informado não existe"
                        )
                );

        return new DadosDetalhamentoUsuario(usuario);
    }

    @Transactional
    public @Nullable DadosDetalhamentoUsuario atualizarPerfil(
            Long id,
            DadosAtualizacaoUsuario dados) {

        Usuario usuario = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Id não informado não existe"
                        )
                );

        usuario.atualizarPerfil(dados.perfil());

        Usuario salvo = repository.save(usuario);

        return new DadosDetalhamentoUsuario(salvo);
    }

    @Transactional
    public void alterarSenha(
            Long id,
            DadosAlteracaoSenha dados) {

        Usuario usuario = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Id não informado não existe"
                        )
                );

        boolean senhaCorreta = passwordEncoder.matches(
                dados.senhaAtual(),
                usuario.getPassword()
        );

        if (!senhaCorreta) {
            throw new RuntimeException(
                    "Senha atual incorreta"
            );
        }

        String senhaCriptografada =
                passwordEncoder.encode(dados.novaSenha());

        usuario.alterarSenha(senhaCriptografada);

        Usuario salvo = repository.save(usuario);
    }

    @Transactional
    public void excluir(Long id) {

        Usuario usuario = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Id não informado não existe"
                        )
                );

        repository.delete(usuario);
    }
}