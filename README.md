# 🕒 Manager Hours API (maneger_hours)

Uma API RESTful desenvolvida em Spring Boot para o gerenciamento de agendamentos de horários. Ideal para estabelecimentos que precisam controlar marcações de clientes com profissionais específicos, evitando conflitos de horários.

## 🛠️ Tecnologias Utilizadas

* **Java 25**: Linguagem principal.
* **Spring Boot 4.0.2**: Framework base para a API REST.
* **Spring Data JPA / Hibernate**: Para o mapeamento objeto-relacional e persistência de dados.
* **H2 Database**: Banco de dados em memória (ideal para desenvolvimento e testes rápidos).
* **Lombok**: Redução de boilerplate (getters, setters, construtores).
* **Maven**: Gerenciador de dependências.

## 🚀 Como Executar o Projeto

1. Certifique-se de ter o **Java 25** e o **Maven** instalados na sua máquina.
2. Clone este repositório.
3. Abra o terminal na pasta raiz do projeto.
4. Execute o comando para baixar as dependências e iniciar a aplicação:
   ```bash
   ./mvnw spring-boot:run

A API estará disponível em: `http://localhost:8080`

## 📡 Endpoints da API

Abaixo estão as rotas disponíveis no sistema para gerenciar os agendamentos. A URL base é `/agendamentos`.

### 1. Criar Agendamento
* **Rota:** `POST /agendamentos`
* **Descrição:** Salva um novo agendamento validando se o horário já não está ocupado (regra de 1 hora de duração).
* **Body (JSON):**
```json
{
  "servico": "Cabelo",
  "profissional": "Barbeiro",
  "dataHoraAgendamento": "2026-02-14T11:00",
  "cliente": "Jorge",
  "telefoneCliente": "8455795584625"
}
````
### 2. Buscar Agendamentos por Dia
* **Rota:** GET /agendamentos
* **Parâmetros (Query):** ?data=YYYY-MM-DD
* **Descrição:** Retorna todos os agendamentos marcados para a data específica informada (ex: 2026-02-14).

### 3. Atualizar Agendamento
* **Rota:** PUT /agendamentos
* **Parâmetros (Query):** ?cliente={nome}&dataHoraAgendamento={data-e-hora}
* **Descrição:** Busca um agendamento existente pelo nome do cliente e horário atual, e atualiza os dados com base no JSON enviado no corpo da requisição.
* **Body (JSON):** Enviar os novos dados do agendamento (mesmo formato do POST).



### 4. Deletar Agendamento
* **Rota:** PUT DELETE /agendamentos
* **Parâmetros (Query):** ?cliente={nome}&dataHoraAgendamento={data-e-hora}
* **Descrição:** Remove o agendamento do banco de dados correspondente ao cliente e horário informados.

### 🐛 Problemas e Soluções (Follow the Data Flow)
Seção dedicada ao registro de erros solucionados durante o desenvolvimento:

1. Erro 500 (Internal Server Error) no PUT

Causa: O código lançava uma RuntimeException genérica quando um agendamento não era encontrado, o que o Spring Boot interpreta como falha do servidor.

Solução: Substituído por ResponseStatusException(HttpStatus.NOT_FOUND), garantindo que o cliente receba um erro 404 (semântico) em vez de um erro interno do servidor.

2. Erro 400 (Bad Request) na Deserialização de Data

Causa: Incompatibilidade entre o formato da String enviada pelo Postman (sem segundos) e a exigência padrão do LocalDateTime.

Solução: Adicionada a anotação @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm") no atributo dataHoraAgendamento para alinhar a entrada de dados com a lógica de negócio.
