package br.com.xbrain.vendasteste.vendas.dto;


import br.com.xbrain.vendasteste.vendas.domain.Venda;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class VendaDto {

    private Long id;

    @NotNull(message = "O campo Valor da Venda é obrigatório.")
    private BigDecimal valorDaVenda;

    @NotNull(message = "O campo Data da Venda é obrigatório.")
    private LocalDate dataDaVenda;

    @NotNull(message = "O campo Vendedor é obrigatório.")
    private Long vendedorId;

    public static VendaDto converter(Venda venda){
        VendaDto dto = new VendaDto();
        dto.setId(venda.getId());
        dto.setValorDaVenda(venda.getValorDaVenda());
        dto.setDataDaVenda(venda.getDataDaVenda());
        return dto;
    }

}
