
<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINALIZADO-success.svg)](https://shields.io/)

# Optionals

Este módulo contém um exercício abrangente demonstrando o uso de Optional em Java, uma classe container introduzida no Java 8 para lidar elegantemente com a presença ou ausência de valores.

## Tecnologias
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Visão Geral

Optional é uma **classe container introduzida no Java 8 que serve como um invólucro para valores que podem ou não estar presentes.** É uma **forma elegante de lidar com a possibilidade de ausência de valores, evitando o temido `NullPointerException`.**

Antes do Optional, era comum retornar `null` quando um valor não estava disponível, levando a código cheio de verificações null e potenciais erros. **O Optional torna explícito no código que um valor pode não existir**, melhorando a segurança e legibilidade do código.

---

## Arquitetura:

O projeto está organizado da seguinte forma:

- **Domain**: Contém a classe `User` modelando a entidade
- **Services**: Contém `UserRepository` com lógica de negócio para busca de usuários
- **Application**: Contém a classe `Main` demonstrando todos os métodos Optional

---

## 1. O que é Optional:

Optional é um objeto container que pode ou não conter um valor não-nulo. Foi introduzido no Java 8 para **fornecer uma solução em nível de tipo para representar valores opcionais em vez de referências null**.

### 1.1. Criando um Optional:

Existem três formas principais de criar um Optional:
```java
// Optional vazio
Optional<String> vazio = Optional.empty();

// Optional com valor (lança exceção se null)
Optional<String> comValor = Optional.of("João");

// Optional que pode ser null
Optional<String> talvezNulo = Optional.ofNullable(possivelmenteNull);
```

### 1.2. Métodos Principais:

**Verificar presença:**
- `isPresent()` — retorna true se há valor
- `isEmpty()` — retorna true se está vazio (Java 11+)

**Obter valor:**
- `get()` — retorna o valor (lança exceção se vazio)
- `orElse(valor)` — retorna o valor ou um padrão
- `orElseGet(supplier)` — retorna o valor ou executa função
- `orElseThrow()` — lança exceção se vazio

**Transformar:**
- `map(função)` — transforma o valor se presente
- `flatMap(função)` — similar ao map, mas para funções que retornam Optional
- `filter(predicado)` — filtra baseado em condição

**Executar ações:**
- `ifPresent(consumer)` — executa ação se valor existe
- `ifPresentOrElse(consumer, runnable)` — executa ação ou alternativa

### 1.3. Benefícios de Usar Optional:

**Segurança contra null** — reduz ocorrências de NullPointerException

**API explícita** — deixa claro quando valores podem estar ausentes

**Estilo funcional** — habilita encadeamento de métodos e transformações

**Melhor documentação** — assinaturas de métodos comunicam intenção

### 1.4. Boas Práticas:

1. **Nunca use `Optional.get()` sem verificar** — use alternativas mais seguras como `orElse()` ou `orElseThrow()`
2. **Evite Optional em campos** — projetado para tipos de retorno, não armazenamento
3. **Não use Optional com coleções** — coleções vazias são melhores que `Optional<List>`
4. **Prefira `orElseGet()` ao invés de `orElse()`** — quando a criação do valor padrão é cara

> **Em resumo:** Optional é uma **ferramenta poderosa para escrever código mais seguro e expressivo** que lida explicitamente com a ausência de valores, eliminando muito da necessidade de verificação de null!

---

## 2. Exercício:

### 2.1. Busca de Perfil de Usuário com Optional

Demonstra uso abrangente de métodos Optional em um cenário de busca de usuários.

**Conceitos-chave:**
- Criação e retorno de valores Optional
- Verificação de presença com `isPresent()` e `isEmpty()`
- Recuperação de valores com `orElse()`, `orElseGet()` e `orElseThrow()`
- Execução de ações com `ifPresent()` e `ifPresentOrElse()`
- Transformação de valores com `map()`
- Filtragem de valores com `filter()`
- Encadeamento de métodos para código conciso

**Classe Domain: User**
- Atributos: `id`, `name`, `email`
- Todos os atributos são final (imutáveis)
- Métodos:
  - `getId()`, `getName()`, `getEmail()`
  - `toString()` sobrescrito para saída formatada

**Classe Service: UserRepository**
- Método: `findByEmail(List<User> userList, String email)`
  - Retorna `Optional.of(user)` quando encontrado
  - Retorna `Optional.empty()` quando não encontrado
  - Demonstra padrões adequados de criação de Optional

**Application:**
- Cria uma lista de 7 usuários com diferentes provedores de email
- **Impressão direta de Optional** — exibe o container Optional
- **`ifPresent()`** — imprime usuário se encontrado
- **`ifPresentOrElse()`** — imprime usuário ou mensagem "Usuário não encontrado"
- **`orElse()`** — retorna usuário padrão quando não encontrado
- **`orElseGet()`** — cria usuário alternativo apenas quando necessário (avaliação preguiçosa)
- **`orElseThrow()`** — lança exceção personalizada com tratamento adequado de erros
- **`map()`** — extrai apenas o nome de usuário do Optional<User>
- **`filter()`** — retorna Optional apenas se email contém "@gmail.com"

---

## 3. Objetivos Alcançados

**Domínio completo da criação de Optional**
- Entendimento de `of()`, `empty()` e `ofNullable()`
- Saber quando usar cada método de criação

**Recuperação segura de valores**
- Evitando `get()` sem verificações
- Usando `orElse()`, `orElseGet()` e `orElseThrow()` apropriadamente
- Entendendo avaliação preguiçosa vs ansiosa

**Execução condicional**
- Usando `ifPresent()` para efeitos colaterais
- Implementando `ifPresentOrElse()` para controle de fluxo completo

**Transformações funcionais**
- Aplicando `map()` para transformação de valores
- Usando `filter()` para resultados Optional condicionais
- Encadeamento de métodos para código mais limpo

**Tratamento de exceções**
- Criando exceções personalizadas com `orElseThrow()`
- Uso adequado de try-catch com Optional

**Boas práticas**
- Escrevendo código null-safe
- Tornando APIs mais expressivas
- Melhorando legibilidade e manutenibilidade do código

---