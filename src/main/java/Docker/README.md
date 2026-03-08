<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINISHED-success.svg)](https://shields.io/)

# Docker and MySQL Setup

This module uses Docker exclusively to provision a MySQL database for the JDBC studies. The goal is not to study Docker itself but to avoid installing MySQL directly on the machine and ensure a consistent and reproducible environment across any system.

## Technologies
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)

</div>

---

## 1. What is Docker

Docker is a platform that runs applications inside containers, which are isolated and lightweight environments. A container includes everything the application needs to run: dependencies, configurations and the program itself, ensuring it behaves the same way on any machine.

An image is a read-only template containing everything a program needs to run. When `image: mysql` is declared, Docker pulls that image from Docker Hub and creates a running container from it. The relationship is the same as class and object: the image is the class, the container is the instance.

Docker is preferred over installing MySQL locally because it is lighter, starts faster, and guarantees that every developer uses exactly the same database configuration without compatibility issues.

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

`image: mysql` pulls the official MySQL image from Docker Hub if not already present locally.

`container_name` sets the name of the running container, read from the `.env` file via `${DB_NAME}`.

`MYSQL_ROOT_PASSWORD` is required for the MySQL container to start. Root is the superuser with full permissions. The official image enforces this variable at initialization and will not start without it. The value is read from `.env` via `${DB_PASSWORD}`.

`MYSQL_ROOT_HOST: '%'` allows the root user to connect from any host, which is necessary for the IDE and JDBC to connect from outside the container.

`ports: "${DB_PORT}:3306"` maps the host port to the container port. The left side is what is accessed on the local machine, the right side is where MySQL listens inside the container. The value is read from `.env` via `${DB_PORT}`.

`volumes: java_training_data:/var/lib/mysql` mounts a named volume at the path where MySQL stores its data. Without this, all data would be lost when the container is stopped. The volume declaration at the bottom registers it with Docker.

---

## 3. Environment Variables

Create a `.env` file at the project root with the following values:

```
DB_NAME=java_training_mysql
DB_PASSWORD=root
DB_PORT=3306
```

Docker Compose reads this file automatically and substitutes the variables in the `docker-compose.yml`.

---

## 4. Commands

```bash
docker-compose up -d        # starts the container in background
docker-compose down         # stops and removes the container
docker-compose down -v      # stops, removes container and volume
docker ps                   # lists running containers
docker volume ls            # lists existing volumes
docker-compose logs db      # shows logs for the db service
```

---

## 5. Connecting via IDE

Use the following settings to connect through DBeaver or any database IDE:

```
Host:     localhost
Port:     3306 (or the value set in DB_PORT)
User:     root
Password: root (or the value set in DB_PASSWORD)
```

If the error `Public Key Retrieval is not allowed` appears, go to Driver Properties in the connection settings and set `allowPublicKeyRetrieval` to `true`.

---