# TransferApi

## Descrição

A **TransferApi** é uma API desenvolvida para gerenciar clientes e realizar transferências entre contas. Implementada em Java 21 com Spring Boot, a aplicação utiliza um banco de dados H2 em memória para desenvolvimento e testes.

## Funcionalidades

- **Cadastro de Clientes:** Permite o cadastro de novos clientes.
- **Listagem de Clientes:** Lista todos os clientes cadastrados.
- **Busca de Cliente por Número da Conta:** Busca um cliente pelo número da conta.
- **Transferência entre Contas:** Realiza transferências de valores entre contas, com validação de saldo e limite de transferência.
- **Histórico de Transferências:** Obtém o histórico de transferências relacionadas a uma conta, incluindo transferências bem-sucedidas e falhas.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.3.0**
- **Gradle 8.x**
- **H2 Database** (para desenvolvimento e testes)
- **Swagger** (para documentação da API)

## Instalação e Execução

### Pré-requisitos
- **Java JDK 21**
- **Gradle**

## Clonando Repositório
```bash
git clone https://github.com/LucianeC/TransferApi.git
````
## Configurando Ambiente 
### Configuração do Banco de Dados:
O projeto utiliza o H2 Database, que está configurado para ser executado em memória. Não são necessárias configurações adicionais para o banco de dados.
### Configuração do Gradle:
O projeto utiliza o Gradle como gerenciador de dependências. O arquivo **build.gradle** já está configurado com todas as dependências necessárias.

A aplicação estará disponível em http://localhost:8080.

## Testes unitarios 
````bash
./gradlew test
````
## Testes de Integração

Os testes de integração são realizados automaticamente ao iniciar a aplicação, e você pode testar a API usando a interface Swagger UI disponível em http://localhost:8080/swagger-ui.html.

## Endpoints da API

### CLIENTE
#### Cadastrar Cliente 
````bash
POST /api/v1/clientes
{
  "nome": "Nome do Cliente",
  "numeroConta": "0000000",
  "saldo": 1000.00
}
````

#### Listar Cliente 
````bash
GET /api/v1/clientes
[]
````

#### Buscar Cliente por Número da Conta
````bash
GET /api/v1/clientes/{numeroConta}
[]
````

### TRANSFERENCIA
#### Realizar Transferência
````bash
POST /api/v1/transferencias
Request Parameters:
numeroContaOrigem: Número da conta de origem
numeroContaDestino: Número da conta de destino
valor: Valor da transferência
````

#### Listar Transferências por Conta
````bash
GET /api/v1/transferencias/{numeroConta}
[]
````

### Estrutura do projeto
````bash
src/
  main/
    java: Código-fonte da aplicação
      controller: Controles da API
      model: Modelos de dados
      repository: Repositórios para acesso a dados
      service: Serviços da aplicação
  test/
    java: Testes unitários
---------
build.gradle: Arquivo Gradle
application.properties: Configurações aplicação
DocumentacaoAPi: Json doc Api
````

### Contato
Se você tiver alguma dúvida ou sugestão, sinta-se à vontade para entrar em contato:

- Nome: Luciane Costa 
- lindedin: https://www.linkedin.com/in/luciane-costa24/
- GitHub: LucianeC
