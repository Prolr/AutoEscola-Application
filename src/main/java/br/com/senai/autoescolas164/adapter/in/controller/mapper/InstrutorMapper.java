package br.com.senai.autoescolas164.adapter.in.controller.mapper;

import br.com.senai.autoescolas164.adapter.in.controller.request.instrutor.DadosCadastroInstrutor;
import br.com.senai.autoescolas164.application.core.domain.Instrutor;
import br.com.senai.autoescolas164.shared.vo.endereco.Endereco;
import br.com.senai.autoescolas164.shared.vo.endereco.mapper.EnderecoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstrutorMapper {
    private final EnderecoMapper enderecoMapper;

    public Instrutor toDomain(DadosCadastroInstrutor dados) {
        return new Instrutor(
                null,
                dados.nome(),
                dados.email(),
                dados.telefone(),
                dados.cnh(),
                true,
                dados.especialidade(),
                enderecoMapper.toEndereco(dados.endereco())
        );
    }
}