package br.com.senai.autoescolas164.domain.usuario;

public record DadosListagemUsuario(
        Long id,
        String login,
        Role perfil
) {
    public DadosListagemUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getPerfil());
    }
}