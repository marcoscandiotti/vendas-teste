package br.com.xbrain.vendasteste.vendas.controller;

import br.com.xbrain.vendasteste.vendas.dto.RelatorioDto;
import br.com.xbrain.vendasteste.vendas.dto.VendedorDto;
import br.com.xbrain.vendasteste.vendas.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/vendedor")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    @GetMapping
    public List<VendedorDto> findAll() {
        return vendedorService.findAll();
    }

    @GetMapping("{id}")
    public VendedorDto findById(@PathVariable Long id) {
        return vendedorService.findById(id);
    }

    @PostMapping
    public void salvarVendedor(@RequestBody @Valid VendedorDto vendedor) {
        vendedorService.salvarVendedor(vendedor);
    }

    @PutMapping
    public void alterarVendedor(@RequestBody @Valid VendedorDto vendedor) {
        vendedorService.alterarVendedor(vendedor);
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable Long id) {
        vendedorService.deletar(id);
    }

    @GetMapping("/relatorio")
    public List<RelatorioDto> getDados(
            @RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam("dataFim") @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
       return vendedorService.getDados(dataInicio, dataFim);
    }

}






