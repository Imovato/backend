<p align="center">
  <h1 align="center">🏠 Imovato</h1>
</p>
Bem-vindo ao nosso projeto de back-end de microsserviços com spring-boot e spring-cloud, para criar uma arquitetura escalável e altamente disponível. 

# <img src="https://user-images.githubusercontent.com/94808306/218839320-ff26eaec-7b62-4513-ad88-caaafe760f97.png" width="5%" style="display: inline-block;"> Introdução

Há oito microsserviços, cada um com uma tarefa específica:
  - `Discovery` mantém o registro dos microsserviços disponíveis.
  - `Gateway` é o ponto de entrada para requisições do usuário.
  - `Auth` autentica usuários.
  - `Payment` processa pagamentos.
  - `Rent` gerencia aluguéis.
  - `Scheduling` agenda tarefas.
  - `CrudService` fornece operações básicas de CRUD.
  
Cada microsserviço pode ser desenvolvido, testado e implementado de forma independente, tornando o sistema mais flexível e fácil de manter.

# <img src="https://user-images.githubusercontent.com/94808306/218839785-2a2e5f36-08e1-4d83-a977-db529e1cdfcc.png" width="5%" style="display: inline-block;"> Execução do projeto
#### 1. Baixe e instale o [MySQL](https://www.mysql.com/downloads/), após isto necessário 6 bases de dados criadas
 - `crud-service` `aquisition` `auth_db` `payment` `rent_db` `scheduling`
<hr>

#### 2. **Alternativa**, executar o [docker-compose-dev.yml](https://github.com/Imovato/backend/blob/master/docker-compose-dev.yml), onde que contém o serviço do mysql
- 1: Baixe e instale o [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- 2: Inicie o Docker Desktop
- 3: Abra o PowerShell, navegue até o diretório onde se encontra o projeto, por exemplo: `C:user\documents\GitHub\backend`
- 4: Execute o docker-compose, com o comando
- 5: 
   ```
   docker-compose -f docker-compose-dev.yml up
   ```
   <br></br>
➡️ Pode abrir o MySQL com HeidiSQL
    - 1: Baixe e instale o [HeidiSQL](https://www.heidisql.com/download.php.)
    - 2: Inicie o HeidiSQL e clique em "File" > "New Session" para criar uma nova conexão com o banco de dados.
    - Na tela de nova sessão, preencha os seguintes campos:
        - a: Hostname: localhost ou o endereço IP da máquina que está executando o serviço do Docker
        - b: User: o nome de usuário configurado para o serviço MySQL no arquivo docker-compose
        - c: Password: a senha configurada para o serviço MySQL no arquivo docker-compose
        - d: Port: a porta configurada para o serviço MySQL no arquivo docker-compose (padrão é 3306)
    - 3: Clique em "Open" para conectar ao banco de dados.
<hr>
        
#### 3. Fazer os cadastros das filas dos microsserviços no Rabbitmq
- Para instalar o RabbitMQ no Windows, você precisa seguir os seguintes passos:
    - 1: Baixe e instale o Erlang para Windows na sua máquina: [Erlang](https://www.erlang.org/downloads)
    - 2: Baixe e instale o RabbitMQ para Windows na sua máquina: [RabbitMQ](https://www.rabbitmq.com/download.html)
    - 3: Inicie o RabbitMQ Server na sua máquina.
    - 4: Abra o prompt de comando e use o seguinte comando para acessar o RabbitMQ Management:
        ```
        rabbitmq-plugins enable rabbitmq_management
        ```
    - 5: Acesse a interface web do RabbitMQ Management em seu navegador
    	- URL: http://localhost:15672/ 
    	- (padrão é usuário "guest" e senha "guest").
    
➡️ **Agora**, para criar as filas na aplicação de cada microsserviço, você pode seguir os seguintes passos:
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

#### <img src="https://user-images.githubusercontent.com/94808306/218846508-c0f8b682-8706-41d7-be28-93bfbe2dbfa8.png" width="3%" style="display: inline-block;"> Figura ilustrativa do funcionamento das Filas
<p align="center">
  <a href="https://github.com/SamuelModesto">
      <img alt="template-method" src="https://github.com/SamuelModesto/Imagens/blob/master/Imagens%20Imovato/ImovatoFilas.jpeg" />
  </a>
</p>

###### 👨🏻‍🏫 Tutorial da instalação do RabbitMQ: <img src="https://user-images.githubusercontent.com/94808306/218837983-d1203bee-b386-46f1-8d6f-44e47a5c66de.png" width="5%" style="display: inline-block;"> [You Tube](https://youtu.be/PESoVKv0Spo)
###### 👨🏻‍🏫 Tutorial de cadastro de fila no RabbitMQ: <img src="https://user-images.githubusercontent.com/94808306/218837983-d1203bee-b386-46f1-8d6f-44e47a5c66de.png" width="5%" style="display: inline-block;"> [You Tube 1](https://youtu.be/SzcvuHjRJKE) ou <img src="https://user-images.githubusercontent.com/94808306/218837983-d1203bee-b386-46f1-8d6f-44e47a5c66de.png" width="5%" style="display: inline-block;"> [You Tube 2](https://youtube.com/playlist?list=PLZTjHbp2Y7809w3PLM0UE_LgQq6vk49q0)

<hr>

#### 4. **Alternativa** executar o [docker-compose-dev.yml](https://github.com/Imovato/backend/blob/master/docker-compose-dev.yml), onde que contém o serviço do rabbitmq
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
   - OBS: As Exchanges e Queues são criadas automáticas pelo arquivo de definições [RabbitMQ Definitions](https://github.com/Imovato/backend/blob/master/rabbit_definitions.json) 

#### 5. Recuperação de senha local com MailHog
   - Para testar o fluxo de "esqueci minha senha" localmente, suba o `MailHog` junto com o MongoDB e o RabbitMQ usando o arquivo [`backend/cadastral/docker-compose-dev.yml`](./cadastral/docker-compose-dev.yml).
   - Interface web do MailHog: `http://localhost:8025`
   - SMTP local para o backend: `localhost:1025`
   - No perfil `dev`, o serviço `cadastral` envia o token de recuperação por e-mail para o MailHog e também registra o token no retorno da API para facilitar testes manuais.

# <img src="https://user-images.githubusercontent.com/94808306/218842555-9f2cfa9b-66db-4129-9d03-ee7a112cff73.png" width="5%" style="display: inline-block;"> Dockerfile
O Dockerfile é um arquivo de configuração que permite que você crie uma imagem personalizada do Docker para sua aplicação.
- O [Dockerfile](https://github.com/Imovato/backend/blob/master/CrudService/Dockerfile) fornecido tem duas etapas (FROM) para criar uma imagem:
	- A primeira etapa usa a imagem do Maven para compilar o código-fonte da aplicação. Ele copia o arquivo pom.xml e o diretório src para um diretório de trabalho no container, em seguida, executa o comando 'mvn package' para criar o arquivo jar.
	- A segunda etapa usa a imagem do OpenJDK para criar um container que executará a aplicação. Ele copia o arquivo jar criado na primeira etapa para a imagem final e define a porta em que a aplicação estará em execução. Em seguida, define o comando de entrada para iniciar a aplicação no container, especificando o perfil 'docker-demo'.

O Dockerfile permite criar uma imagem que encapsula todas as dependências necessárias e configurações para executar a aplicação, o que facilita a implantação e o gerenciamento da aplicação em diferentes ambientes.

# <img src="https://user-images.githubusercontent.com/94808306/218843489-f512178b-1b81-4817-b2b5-2faf060d8fd6.png" width="8%" style="display: inline-block;"> Docker-compose
- 1: O arquivo [docker-compose-dev.yml](https://github.com/Imovato/backend/blob/master/docker-compose-dev.yml) contém a definição dos serviços MySQL e RabbitMQ para o ambiente de desenvolvimento. Esses serviços são executados como contêineres do Docker e podem ser gerenciados por meio desse arquivo.
- 2: O arquivo [docker-compose-sonar.yml](https://github.com/Imovato/backend/blob/master/docker-compose-sonar.yml) define a configuração do SonarQube e sua base de dados correspondente. O SonarQube é uma ferramenta de análise de código aberto que ajuda a detectar problemas de qualidade de código.
- 3: O arquivo [docker-compose-deploy.yml](https://github.com/Imovato/backend/blob/master/docker-compose-deploy.yml) contém as definições para implantar as imagens dos microserviços e o banco de dados, juntamente com o RabbitMQ, em um ambiente de produção. Esse arquivo é usado para gerenciar a implantação de aplicativos em contêineres do Docker em um ambiente de produção.

# <img src="https://user-images.githubusercontent.com/94808306/218844771-6a4ff1c3-82c5-4726-b2ee-a6b765230624.png" width="5%" style="display: inline-block;"> CI/CD Pipeline com Jenkins
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

# <img src="https://user-images.githubusercontent.com/94808306/218845473-a03734bb-4827-4353-aa0b-dc7b3887d3f9.png" width="5%" style="display: inline-block;"> Links
Os links estão disponíveis na documentação para auxiliar o desenvolvedor, porém itens como login e senha devem ser consultados nos arquivos de propriedade da aplicação.
|  Nome           | Link    | 
| :-------------  | :----------- |
| Swagger      |  http://localhost:8081/crudService/swagger-ui.html# |
| RabbitMQ      | http://localhost:15672/#/ |

#### <img src="https://user-images.githubusercontent.com/94808306/218847126-74c8e45c-69c7-49f2-9967-c21bfb0ac1aa.png" width="3%" style="display: inline-block;">   EndPoints dos microsserviços
| Aplicação    | Porta | context-path        | EndPoint                                   |
| ------------ | ----- | ------------------- | ----------------------------------------- |
| discovery    | 8087  | /discovery         | http://localhost:8087/discovery/*         |
| acquisition  | 8084  | /acquisitionService | http://localhost:8084/acquisitionService/* |
| auth         | 8083  | /authService       | http://localhost:8083/authService/*       |
| CrudService  | 8081  | /crudService       | http://localhost:8081/crudService/*       |
| gateway      | 8080  | /gateway           | http://localhost:8080/gateway/*           |
| payment      | 8082  | /paymentService    | http://localhost:8082/paymentService/*    |
| rent         | 8085  | /rentService       | http://localhost:8085/rentService/*       |
| scheduling   | 8086  | /schedulingService | http://localhost:8086/schedulingService/* |


# ⚙ Tecnologias Utilizadas

Tecnologia|Versão 
----|----
🐬MySQL|8.0.23
🍃Spring Boot|2.4.2
📺Netflix Eureka|1.10.11

# 🔨 Ferramentas
<p align="left">
  <img src="https://user-images.githubusercontent.com/94808306/218291199-b46da654-3be0-4bb1-b562-4b52a752e91e.png" width="5%" style="display: inline-block;">
  <img src="https://user-images.githubusercontent.com/94808306/218291220-845eba8e-5445-4d24-ae67-8d84df147826.png" width="5%" style="display: inline-block;">
  <img src="https://user-images.githubusercontent.com/94808306/218291409-2ff2da32-4ee9-4b44-9155-f611f9b6f714.png" width="5%" style="display: inline-block;">
  <img src="https://user-images.githubusercontent.com/94808306/218291444-7c45a496-2cfa-4bf5-b534-1816ba1a6013.png" width="5%" style="display: inline-block;">
  <img src="https://user-images.githubusercontent.com/94808306/218291546-ba11a3df-b998-4744-938e-fd08f8c73dba.png" width="8%" style="display: inline-block;">
  <img src="https://user-images.githubusercontent.com/94808306/218291681-08c6dca5-869f-4cb9-a032-cc68eb6779aa.png" width="5%" style="display: inline-block;">
</p>

# <img src="https://user-images.githubusercontent.com/94808306/218848224-1422f187-a01e-4b46-9606-bef2cd3c35e8.png" width="5%" style="display: inline-block;"> Front-End
- O front-end encontra-se em outro repositório. **[GitHub](https://github.com/RP-IV-GP2/front-web)**
