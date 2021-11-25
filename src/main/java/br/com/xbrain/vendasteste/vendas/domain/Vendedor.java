package br.com.xbrain.vendasteste.vendas.domain;


import br.com.xbrain.vendasteste.vendas.dto.VendedorDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vendedor")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vendedor {

    @Id
    @GeneratedValue(generator = "seqvendedor")
    @SequenceGenerator(name = "seqvendedor", sequenceName = "seqvendedor", allocationSize = 1)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false,length = 255)
    private String nome;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "vendedor",
            orphanRemoval = true)

    @Singular
   private List<Venda> vendas;


    public static Vendedor converter(VendedorDto dto) {
        Vendedor vendedor = new Vendedor();
        vendedor.setNome(dto.getNome());
        vendedor.setId(dto.getId());
        return vendedor;
    }

}
