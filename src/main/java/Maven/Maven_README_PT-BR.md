<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINALIZADO-success.svg)](https://shields.io/)

# Maven

Maven é uma ferramenta de gerenciamento de projetos Java. Resolve três problemas principais: gerenciar dependências automaticamente sem precisar baixar JARs manualmente, padronizar a estrutura de projetos para que todo projeto Maven siga a mesma organização, e automatizar o ciclo de build desde a compilação até o empacotamento e publicação.

## Tecnologias
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)

</div>

---

## 1. Estrutura de Pastas

Todo projeto Maven segue a mesma estrutura obrigatória. O Maven espera encontrar os arquivos exatamente nesses caminhos.

```
meu-projeto/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/exemplo/
    │   │       └── Main.java
    │   └── resources/
    │       ├── application.properties
    │       └── logback.xml
    └── test/
        ├── java/
        │   └── com/exemplo/
        │       └── MainTest.java
        └── resources/
            └── test.properties
```

`src/main/java` contém todo o código de produção. `src/main/resources` contém arquivos de configuração e recursos que precisam estar no classpath em runtime. `src/test/java` contém os testes. `src/test/resources` contém recursos usados apenas durante os testes. `pom.xml` fica na raiz do projeto.

Após o primeiro build o Maven cria a pasta `target`:

```
target/
├── classes/                  # .class compilados do main
├── test-classes/             # .class compilados dos testes
├── meu-projeto-1.0.0.jar     # JAR gerado
├── surefire-reports/         # relatórios dos testes
└── maven-archiver/           # metadados do empacotamento
```

---

## 2. pom.xml

POM significa Project Object Model. É o arquivo central que descreve o projeto e tudo que ele precisa.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <groupId>com.exemplo</groupId>
    <artifactId>meu-projeto</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <name>Meu Projeto</name>
    <description>Descrição do projeto</description>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.33</version>
        </dependency>

        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.10.0</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.30</version>
            <scope>provided</scope>
        </dependency>

    </dependencies>

    <build>
        <plugins>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>17</source>
                    <target>17</target>
                </configuration>
            </plugin>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.1.2</version>
            </plugin>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>3.3.0</version>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>com.exemplo.Main</mainClass>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>

        </plugins>
    </build>

</project>
```

---

## 3. Identidade do Projeto

`groupId` é o identificador da organização ou grupo, geralmente o domínio reverso como `com.google` ou `org.apache`. `artifactId` é o nome do projeto. `version` é a versão atual. Juntos esses três formam as coordenadas únicas do projeto no ecossistema Maven, conhecidas como GAV (GroupId, ArtifactId, Version).

`packaging` define o formato de saída. `jar` é o padrão para aplicações Java. `war` é para aplicações web tradicionais. `pom` é usado em projetos pai que apenas organizam outros projetos.

---

## 4. Dependências e Scopes

Cada dependência tem um `scope` que define em qual fase do ciclo de vida ela está disponível.

`compile` é o padrão quando não declarado. A dependência está disponível em compilação, teste e runtime. `test` está disponível apenas nos testes e não é incluída no JAR final. `provided` está disponível em compilação mas não é incluída no JAR porque o ambiente de execução já a fornece, como um servidor de aplicação. `runtime` não é necessária para compilar mas é necessária para executar.

---

## 5. JAR

JAR significa Java ARchive. É um arquivo ZIP com extensão `.jar` que contém os `.class` compilados, recursos e metadados. É o formato padrão de distribuição de aplicações e bibliotecas Java.

Dentro de todo JAR existe uma pasta `META-INF` com o arquivo `MANIFEST.MF` que descreve o conteúdo:

```
Manifest-Version: 1.0
Main-Class: com.exemplo.Main
Class-Path: libs/mysql-connector.jar
```

`Main-Class` define qual classe tem o método `main` para executar quando o JAR for chamado com `java -jar`. Sem isso o JAR é uma biblioteca, não um executável.

Um JAR comum não inclui as dependências. Para gerar um JAR que empacota tudo, chamado de fat JAR ou uber JAR, utiliza-se o `maven-shade-plugin` ou `maven-assembly-plugin`.

---

## 6. Ciclo de Build

Maven possui três ciclos de vida independentes. O principal é o `default`, que vai da validação até o deploy. `clean` remove arquivos gerados. `site` gera documentação.

O ciclo `default` em ordem completa:

```
validate          valida se o pom.xml está correto
initialize        inicializa propriedades e cria diretórios
generate-sources  gera código fonte (Lombok, mappers, etc)
process-sources   processa o código gerado
compile           compila src/main/java para target/classes
process-classes   processa os .class (instrumentação, etc)
generate-test-sources  gera código de teste
test-compile      compila src/test/java para target/test-classes
test              executa os testes com Surefire
prepare-package   prepara o empacotamento
package           gera o JAR ou WAR em target/
verify            roda verificações de qualidade e integração
install           copia o JAR para ~/.m2/repository
deploy            envia o JAR para repositório remoto
```

Quando um comando é chamado, todas as fases anteriores executam em ordem. Chamar `mvn package` executa validate, compile, test e package. Chamar `mvn install` executa tudo até install.

---

## 7. Comandos e Flags

Comandos básicos:

```bash
mvn validate          # valida o projeto
mvn compile           # compila
mvn test              # compila e roda testes
mvn package           # compila, testa e gera JAR
mvn verify            # compila, testa, verifica e empacota
mvn install           # tudo acima + instala no repositório local
mvn deploy            # tudo acima + envia para repositório remoto
mvn clean             # apaga a pasta target/
```

Combinações mais usadas no dia a dia:

```bash
mvn clean compile              # limpa e recompila
mvn clean test                 # limpa e roda testes
mvn clean package              # limpa e gera JAR
mvn clean install              # limpa, reconstrói e instala localmente
mvn clean install -DskipTests  # reconstrói sem rodar testes
```

Flags importantes:

```bash
-DskipTests              # pula a execução dos testes mas ainda compila
-Dmaven.test.skip=true   # pula compilação E execução dos testes
-pl modulo-a             # executa só em um módulo específico
-am                      # também builda os módulos dos quais o alvo depende
-rf :modulo-a            # retoma o build a partir de um módulo específico
-X                       # modo debug, mostra log detalhado
-q                       # modo silencioso, mostra só erros
-e                       # mostra stack trace completo em caso de erro
-T 4                     # usa 4 threads para build paralelo
-o                       # modo offline, não tenta baixar dependências
-U                       # força atualização de snapshots no repositório
-P perfil-producao       # ativa um profile específico
```

Executando o JAR gerado:

```bash
java -jar target/meu-projeto-1.0.0.jar
```

---

## 8. Repositório Local

Toda dependência baixada fica em `~/.m2/repository` organizada pela estrutura GAV:

```
~/.m2/repository/
└── mysql/
    └── mysql-connector-java/
        └── 8.0.33/
            ├── mysql-connector-java-8.0.33.jar
            ├── mysql-connector-java-8.0.33.pom
            └── mysql-connector-java-8.0.33.jar.sha1
```

O arquivo `.sha1` é o hash de verificação de integridade. O Maven verifica se o JAR baixado não foi corrompido comparando o hash.

Em empresas é comum ter um repositório privado como Nexus ou Artifactory entre o projeto e o Maven Central, para controlar quais dependências são permitidas e hospedar artefatos internos.

---

## 9. Profiles

Profiles permitem configurações diferentes para ambientes diferentes no mesmo pom.xml:

```xml
<profiles>
    <profile>
        <id>desenvolvimento</id>
        <properties>
            <db.url>jdbc:mysql://localhost:3306/dev</db.url>
        </properties>
    </profile>
    <profile>
        <id>producao</id>
        <properties>
            <db.url>jdbc:mysql://prod-server:3306/app</db.url>
        </properties>
    </profile>
</profiles>
```

Ativado com `mvn package -P producao`.

---

## 10. Projetos Multi-Módulo

Projetos grandes dividem o código em módulos. Um pom.xml pai orquestra os filhos:

```xml
<modules>
    <module>modulo-core</module>
    <module>modulo-web</module>
    <module>modulo-service</module>
</modules>
```

Cada módulo tem seu próprio pom.xml e pode depender dos outros módulos. Executar `mvn install` na raiz builda todos em ordem de dependência.

---

## 11. Versionamento

Maven segue a convenção `MAJOR.MINOR.PATCH` com um sufixo especial. `1.0.0` é uma versão estável. `1.0.0-SNAPSHOT` é uma versão em desenvolvimento. SNAPSHOT significa que o artefato pode mudar e o Maven sempre busca a versão mais recente no repositório quando encontra um. Versões estáveis são imutáveis uma vez publicadas.

---