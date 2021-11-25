package br.com.xbrain.vendasteste.vendas.domain;


import br.com.xbrain.vendasteste.vendas.dto.VendaDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "venda")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Venda {

    @Id
    @GeneratedValue(generator = "seqvenda")
    @SequenceGenerator(name = "seqvenda", sequenceName = "seqvenda", allocationSize = 1)
    @Column(nullable = false)
    private Long id;

    private BigDecimal valorDaVenda;
    private LocalDate dataDaVenda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id")
    @JsonBackReference
    private Vendedor vendedor;

    public static Venda converter(VendaDto dto) {
        Venda venda = new Venda();
        venda.setDataDaVenda(dto.getDataDaVenda());
        venda.setValorDaVenda(dto.getValorDaVenda());
        return venda;
    }

}

