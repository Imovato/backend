<p align="center">
  <h1 align="center">🏠 Imovato</h1>
</p>
Bem-vindo ao nosso projeto de back-end de microsserviços com spring-boot e spring-cloud, para criar uma arquitetura escalável e altamente disponível. 

# Introdução
Há oito microsserviços, cada um com uma tarefa específica:
  - `Discovery` mantém o registro dos microsserviços disponíveis.
  - `Gateway` é o ponto de entrada para requisições do usuário.
  - `Auth` autentica usuários.
  - `Payment` processa pagamentos.
  - `Rent` gerencia aluguéis.
  - `Scheduling` agenda tarefas.
  - `CrudService` fornece operações básicas de CRUD.
  
Cada microsserviço pode ser desenvolvido, testado e implementado de forma independente, tornando o sistema mais flexível e fácil de manter.

# Execução do projeto
1. Baixe e instale o [MySQL](https://www.mysql.com/downloads/), após isto necessário 6 bases de dados criadas
    - `crud-service` `aquisition` `auth_db` `payment` `rent_db` `scheduling`

2. **Alternativa**, executar o [docker-compose-dev.yml](https://github.com/Imovato/backend/blob/master/docker-compose-dev.yml), onde que contém o serviço do mysql

    - 1: Baixe e instale o [Docker Desktop](https://www.docker.com/products/docker-desktop/)
    - 2: Inicie o Docker Desktop
	 - 3: Abra o PowerShell, navegue até o diretório onde se encontra o projeto, por exemplo: `C:user\documents\GitHub\backend`
	 - 4: Execute o docker-compose, com o comando
	  - 5: 
        ```
          docker-compose -f docker-compose-dev.yml up
        ```
      - Pode abrir o MySQL com HeidiSQL
	      - 1: Baixe e instale o [HeidiSQL](https://www.heidisql.com/download.php.)
	      - 2: Inicie o HeidiSQL e clique em "File" > "New Session" para criar uma nova conexão com o banco de dados.
          - Na tela de nova sessão, preencha os seguintes campos:
            - a: Hostname: localhost ou o endereço IP da máquina que está executando o serviço do Docker
            - b: User: o nome de usuário configurado para o serviço MySQL no arquivo docker-compose
            - c: Password: a senha configurada para o serviço MySQL no arquivo docker-compose
            - d: Port: a porta configurada para o serviço MySQL no arquivo docker-compose (padrão é 3306)
        - 3: Clique em "Open" para conectar ao banco de dados.
        
### 3. Fazer os cadastros das filas dos microsserviços no Rabbitmq
- Para instalar o RabbitMQ no Windows, você precisa seguir os seguintes passos:
 1. Baixe e instale o Erlang para Windows na sua máquina: [Erlang](https://www.erlang.org/downloads)
 2. Baixe e instale o RabbitMQ para Windows na sua máquina: [RabbitMQ](https://www.rabbitmq.com/download.html)
 3. Inicie o RabbitMQ Server na sua máquina.
 4. Abra o prompt de comando e use o seguinte comando para acessar o RabbitMQ Management:
    ```
    rabbitmq-plugins enable rabbitmq_management
    ```
 4. Acesse a interface web do RabbitMQ Management em seu navegador
    - URL: http://localhost:15672/ 
    - (padrão é usuário "guest" e senha "guest").
    
- Agora, para criar as filas na aplicação de cada microsserviço, você pode seguir os seguintes passos:
   - 1: Acesse a interface gráfica do RabbitMQ Management através do navegador.
   - 2: Faça login na plataforma, se necessário.
   - 3: Selecione a aba "Exchanges".
   - 4: Clique no nome do exchange definido no arquivo "application.yml" dos microsserviços, no exemplo "crud.exchange".
   - 5: Clique na aba "Bindings" e depois em "Add Binding".
   - 6: Selecione o tipo de exchange adequado, por exemplo "Direct" ou "Topic".
   - 7: No campo "Queue", selecione a fila que deseja adicionar ao exchange, por exemplo "auth.signup" ou "crud.acquisition.property".
   - 8: Preencha o campo "Routing Key" com a routing key definida no arquivo "application.yml" do microsserviço correspondente.
   - 9: Clique em "Add binding" para criar o binding entre o exchange e a fila.
   - 10: Repita os passos 5 a 9 para cada fila que deseja adicionar ao exchange, com suas respectivas routing keys e configurações, conforme especificado em cada arquivo "application.yml" dos microsserviços

## Figura ilustrativa do funcionamento das Filas
<p align="center">
  <a href="https://github.com/SamuelModesto">
      <img alt="template-method" src="https://github.com/SamuelModesto/Imagens/blob/master/Imagens%20Imovato/ImovatoFilas.jpeg" />
  </a>
</p>

#### 📺 Tutorial da instalação do RabbitMQ: [You Tube](https://youtu.be/PESoVKv0Spo)
#### 📺 Tutorial de cadastro de fila no RabbitMQ: [You Tube 1](https://youtu.be/SzcvuHjRJKE) ou [You Tube 2](https://youtube.com/playlist?list=PLZTjHbp2Y7809w3PLM0UE_LgQq6vk49q0)


4. **Alternativa**,  executar o [docker-compose-dev.yml](https://github.com/Imovato/backend/blob/master/docker-compose-dev.yml), onde que contém o serviço do rabbitmq
	- 1: Inicie o Docker Desktop
	- 2: Abra o PowerShell, navegue até o diretório onde se encontra o projeto, por exemplo: `C:user\documents\GitHuB\backend`
	- 3: Execute o docker-compose, com o comando
	- 4: 
      ```
      docker-compose -f docker-compose-dev.yml up
	    ```
    - 5: Acesse a interface web do RabbitMQ Management em seu navegador 
      - URL: http://localhost:15672/ 
      - (padrão é usuário "admin" e senha "admin"), definido no arquivo [rabbitmq.config](https://github.com/Imovato/backend/blob/master/rabbitmq.config)

## CI/CD Pipeline com Jenkins
Este pipeline [Jenkinsfile](https://github.com/Imovato/backend/blob/master/Jenkinsfile) é definido em uma linguagem de script para a ferramenta Jenkins.
Ele tem vários estágios para compilar, testar e implantar dois serviços diferentes: 
- **Discovery e Rent** 
  - O estágio de "Build Discovery" verifica o código-fonte no repositório do GitHub, compila o código-fonte usando o Maven e empacota em um arquivo WAR. 
  - Em seguida, o estágio "Deploy Discovery" implanta o serviço no servidor Tomcat local. 
  - O estágio "Build Rent" compila o código-fonte do serviço Rent. 
  - O estágio "Unit Tests-Rent" executa os testes unitários para o serviço Rent. 
  - O estágio "Sonar Analysis" executa a análise de código-fonte usando o SonarQube do docker-compose [docker-compose-sonar.yml](https://github.com/Imovato/backend/blob/master/docker-compose-sonar.yml). 
  - O estágio "Deploy Rent" implanta o serviço Rent no servidor Tomcat. 
  - Finalmente, o estágio "API Test-Rent" executa testes de API em um projeto de teste separado.

## Links
Os links estão disponíveis na documentação para auxiliar o desenvolvedor, porém itens como login e senha devem ser consultados nos arquivos de propriedade da aplicação.
|  Nome           | Link    | 
| :-------------  | :----------- |
| Swagger      |  http://localhost:8081/crudService/swagger-ui.html# |
| RabbitMQ      | http://localhost:15672/#/ |


# ⚙Tecnologias Utilizadas

Tecnologia|Versão 
----|----
🐬MySQL|8.0.23
🍃Spring Boot|2.4.2
📺Netflix Eureka|1.10.11

## Front-End [GitHub](https://github.com/RP-IV-GP2/front-web)
