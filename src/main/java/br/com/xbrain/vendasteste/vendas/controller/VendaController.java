package br.com.xbrain.vendasteste.vendas.controller;

import br.com.xbrain.vendasteste.vendas.dto.VendaDto;
import br.com.xbrain.vendasteste.vendas.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping
    public void salvarVenda(@RequestBody @Valid VendaDto vendaDto) {
        vendaService.salvarVenda(vendaDto);
    }

    @PutMapping
    public void atualizarVenda(@RequestBody VendaDto venda) {
        vendaService.alterarVenda(venda);
    }

    @DeleteMapping("{id}")
    public void deletarVenda(@PathVariable Long id) {
        vendaService.deletarVenda(id);
    }

    @GetMapping("{id}")
    public VendaDto findById(@PathVariable Long id) {
        return vendaService.findById(id);
    }


    @GetMapping
    public List<VendaDto> findAll() {
        return vendaService.findAll();
    }
}




