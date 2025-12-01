# Testes de Integração

Este documento descreve os testes de integração implementados nos serviços Imovato usando **Testcontainers** e **@SpringBootTest**.

## Visão Geral

Os testes de integração validam que cada serviço funciona corretamente com seus recursos reais locais:
- **Banco de Dados**: MongoDB (cadastral) e MySQL (transacional)
- **Mensageria**: RabbitMQ
- **APIs REST**: Controllers e endpoints

## Tecnologias Utilizadas

- **Testcontainers 1.19.3**: Provisiona containers Docker reais para testes
- **JUnit 5**: Framework de testes
- **AssertJ**: Asserções fluentes
- **Awaitility**: Testes assíncronos (transacional)
- **Spring Security Test**: Testes com autenticação mockada
- **MockMvc**: Testes de API REST

## Estrutura dos Testes

### Serviço Cadastral

**Localização**: `cadastral/src/test/java/com/unipampa/crud/integration/`

**Classes**:
- `BaseIntegrationTest.java`: Classe base com configuração dos containers
- `AccommodationIntegrationTest.java`: Testes de integração para acomodações

**Containers**:
- MongoDB 7.0
- RabbitMQ 3.12-management

**Cenários Testados**:
✅ Conexão com MongoDB e RabbitMQ  
✅ Salvamento de acomodações no MongoDB  
✅ Busca de todas as acomodações  
✅ Busca de acomodação por ID via API REST  
✅ Atualização de status de acomodação  
✅ Deleção de acomodação  
✅ Múltiplas acomodações para o mesmo anfitrião  
✅ Integridade de dados da acomodação  
✅ Retorno 404 quando acomodação não encontrada  

### Serviço Transacional

**Localização**: `transacional/src/test/java/com/example/rent/integration/`

**Classes**:
- `BaseIntegrationTest.java`: Classe base com configuração dos containers
- `BookingIntegrationTest.java`: Testes de integração para reservas

**Containers**:
- MySQL 8.0
- RabbitMQ 3.12-management

**Cenários Testados**:
✅ Conexão com MySQL e RabbitMQ  
✅ Salvamento de reserva no MySQL  
✅ Busca de todas as reservas  
✅ Atualização de status de reserva  
✅ Deleção de reserva  
✅ Recebimento de mensagens do RabbitMQ (teste assíncrono)  
✅ Múltiplas reservas para a mesma acomodação  
✅ Validação de datas de reserva  
✅ Reserva com múltiplos hóspedes  
✅ Verificação de disponibilidade de acomodação  
✅ Integridade de dados da reserva  
✅ Deleção em cascata de hóspedes  

## Como Executar os Testes

### Pré-requisitos

1. **Docker**: Testcontainers requer Docker instalado e em execução
   ```bash
   # Verificar se Docker está rodando
   docker ps
   ```

2. **Java 21**: Versão do Java configurada no projeto

3. **Maven**: Para build e execução dos testes

### Executar Todos os Testes de Integração

#### Cadastral
```bash
cd cadastral
mvn test -Dtest=*IntegrationTest
```

#### Transacional
```bash
cd transacional
mvn test -Dtest=*IntegrationTest
```

### Executar Teste Específico

```bash
cd cadastral
mvn test -Dtest=AccommodationIntegrationTest

cd transacional
mvn test -Dtest=BookingIntegrationTest
```

### Executar Método de Teste Específico

```bash
cd cadastral
mvn test -Dtest=AccommodationIntegrationTest#shouldSaveAccommodationToMongoDB

cd transacional
mvn test -Dtest=BookingIntegrationTest#shouldReceiveAccommodationMessageFromRabbitMQ
```

### Executar com Relatório de Cobertura

```bash
cd cadastral
mvn clean verify

# Ver relatório: target/site/jacoco/index.html
```

## Configuração dos Testes

### Perfil de Teste

Os testes usam o perfil `test` com configurações específicas:

**cadastral/src/test/resources/application-test.yml**
**transacional/src/test/resources/application-test.yml**

### Propriedades Dinâmicas

As configurações de conexão com MongoDB, MySQL e RabbitMQ são injetadas dinamicamente pelos Testcontainers via `@DynamicPropertySource`.

### Reutilização de Containers

Os containers são configurados com `.withReuse(true)` para melhor performance, permitindo que múltiplos testes reutilizem o mesmo container.

## Padrões de Testes

### Estrutura Given-When-Then

```java
@Test
void shouldSaveAccommodationToMongoDB() {
    // Given - Preparação
    Accommodation saved = accommodationRepository.save(testAccommodation);

    // When - Execução
    Accommodation found = accommodationRepository.findById(saved.getId()).orElse(null);

    // Then - Validação
    assertThat(found).isNotNull();
    assertThat(found.getTitle()).isEqualTo("Apartamento Cobertura");
}
```

### Limpeza de Dados

```java
@BeforeEach
void setUp() {
    accommodationRepository.deleteAll();
    // Preparação dos dados de teste
}

@AfterEach
void tearDown() {
    accommodationRepository.deleteAll();
}
```

### Testes de API com Segurança

```java
@Test
@WithMockUser(roles = {"HOST"})
void shouldRetrieveAllAccommodationsViaAPI() throws Exception {
    mockMvc.perform(get("/accommodations"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray());
}
```

### Testes Assíncronos (RabbitMQ)

```java
@Test
void shouldReceiveAccommodationMessageFromRabbitMQ() {
    // When - Publica mensagem
    rabbitTemplate.convertAndSend(queue, message);

    // Then - Aguarda processamento assíncrono
    await().atMost(5, TimeUnit.SECONDS).untilAsserted(() -> {
        assertThat(repository.findById(id)).isPresent();
    });
}
```

## Troubleshooting

### Docker não está rodando
```
org.testcontainers.dockerclient.DockerClientProvisionException
```
**Solução**: Inicie o Docker Desktop

### Porta já em uso
```
Container startup failed
```
**Solução**: Pare containers conflitantes ou use `.withReuse(false)`

### Timeout em testes assíncronos
```
ConditionTimeoutException
```
**Solução**: Aumente o timeout em `await().atMost(...)`

### Erros de Schema no MySQL
```
Table doesn't exist
```
**Solução**: Verifique `spring.jpa.hibernate.ddl-auto=create-drop` no application-test.yml

## Boas Práticas

1. **Isolamento**: Cada teste deve ser independente
2. **Limpeza**: Sempre limpe dados antes/depois dos testes
3. **Nomenclatura**: Use nomes descritivos (should...When...)
4. **Asserções**: Use AssertJ para asserções fluentes
5. **Performance**: Reutilize containers quando possível
6. **Cobertura**: Teste cenários de sucesso e falha

## Métricas de Cobertura

Execute os testes com JaCoCo para gerar relatórios de cobertura:

```bash
mvn clean verify
```

Visualize o relatório em:
- Cadastral: `cadastral/target/site/jacoco/index.html`
- Transacional: `transacional/target/site/jacoco/index.html`

## Integração com CI/CD

Os testes de integração podem ser executados em pipelines:

```yaml
# Exemplo GitHub Actions
- name: Run Integration Tests
  run: |
    mvn test -Dtest=*IntegrationTest
```

**Nota**: Certifique-se de que o ambiente CI tenha Docker disponível.

## Referências

- [Testcontainers Documentation](https://www.testcontainers.org/)
- [Spring Boot Testing](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.testing)
- [AssertJ Documentation](https://assertj.github.io/doc/)
- [Awaitility Documentation](https://github.com/awaitility/awaitility)
