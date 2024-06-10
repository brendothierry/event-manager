Claro, aqui está o README completo no formato Markdown:

# Aplicação de Gerenciamento de Eventos

## Visão Geral

A aplicação de Gerenciamento de Eventos é baseada em Java, utilizando Spring Boot e PostgreSQL para gerenciar eventos. A aplicação fornece endpoints RESTful para a criação e gerenciamento de instituições e eventos. Além disso, inclui um agendador que verifica periodicamente a validade das datas dos eventos e atualiza seu status conforme necessário.

## Pré-requisitos

- **Java 11**: Certifique-se de ter o JDK 11 instalado.
- **PostgreSQL**: Certifique-se de ter o PostgreSQL instalado e em execução.

## Configuração

### Passo 1: Configuração do Banco de Dados

1. Crie um banco de dados PostgreSQL. Você pode usar os detalhes de configuração do arquivo `application.properties` no projeto.
   
   Exemplo:
   ```sql
   CREATE DATABASE event_manager;
   ```

2. Configure as definições do banco de dados PostgreSQL no arquivo `application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/event_manager
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   ```

### Passo 2: Executando a Aplicação

1. Abra sua IDE e execute o arquivo `Application.java` como uma aplicação Java.

2. Uma vez que a aplicação inicie, ela criará automaticamente as tabelas necessárias no seu banco de dados PostgreSQL.

### Passo 3: Inserindo Dados Iniciais

#### Usando Consultas SQL

Você pode inserir dados iniciais na tabela `institutions` usando as seguintes consultas SQL:
```sql
INSERT INTO institutions (name, type) VALUES ('Institution A', 'Type 1');
INSERT INTO institutions (name, type) VALUES ('Institution B', 'Type 2');
INSERT INTO institutions (name, type) VALUES ('Institution C', 'Type 3');
```

#### Usando Postman

Alternativamente, você pode usar o Postman para criar instituições via os endpoints RESTful fornecidos.

### Passo 4: Criando Eventos

Após criar as instituições, você pode criar eventos através dos endpoints fornecidos ou através da interface front-end.

## Funcionalidades

- **Gerenciamento de Instituições**: Criar e recuperar instituições.
- **Gerenciamento de Eventos**: Criar, atualizar e recuperar eventos.
- **Agendador**: Verifica automaticamente e atualiza a validade dos eventos a cada 24 horas.

### Configuração do Agendador

A aplicação utiliza a anotação `@Scheduled` do Spring para verificar a validade das datas dos eventos. Por padrão, isso ocorre a cada 24 horas. Para fins de teste, você pode reduzir o intervalo para 1 minuto.

Exemplo:
```java
@Scheduled(fixedRate = 60000) // Executa a cada 1 minuto
public void checkAndUpdateEventStatus() {
    // Implementação do método
}
```

## Endpoints

### Endpoints de Instituições

- **Criar Instituição**: `POST /events/createInstitution`
- **Obter Todas as Instituições**: `GET /events/findAllInstitutions`

### Endpoints de Eventos

- **Criar Evento**: `POST /events/createEvent`
- **Obter Todos os Eventos**: `GET /events/findAllEvents`
- **Atualizar Evento**: `PUT /events/updateEvent`

## Testes

Os testes unitários são fornecidos para a camada de serviço para garantir a funcionalidade da lógica central. Você pode executar os testes usando seu framework de testes preferido.

## Contato

Para quaisquer dúvidas, por favor contate com `brendothierry41@gmail.com`.
