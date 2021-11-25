package br.com.xbrain.vendasteste.vendas.dto;

import br.com.xbrain.vendasteste.vendas.domain.Vendedor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class VendedorDto {

    @NotNull(message = "Campo nome é obrigatório")
    private String nome;

    private Long id;

    public static VendedorDto converter(Vendedor vendedor){
        VendedorDto dto = new VendedorDto();
        dto.setNome(vendedor.getNome());
        dto.setId(vendedor.getId());
        return dto;
    }


}
