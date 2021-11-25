package br.com.xbrain.vendasteste.vendas.repository;
import br.com.xbrain.vendasteste.vendas.domain.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {


}
