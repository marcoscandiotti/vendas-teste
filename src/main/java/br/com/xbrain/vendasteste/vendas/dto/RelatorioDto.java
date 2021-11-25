package br.com.xbrain.vendasteste.vendas.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RelatorioDto {

    private String nomeDoVendedor;
    private Integer totalDeVendas;
    private Double mediaDeVendasDiaria;

}
