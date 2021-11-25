package br.com.xbrain.vendasteste.vendas.controller;

import br.com.xbrain.vendasteste.vendas.dto.VendedorDto;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:vendedores.sql", "classpath:vendas.sql"})
@Transactional
public class VendedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void deveRetornarTodosOsVendedores() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/vendedor")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(4)));
    }

    @Test
    public void retornaVendedoresPorId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/vendedor/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome", Matchers.is("Marcos Vendedor Um")));
    }

    @Test
    public void deveSalvarVendedor() throws Exception {
        VendedorDto vendedor = new VendedorDto();
        vendedor.setNome("Marcos Vendedor Teste");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/vendedor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(vendedor)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void naoDeveSalvarUmVendedorSemNome() throws Exception {
        VendedorDto vendedor = new VendedorDto();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/vendedor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(vendedor)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void deveAlterarVendedor() throws Exception  {
        VendedorDto vendedor = new VendedorDto();
        vendedor.setId(1L);
        vendedor.setNome("Marcos Vendedor Atualizado");
        mockMvc.perform(MockMvcRequestBuilders.put("/api/vendedor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(vendedor)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveDeletarUmVendedor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/vendedor/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveRetornarRelatorio() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/vendedor/relatorio?dataInicio=2021-11-18&dataFim=2021-11-20"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nomeDoVendedor", Matchers.is("Marcos Vendedor Um")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].totalDeVendas", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].mediaDeVendasDiaria", Matchers.is(1.0)));
    }

}



