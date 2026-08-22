package br.com.senai.autoescolas164.domain.usuario;

public record DadosDetalhamentoUsuario(
        Long id,
        String login,
        String perfil,
        boolean ativo
) {

    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getPerfil().name(),
                usuario.isEnabled()
        );
    }
}