package br.com.senai.autoescolas164.shared.vo.endereco.mapper;

import br.com.senai.autoescolas164.shared.vo.endereco.Endereco;
import br.com.senai.autoescolas164.shared.vo.endereco.dto.DadosEndereco;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {
    public Endereco toEndereco(DadosEndereco dados) {
        return new Endereco(
                dados.logradouro(),
                dados.numero(),
                dados.complemento(),
                dados.bairro(),
                dados.cidade(),
                dados.uf(),
                dados.cep()
        );
    }
}