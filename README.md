Com base nos arquivos do projeto fornecidos, aqui está um arquivo `README.md` com as instruções básicas para configuração e execução local.

````markdown
# Gerenciador de Projetos (ger_de_projetos)

Esta é uma API REST para gerenciamento de projetos e tarefas, construída com Spring Boot, JPA (Hibernate) e MySQL. O projeto utiliza Flyway para gerenciar as migrações do banco de dados.

## Pré-requisitos

Antes de começar, certifique-se de ter o seguinte software instalado:

* **Java Development Kit (JDK) 21** (ou superior)
* **MySQL** (ou um servidor de banco de dados compatível)
* **Maven** (Opcional, pois o projeto inclui o Maven Wrapper)

## Configuração

1.  **Clone o repositório** (ou extraia os arquivos do projeto).

2.  **Configure o Banco de Dados MySQL:**
    * Certifique-se de que seu servidor MySQL esteja em execução (geralmente em `localhost:3306`).
    * O projeto está configurado para usar um banco de dados chamado `controle_projetos`. O script de conexão tentará criar o banco de dados se ele não existir (`createDatabaseIfNotExist=true`).
    * Abra o arquivo `src/main/resources/application.properties` e atualize as credenciais do seu banco de dados (usuário e senha):

    ```properties
    # Configurando banco de dados no MySQL
    spring.datasource.url=jdbc:mysql://localhost:3306/controle_projetos?createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC
    spring.datasource.username=seu_usuario_mysql
    spring.datasource.password=sua_senha_mysql
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

    # JPA/HIBERNATE
    # (ddl-auto=none pois estamos usando Flyway)
    spring.jpa.hibernate.ddl-auto=none
    ```

3.  **Migração do Banco de Dados (Flyway):**
    * As tabelas (`projeto` e `tarefa`) serão criadas automaticamente pelo Flyway na primeira vez que a aplicação for executada.
    * O script de migração está localizado em `src/main/resources/db/migration/V1__Criacao_das_Tabelas.sql`.

## Execução

Este projeto utiliza o Maven Wrapper (`mvnw`), que permite compilar e executar o projeto sem a necessidade de ter o Maven instalado manualmente.

1.  Abra um terminal ou prompt de comando na raiz do projeto.

2.  Execute um dos seguintes comandos:

    * **Para Linux/macOS:**
        ```bash
        ./mvnw spring-boot:run
        ```

    * **Para Windows (CMD):**
        ```bash
        ./mvnw.cmd spring-boot:run
        ```

3.  A aplicação será iniciada e estará acessível em `http://localhost:8080`.

## Endpoints da API

A API expõe os seguintes endpoints principais:

### Projetos (`/api/projetos`)

* `GET /api/projetos`: Lista todos os projetos.
* `POST /api/projetos`: Cria um novo projeto.
* `GET /api/projetos/{id}`: Busca um projeto pelo ID.
* `PUT /api/projetos/{id}`: Atualiza um projeto existente.
* `DELETE /api/projetos/{id}`: Remove um projeto.

### Tarefas (`/api/tarefas` e `/api/projetos/{idProjeto}/tarefas`)

* `GET /api/tarefas`: Lista todas as tarefas.
* `POST /api/tarefas`: Cria uma nova tarefa (deve estar associada a um projeto no corpo da requisição).
* `GET /api/tarefas/{id}`: Busca uma tarefa pelo ID.
* `PUT /api/tarefas/{id}`: Atualiza uma tarefa existente.
* `DELETE /api/tarefas/{id}`: Remove uma tarefa.
* `GET /api/projetos/{idProjeto}/tarefas`: Lista todas as tarefas associadas a um projeto específico.
````
