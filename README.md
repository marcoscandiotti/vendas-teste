#### vendas-teste

# README 

- Projeto de Teste para Criação de uma API de Vendas
- No projeto foi utilizado: Spring Boot, Lombok, Banco de Dados H2.
- Adicionado Métodos de Salvar, Deletar, Atualizar e Buscar Vendas e Vendedor.

Primeiro deve-se rodar a aplicação dentro da IDE. Após isso podemos acessar o banco de dados em memória pelo link: 

http://localhost:8081/h2-console

DriverClass: org.h2.Driver

JDBC URL: jdbc:h2:mem:testdb

Login: sa

Senha: password

---

## Utilização no Postman:

### Para Vendedor:

* Salvar Vendedor

**POST** - http://localhost:8081/api/vendedor 

Body: 
{  
   "nome": "Exemplo Nome"
}

---

* Buscar Vendedor (Todos)

**GET** - http://localhost:8081/api/vendedor/

---

* Buscar Vendedor (Por ID)

**GET** - http://localhost:8081/api/vendedor/{id}

---

* Deletar Vendedor

**DELETE** - http://localhost:8081/api/vendedor/{id}

---

* Atualizar Vendedor

**PUT** - http://localhost:8081/api/vendedor

---

---

### Para Vendas:

* Salvar Venda
**POST** - http://localhost:8081/api/venda

Body:
{ 
    "valorDaVenda": 150.00,
    "dataDaVenda": "2021-11-29",
    "vendedorId": 1
}

---

* Relatório de Vendas ( Data Informada por Parâmetro - Exibe o Nome dos Vendedores, Total de Vendas e a Média de Vendas Diária de Cada Vendedor ) 

**GET** - localhost:8081/api/vendedor/relatorio?dataInicio=2021-11-20&dataFim=2021-11-29

---

* Buscar Venda (Exibe Todas) 

**GET** - http://localhost:8081/api/venda/

---

* Buscar Venda (Por ID)

**GET** - http://localhost:8081/api/venda/{id}

---

* Atualiza Venda

**PUT** - http://localhost:8081/api/venda/{id}

---

* Deleta Venda

**DELETE** - http://localhost:8081/api/venda/{id}















