package com.example.rent.integration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

//infraestrutura pra rodar os testes
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //carrega todo sring boot, como se rodasse producao
@Testcontainers //inicia containers Docker antes dos testes
@ActiveProfiles("test") //Força o uso do perfil application-test.properties pra usar conf isoladas
public abstract class BaseIntegrationTest {

    @Container
    protected static final MySQLContainer<?> mysqlContainer = new MySQLContainer<>( //funciona como um banco real
            DockerImageName.parse("mysql:8.0")
    )
            .withDatabaseName("rent_db")
            .withUsername("test")
            .withPassword("test");

    @Container
    protected static final RabbitMQContainer rabbitMQContainer = new RabbitMQContainer( //testa envio de msg, serv produz evento
            DockerImageName.parse("rabbitmq:3.12-management")
    );

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        // Configuração do MySQL
        registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mysqlContainer::getUsername);
        registry.add("spring.datasource.password", mysqlContainer::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
        
        // Configuração do RabbitMQ
        registry.add("spring.rabbitmq.host", rabbitMQContainer::getHost);
        registry.add("spring.rabbitmq.port", rabbitMQContainer::getAmqpPort);
        registry.add("spring.rabbitmq.username", rabbitMQContainer::getAdminUsername);
        registry.add("spring.rabbitmq.password", rabbitMQContainer::getAdminPassword);
        
        // Desabilita Eureka para testes
        registry.add("eureka.client.enabled", () -> "false");
        registry.add("spring.cloud.config.enabled", () -> "false");
    }
}
