package br.com.xbrain.vendasteste.vendas.controller;

import br.com.xbrain.vendasteste.vendas.dto.VendaDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:vendedores.sql", "classpath:vendas.sql"})
@Transactional

public class VendaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void deveSalvarVenda() throws Exception {
        VendaDto venda = new VendaDto();
        venda.setValorDaVenda(new BigDecimal("99.50"));
        venda.setVendedorId(1L);
        venda.setDataDaVenda(LocalDate.of(2021, 11, 15));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/venda")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(venda)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void retornaVendasPorId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/venda/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.valorDaVenda", Matchers.is(150.00)));
    }

    @Test
    public void deveRetornarTodasAsVendas() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/venda")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(4)));
    }

    @Test
    public void deveAlterarVenda() throws Exception  {
        VendaDto venda = new VendaDto();
        venda.setVendedorId(1L);
        venda.setId(1L);
        venda.setValorDaVenda(new BigDecimal("109.90"));
        venda.setDataDaVenda(LocalDate.of(2021,11,24));
        mockMvc.perform(MockMvcRequestBuilders.put("/api/venda")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(venda)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveDeletarUmaVenda() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/venda/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
