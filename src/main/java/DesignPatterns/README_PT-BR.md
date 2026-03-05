<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINALIZADO-success.svg)](https://shields.io/)

# Design Patterns

Este módulo cobre quatro padrões de projeto criacionais fundamentais: Builder, Factory, Singleton e DTO. Padrões de projeto são soluções reutilizáveis para problemas recorrentes no desenvolvimento de software. Não são código pronto, mas modelos para estruturar o código de forma organizada e manutenível para resolver tipos específicos de problemas.

## Tecnologias
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Arquitetura

- **Builder**: `PizzaOrder`, `Main`
- **Factory**: `Notification`, `EmailNotification`, `SmsNotification`, `PushNotification`, `NotificationFactory`, `Main`
- **Singleton**: `Config`, `EagerConfig`, `LazyConfig`, `LazyConfigNoThreadSafe`, `EnumConfig`, `Main`
- **DataTransferObject**: `Domain` (`User`, `UserRequestDTO`, `UserResponseDTO`), `Service` (`UserService`), `Main`

---

## 1. Builder

O padrão Builder é aplicado quando um objeto requer muitos parâmetros no construtor, especialmente quando vários são opcionais. Em vez de passar tudo em uma única chamada ao construtor, o objeto é montado passo a passo através de uma interface fluente.

O padrão é composto por um construtor privado na classe principal, uma classe interna estática `Builder` que acumula os valores, métodos setter no Builder que retornam `this` para permitir o encadeamento, e um método `build()` que instancia o objeto final.

```java
Usuario usuario = new Usuario.Builder()
    .nome("Eduardo")
    .email("eduardo@email.com")
    .idade(25)
    .build();
```

O método `build()` é também o local adequado para validação, garantindo que o objeto só seja criado em um estado válido.

---

## 2. Factory

O padrão Factory centraliza e esconde a lógica de criação de objetos. Em vez de usar `new` espalhado pela base de código, a criação é delegada a um único lugar. O chamador recebe uma instância tipada para uma interface ou classe abstrata, sem conhecimento do tipo concreto criado.

```java
Notification notification = NotificationFactory.create("EMAIL");
notification.send("Hello!");
```

Adicionar um novo tipo requer apenas uma nova classe e uma linha na Factory. Nada mais muda. O padrão também reforça a programação para interfaces, já que a variável retornada é sempre tipada para o contrato e não para a implementação.

---

## 3. Singleton

O padrão Singleton garante que uma classe tenha apenas uma instância em toda a aplicação e que essa instância seja globalmente acessível. É adequado para recursos que devem existir apenas uma vez, como conexões com banco de dados, configuração da aplicação e pools de threads.

Existem três abordagens de implementação, cada uma com diferentes tradeoffs.

**Eager** cria a instância quando a classe é carregada, antes de qualquer chamada ao `getInstance()`. É thread-safe por natureza porque a JVM garante que a inicialização estática é atômica. O tradeoff é que o objeto é criado mesmo que nunca seja usado.

```java
public class Config {
    private static final Config INSTANCE = new Config();
    private Config() {}
    public static Config getInstance() { return INSTANCE; }
}
```

**Lazy** cria a instância apenas na primeira requisição. Evita alocar o objeto se desnecessário, mas requer medidas de thread-safety. Sem sincronização, duas threads podem entrar na verificação de null simultaneamente e criar duas instâncias separadas, quebrando o padrão. A solução é o Double-Checked Locking: a primeira verificação fora do `synchronized` evita bloqueio em toda chamada após a instância existir, a segunda verificação dentro garante que apenas uma thread a cria, e o `volatile` garante que todas as threads enxergam o valor atualizado imediatamente sem interferência de cache de CPU.

```java
public class Config {
    private static volatile Config INSTANCE;
    private Config() {}
    public static Config getInstance() {
        if (INSTANCE == null) {
            synchronized (Config.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Config();
                }
            }
        }
        return INSTANCE;
    }
}
```

**Enum** é a abordagem mais simples e segura. A JVM garante uma única instância, é thread-safe por natureza assim como o Eager, e é protegido contra serialização e reflection. Serialização é um risco em implementações tradicionais de Singleton porque desserializar um objeto cria uma nova instância, quebrando a garantia. Com enum isso não ocorre.

```java
public enum Config {
    INSTANCE;
    public void doSomething() { ... }
}
```

É importante observar que o Singleton garante uma única instância mas não garante acesso thread-safe ao seu estado interno. Campos mutáveis compartilhados como mapas ou contadores ainda requerem sincronização adequada. `ConcurrentHashMap` é a escolha padrão para estado de mapa compartilhado e `AtomicInteger` para contadores compartilhados.

| | Eager | Lazy | Enum |
|--|-------|------|------|
| Criação | Na inicialização | Na primeira chamada | Na inicialização |
| Thread-safe | Sim | Sim com volatile | Sim |
| Memória | Sempre aloca | Aloca só se usar | Sempre aloca |
| Complexidade | Simples | Média | Simples |
| Recomendado quando | Sempre usado | Pesado e eventual | Caso geral |

---

## 4. DTO — Data Transfer Object

O padrão DTO é utilizado para transportar dados entre camadas da aplicação. Um DTO não possui lógica de negócio nem comportamento, apenas transporta dados. Seu propósito é desacoplar o que é armazenado internamente do que é exposto externamente.

Entidades de banco de dados tipicamente contêm mais informações do que deveriam ser expostas. Uma entidade pode armazenar senhas, identificadores sensíveis e relacionamentos pesados. Retornar a entidade diretamente expõe dados sensíveis, acopla o contrato da API à estrutura do banco e torna qualquer mudança interna uma potencial quebra de contrato para o chamador. O DTO atua como uma camada de isolamento, permitindo que a entidade evolua internamente sem afetar o contrato externo.

```
Cliente -> RequestDTO -> Service -> Entidade -> Banco
Banco -> Entidade -> Service -> ResponseDTO -> Cliente
```

O tipo Record introduzido no Java 16 é a escolha natural para DTOs. É imutável, elimina boilerplate e carrega apenas dados sem lógica.

```java
public record UserResponseDTO(Long id, String name, String email) {}
public record UserRequestDTO(String name, String email, String password) {}
```

A conversão entre entidade e DTO é feita manualmente na camada de serviço ou através de bibliotecas de mapeamento como MapStruct.

---

## 5. Exercícios

### 5.1. Builder — PizzaOrder

Implementação de um pedido de pizza usando o padrão Builder. A classe `PizzaOrder` expõe apenas o `Builder` interno, mantendo seu próprio construtor privado. O método `build()` valida que os campos obrigatórios `size`, `crust` e `sauce` estão presentes antes da instanciação. A lista de `toppings` é armazenada como uma lista não modificável para preservar a imutabilidade após a construção.

**Conceitos chave:** construtor privado, Builder estático interno, encadeamento de métodos, validação no `build()`, coleções não modificáveis.

---

### 5.2. Factory — NotificationFactory

Implementação de um sistema de notificações com três tipos concretos: `EmailNotification`, `SmsNotification` e `PushNotification`, todos implementando a interface `Notification`. A `NotificationFactory` usa uma switch expression para retornar a implementação correta. A entrada é normalizada com `toUpperCase()` para tornar a resolução do tipo insensível a maiúsculas e minúsculas.

**Conceitos chave:** interface como tipo de retorno, switch expression, resolução case-insensitive, programação para interfaces.

---

### 5.3. Singleton — ApplicationConfig

Implementação das três abordagens de Singleton através de uma interface `Config` compartilhada. `EagerConfig` usa inicialização estática final. `LazyConfig` aplica Double-Checked Locking com `volatile`. `EnumConfig` usa a abordagem enum. Uma quarta classe `LazyConfigNoThreadSafe` foi adicionada para demonstrar a race condition na prática: quando 10 threads acessam antes de qualquer instância existir, o output mostra hashcodes diferentes provando que múltiplas instâncias foram criadas. Todas as implementações thread-safe usam `ConcurrentHashMap` para o mapa interno de propriedades.

**Conceitos chave:** Eager, Lazy com Double-Checked Locking, Enum Singleton, `volatile`, `synchronized`, `ConcurrentHashMap`, demonstração de race condition.

---

### 5.4. DTO — UserDTO

Implementação de um sistema de gerenciamento de usuários demonstrando a separação entre entidade interna e representação externa. A entidade `User` armazena id, name, email, password e cpf internamente e é construída usando o padrão Builder. Dois Records atuam como DTOs: `UserRequestDTO` transporta name, email e password do cliente, e `UserResponseDTO` retorna apenas id, name e email. O `UserService` cuida da criação, busca por id e listagem, sempre convertendo para DTO antes de retornar. Campos sensíveis nunca aparecem no output.

**Conceitos chave:** separação entre entidade e DTO, Record como DTO, DTOs de request e response, conversão manual na camada de serviço, isolamento de dados sensíveis, Builder combinado com DTO.

---

## 6. Resultados

- Padrão Builder para construção de objetos complexos passo a passo com validação no momento da construção
- Padrão Factory para centralizar a criação de objetos e programar para interfaces
- Padrão Singleton em três abordagens: Eager, Lazy com Double-Checked Locking e Enum
- Thread-safety no Singleton: `volatile`, `synchronized` e `ConcurrentHashMap` para estado interno
- Race condition demonstrada na prática com `LazyConfigNoThreadSafe`
- Padrão DTO para isolar entidades internas de contratos externos
- Records como abordagem moderna para implementação de DTOs
- Combinação de padrões: Builder utilizado dentro do exercício de DTO para construção da entidade

---