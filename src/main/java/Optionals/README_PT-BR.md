# Optional

---

## Visão Geral
Este módulo contém um exercício que demonstra o uso de **Optional** em Java para representar valores que podem ou não estar presentes, evitando `NullPointerException` e incentivando um código mais seguro e expressivo.

**Breve explicação:**  
`Optional<T>` é um contêiner que pode armazenar um valor do tipo `T` ou estar vazio.  
Ele fornece métodos seguros para lidar com ausência de valores, substituindo verificações manuais de `null`.

O exercício está organizado em três pacotes principais:
- **Domain** — contém a entidade `User`
- **Controller** — contém o repositório responsável por buscas e armazenamento
- **Application** — contém o `Main` demonstrando o uso de Optional na prática  
Além disso, há um arquivo **ProblemQuestion.txt** com o enunciado original.

---

## Estrutura de Pastas

### 1. Optionals.Domain
Contém a entidade principal do exercício.

**Classe: User**
- Atributos: `id`, `name`, `email`
- Métodos:
  - Getters (`getId`, `getName`, `getEmail`)
  - `toString()` formatado para exibição
- Representa um usuário cadastrado no "banco" (lista)

---

### 2. Optionals.Controller
Contém a classe responsável pela lógica de armazenamento e busca.

**Classe: UserRepository**
- Atributos:
  - `userList` — lista interna de usuários
- Métodos:
  - `add(User user)` — adiciona um usuário, verificando se é nulo  
  - `findByEmail(String email)` — retorna `Optional<User>`  
    - `Optional.of(u)` se encontrar  
    - `Optional.empty()` se não encontrar  
- Demonstra claramente como Optional deve ser retornado em operações que podem falhar

---

### 3. Optionals.Application
Demonstra o uso de Optional em diversas situações do mundo real.

**Classe: Main**
Executa os seguintes cenários com `Optional`:

- **Criação da lista inicial** de usuários
- **Inserção** de novos usuários com `add`
- Uso de:  
  - `Optional.of`  
  - `Optional.empty`  
  - `ifPresent(System.out::println)`  
  - `ifPresentOrElse`  
  - `orElse` — fornece valor padrão  
  - `orElseGet` — fornece valor padrão via função  
  - `orElseThrow` — lança exceção se valor ausente

**Cenários demonstrados:**
- Busca bem-sucedida por e-mail e impressão direta do Optional
- Impressão condicionada quando o valor existe (`ifPresent`)
- Ação alternativa quando não existe (`ifPresentOrElse`)
- Fallback (usuário padrão) com `orElse`
- Fallback gerado dinamicamente com `orElseGet`
- Exceção lançada ao não encontrar (`orElseThrow`)

Esse exercício mostra na prática como Optional substitui verificações de nulo e torna o fluxo mais explícito e seguro.

---

## Propósito
- Ensinar o uso correto de `Optional` em buscas, retornos e validações  
- Mostrar boas práticas que evitam `NullPointerException`  
- Demonstrar métodos essenciais e seus cenários reais de aplicação  
- Incentivar código mais declarativo e funcional

---

## Arquivos Incluídos
- **Domain/User.java**
- **Controller/UserRepository.java**
- **Application/Main.java**
- **ProblemQuestion.txt**

---

## Como Executar
1. Navegue até a pasta **Optionals/Application**
2. Execute a classe **Main**
3. Observe a saída no console demonstrando cada operação com Optional

---

## Requisitos
- **Java 17+**
- Conhecimento básico de coleções e POO
- Familiaridade inicial com Optional ajuda, mas não é necessária

---

## Notas
- Uso consistente de Optional em operações de busca  
- Eliminação de verificações manuais de `null`  
- Demonstração dos métodos mais utilizados:  
  - `of`, `empty`, `ifPresent`, `ifPresentOrElse`  
  - `orElse`, `orElseGet`, `orElseThrow`  
- Exemplo claro de fallback e tratamento de ausência de valor  

---
