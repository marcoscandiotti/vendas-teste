package br.com.xbrain.vendasteste.vendas.service;


import br.com.xbrain.vendasteste.vendas.domain.Vendedor;
import br.com.xbrain.vendasteste.vendas.dto.RelatorioDto;
import br.com.xbrain.vendasteste.vendas.dto.VendedorDto;
import br.com.xbrain.vendasteste.vendas.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    public List<RelatorioDto> getDados(LocalDate dataInicio, LocalDate dataFim) {
        List<Vendedor> vendedor = vendedorRepository.findByPeriodo(dataInicio, dataFim);

        long dias = DAYS.between(dataInicio, dataFim) + 1;

        return vendedor.stream()
                .map(v -> new RelatorioDto(v.getNome(), v.getVendas().size(), (double) v.getVendas().size() / dias))
                .collect(Collectors.toList());
    }

    public List<VendedorDto> findAll() {
        return vendedorRepository.findAll()
                .stream()
                .map(VendedorDto::converter)
                .collect(Collectors.toList());
    }

    public VendedorDto findById(Long id) {
        Vendedor vendedor = vendedorRepository.findById(id).get();
        return VendedorDto.converter(vendedor);

    }

    public void salvarVendedor(VendedorDto vendedor) {
        vendedorRepository.save(Vendedor.converter(vendedor));
    }

    public void alterarVendedor(VendedorDto vendedor) {
        Vendedor vendedorAlterado = vendedorRepository.findById(vendedor.getId()).get();
        vendedorAlterado.setNome(vendedor.getNome());

        vendedorRepository.save(vendedorAlterado);
    }

    public void deletar(Long id) {
        Vendedor deletarVendedor = vendedorRepository.findById(id).get();
        vendedorRepository.delete(deletarVendedor);
    }

}

