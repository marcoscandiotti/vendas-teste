package br.com.xbrain.vendasteste.vendas.service;


import br.com.xbrain.vendasteste.vendas.domain.Venda;
import br.com.xbrain.vendasteste.vendas.domain.Vendedor;
import br.com.xbrain.vendasteste.vendas.dto.VendaDto;
import br.com.xbrain.vendasteste.vendas.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private VendedorService vendedorService;

    public void salvarVenda(VendaDto vendaDto) {
        Venda venda = Venda.converter(vendaDto);
        Vendedor vendedor = Vendedor.converter(vendedorService.findById(vendaDto.getVendedorId()));
        venda.setVendedor(vendedor);
        vendaRepository.save(venda);
    }

    public void alterarVenda(VendaDto venda) {
        Venda vendaAlterada = vendaRepository.findById(venda.getId()).get();
        vendaAlterada.setValorDaVenda(venda.getValorDaVenda());
        vendaAlterada.setDataDaVenda(venda.getDataDaVenda());
        vendaAlterada.setId(venda.getId());
        vendaRepository.save(vendaAlterada);
    }

    public void deletarVenda(Long id) {
        Venda deletarVenda = vendaRepository.findById(id).get();
        vendaRepository.delete(deletarVenda);
    }

    public VendaDto findById(Long id) {
        Venda venda = vendaRepository.findById(id).get();
        return VendaDto.converter(venda);
    }

    public List<VendaDto> findAll() {
        return vendaRepository.findAll()
                .stream()
                .map(VendaDto::converter)
                .collect(Collectors.toList());
    }
}
