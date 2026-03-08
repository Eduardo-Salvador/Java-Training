<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINALIZADO-success.svg)](https://shields.io/)

# Docker e Configuração do MySQL

Este módulo utiliza Docker exclusivamente para provisionar um banco de dados MySQL para os estudos de JDBC. O objetivo não é estudar Docker em si, mas evitar a instalação do MySQL diretamente na máquina e garantir um ambiente consistente e reproduzível em qualquer sistema.

## Tecnologias
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)

</div>

---

## 1. O que é Docker

Docker é uma plataforma que executa aplicações dentro de containers, que são ambientes isolados e leves. Um container inclui tudo que a aplicação precisa para rodar: dependências, configurações e o próprio programa, garantindo que se comporte da mesma forma em qualquer máquina.

Uma imagem é um template somente leitura contendo tudo que um programa precisa para rodar. Ao declarar `image: mysql`, o Docker busca essa imagem no Docker Hub e cria um container em execução a partir dela. A relação é a mesma de classe e objeto: a imagem é a classe, o container é a instância.

Docker é preferido em relação à instalação local do MySQL porque é mais leve, inicia mais rápido e garante que todos os desenvolvedores utilizem exatamente a mesma configuração do banco sem problemas de compatibilidade.

---

## 2. docker-compose.yml

```yaml
version: '2.4'
services:
  db:
    image: mysql
    container_name: ${DB_NAME}
    environment:
      MYSQL_ROOT_PASSWORD: ${DB_PASSWORD}
      MYSQL_ROOT_HOST: '%'
    ports:
      - "${DB_PORT}:3306"
    volumes:
      - java_training_data:/var/lib/mysql

volumes:
  java_training_data:
```

`image: mysql` busca a imagem oficial do MySQL no Docker Hub caso não exista localmente.

`container_name` define o nome do container em execução, lido do arquivo `.env` via `${DB_NAME}`.

`MYSQL_ROOT_PASSWORD` é obrigatório para o container MySQL iniciar. Root é o superusuário com permissões totais. A imagem oficial impõe essa variável na inicialização e não sobe sem ela. O valor é lido do `.env` via `${DB_PASSWORD}`.

`MYSQL_ROOT_HOST: '%'` permite que o usuário root conecte a partir de qualquer host, o que é necessário para a IDE e o JDBC conectarem de fora do container.

`ports: "${DB_PORT}:3306"` mapeia a porta do host para a porta do container. O lado esquerdo é o que é acessado na máquina local, o lado direito é onde o MySQL escuta dentro do container. O valor é lido do `.env` via `${DB_PORT}`.

`volumes: java_training_data:/var/lib/mysql` monta um volume nomeado no caminho onde o MySQL armazena seus dados. Sem isso, todos os dados seriam perdidos ao parar o container. A declaração do volume no final do arquivo o registra no Docker.

---

## 3. Variáveis de Ambiente

Crie um arquivo `.env` na raiz do projeto com os seguintes valores:

```
DB_NAME=java_training_mysql
DB_PASSWORD=root
DB_PORT=3306
```

O Docker Compose lê esse arquivo automaticamente e substitui as variáveis no `docker-compose.yml`.

---

## 4. Comandos

```bash
docker-compose up -d        # sobe o container em background
docker-compose down         # para e remove o container
docker-compose down -v      # para, remove o container e o volume
docker ps                   # lista os containers em execução
docker volume ls            # lista os volumes existentes
docker-compose logs db      # exibe os logs do serviço db
```

---

## 5. Conectando via IDE

Utilize as seguintes configurações para conectar pelo DBeaver ou qualquer IDE de banco de dados:

```
Host:     localhost
Port:     3306 (ou o valor definido em DB_PORT)
User:     root
Password: root (ou o valor definido em DB_PASSWORD)
```

Caso o erro `Public Key Retrieval is not allowed` apareça, acesse as propriedades do driver na configuração da conexão e defina `allowPublicKeyRetrieval` como `true`.

---