# Projeto de Treinamento - E-commerce com Spring Boot e React

Este é um projeto de e-commerce desenvolvido para fins de aprendizado, utilizando **Spring Boot** no back-end e **React** no front-end. O sistema possui funcionalidades típicas de um e-commerce, como o cadastro de produtos, alteração de preços conforme o tipo de produto e o meio de pagamento, além de um sistema de chat entre o cliente e o vendedor. Além disso, o projeto foi estruturado utilizando design patterns, como **Command**, **Factory**, **Builder** e **Decorator**, para proporcionar uma melhor organização e compreensão dos padrões de design no desenvolvimento de sistemas.

## Tecnologias Utilizadas

- **Back-end**: Spring Boot (Java).
- **Front-end**: React (Typescript).
- **Banco de dados**: Postgres.
- **Design Patterns**: Command, Factory, Builder, Decorator.
- **Outras Dependências**: Docker.

## Funcionalidades Principais

- **Cadastro de Produtos**: O sistema permite o cadastro de novos produtos com informações como nome, descrição, preço, e tipo de produto.
- **Alteração de Preço por Tipo de Produto**: O preço de um produto é alterado automaticamente conforme o tipo do produto, utilizando lógica de negócios específica.
- **Alteração de Preço por Meio de Pagamento**: O preço final dos produtos também pode variar de acordo com o meio de pagamento escolhido pelo cliente (cartão de crédito, pix, etc).
- **Chat entre Cliente e Vendedor**: Um sistema de chat permite que os clientes conversem diretamente com os vendedores, facilitando o suporte e a negociação.

## Rodando o Projeto
#### Na pasta main ``design-patterns-spring-boot´´

### Com docker-compose
- **Execute**:
```bash
  docker-compose up -d --build
```

### Esse script pode auxiliar na criação do postgres caso precise:
```bash
  docker run --rm -d -p 5432:5432 --name postgres-db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=secret -e POSTGRES_DB=users postgres:9-alpine
```
- **Depois que executar esse comando, execute a partir do 2°passo da criação sem docker**:

### Sem docker-compose
- Abra o postgress, crie um novo database, altere os atributos da pasta application.properties `datasource´, para corresponder ao database criado.
- **Executando o server**:
```bash
  cd server
  mvn clean package
  java -jar security-0.0.1-SNAPSHOT.jar
```
- **Executando a web**:
```bash
  cd web
  npm install
  npm run dev
```

### Rota de acesso
- **URL**: http://localhost:5173/

### Se você executou com docker, pode remover o container e a imagem utilizando
- **Removendo**:
```bash
  docker-compose down --rmi all -v
```
