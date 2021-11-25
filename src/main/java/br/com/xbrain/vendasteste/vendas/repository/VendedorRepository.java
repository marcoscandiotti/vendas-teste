package br.com.xbrain.vendasteste.vendas.repository;

import br.com.xbrain.vendasteste.vendas.domain.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {


    @Query("SELECT DISTINCT v FROM Vendedor v LEFT JOIN FETCH v.vendas ve WHERE ve.dataDaVenda BETWEEN ?1 AND ?2")
    List<Vendedor> findByPeriodo(LocalDate dataInicio, LocalDate dataFim);

}
