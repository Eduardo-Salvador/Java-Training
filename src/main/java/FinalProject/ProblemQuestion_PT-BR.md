# Projeto Final

# Pet Adoption System v2.0

---

## Objetivo

Este projeto é o desafio final do repositório Java Training. Expande o sistema CLI do v1.0 para uma aplicação desktop completa que integra todos os tópicos abordados ao longo do curso.

O sistema gerencia um abrigo de adoção de pets. O abrigo possui um número crescente de animais aguardando adoção, e a equipe precisa de uma ferramenta para cadastrar novos pets, acompanhar o status de cada um, pesquisá-los por diferentes critérios, processar adoções e monitorar o estado geral do abrigo através de um relatório resumido.

A aplicação possui três áreas principais acessíveis por uma interface gráfica com abas:

**Cadastro:** A equipe cadastra um novo pet preenchendo um formulário com o nome do animal, tipo, sexo, idade, peso, raça e endereço de origem. Todos os campos são validados antes de o pet ser salvo. Campos obrigatórios não podem ser deixados em branco. A idade deve ser um número entre 0.0 e 20.0, onde valores abaixo de 1.0 representam a idade em fração de ano (por exemplo, 0.5 significa seis meses). O peso deve estar entre 0.5 kg e 60.0 kg. Nome e raça devem conter apenas letras. Quando o cadastro é concluído com sucesso, uma mensagem de confirmação é exibida. Quando a validação falha, o erro específico é mostrado à equipe sem fechar o formulário.

**Busca e Adoção:** A equipe pesquisa pets por nome, raça ou tipo. Correspondências parciais são suportadas, de modo que pesquisar por "flu" encontra "Fluffy". Os resultados são exibidos em uma tabela com todas as informações relevantes. A partir dos resultados, a equipe pode selecionar um pet e marcá-lo como adotado, o que altera seu status e o remove do pool de disponíveis. A equipe também pode excluir um registro de pet após uma etapa de confirmação. Apenas um pet pode ser adotado ou excluído por vez.

**Relatório:** A equipe visualiza um resumo ao vivo do abrigo mostrando o total de pets agrupado por tipo, o peso médio por tipo e o pet mais velho cadastrado. Um botão de atualização recalcula tudo sob demanda.

Além da interface gráfica, o sistema executa um servidor de notificações em segundo plano. Toda vez que um pet é cadastrado ou marcado como adotado, uma mensagem é automaticamente transmitida para todos os clientes conectados em tempo real. Isso simula um ambiente de abrigo com múltiplos terminais, onde diferentes membros da equipe na mesma rede recebem atualizações sem precisar atualizar manualmente.

O dono do abrigo pode:

- Cadastrar um novo pet por meio de um formulário gráfico
- Pesquisar pets por nome, raça ou tipo com suporte a correspondência parcial
- Adotar um pet diretamente dos resultados da busca
- Excluir um registro de pet dos resultados da busca
- Visualizar um relatório resumido ao vivo do abrigo
- Receber notificações em tempo real em qualquer terminal conectado quando um pet é cadastrado ou adotado

O sistema deve persistir todos os dados em um banco MySQL rodando em um container Docker, aplicar arquitetura em camadas com separação entre domínio, repositório, serviço e GUI, e cobrir toda a camada de serviço com testes unitários usando JUnit 5 e Mockito.

---

## Conhecimentos Aplicados

- Programação Orientada a Objetos e Padrões de Projeto (Builder, Factory, Singleton)
- Records e DTOs
- Generics
- Coleções e Estruturas de Dados Concorrentes
- Exceções (exceções customizadas)
- Classes Utilitárias
- Lambdas, Streams, Method References e Optional
- GUI com Swing
- Threads, Channels, Sockets e CompletableFuture
- Maven
- JDBC com MySQL
- Docker
- JUnit 5 e Mockito

---

## Arquitetura

O projeto deve seguir uma arquitetura em camadas com a seguinte estrutura de pacotes:

```
domain/         Entidade Pet, enums (PetType, PetSex, PetStatus), DTOs como Records
repository/     Interface genérica PetRepository e PetRepositoryImpl com JDBC
service/        PetService — toda a lógica de negócio, Streams e Lambdas vivem aqui
factory/        PetFactory — centraliza a criação de Pet a partir de DTOs de request
config/         DatabaseConfig — Singleton gerenciando a conexão JDBC
gui/            MainFrame, RegisterPanel, SearchPanel, ReportPanel (Swing)
server/         NotificationServer e NotificationClient (Channels e Sockets)
util/           PetValidator — métodos estáticos de validação
exception/      PetNotFoundException, PetValidationException, DatabaseConnectionException
test/           FinalProject.PetServiceTest e PetRepositoryTest (JUnit 5 + Mockito)
```

---

## Funcionalidades Passo a Passo

### Passo 1: Infraestrutura

Configure o projeto Maven com todas as dependências necessárias. Crie os arquivos `docker-compose.yml` e `.env`. O arquivo `.env` deve armazenar o nome do banco, a senha e a porta. O MySQL deve rodar em um container Docker. Crie o arquivo `init.sql` que inicializa o schema automaticamente na primeira inicialização. A tabela `pets` deve conter no mínimo: `id`, `name`, `type`, `sex`, `age`, `weight`, `breed`, `address`, `status` e `created_at`.

**Regras:**
- Todos os parâmetros de conexão devem ser lidos do `.env`, nada hardcoded
- A aplicação não deve iniciar se a conexão com o banco falhar
- Log4J2 deve registrar todas as operações SQL no nível DEBUG e todos os erros no nível ERROR

---

### Passo 2: Camada de Domínio

Crie o enum `PetType` com no mínimo: `DOG`, `CAT`, `BIRD`, `RABBIT`, `OTHER`.

Crie o enum `PetSex` com: `MALE`, `FEMALE`.

Crie o enum `PetStatus` com: `AVAILABLE`, `ADOPTED`, `UNDER_TREATMENT`.

Crie a entidade `Pet` usando o **padrão Builder**. A entidade deve ter construtor privado, uma classe interna estática `Builder` e um método `build()` que valida os campos obrigatórios antes da instanciação. Os campos obrigatórios são `name`, `type`, `sex` e `age`. Todos os outros campos são opcionais.

Crie `PetRequestDTO` e `PetResponseDTO` como **Records**. O `PetRequestDTO` transporta dados da GUI para o serviço. O `PetResponseDTO` transporta dados do serviço de volta para a GUI. A entidade `Pet` nunca deve alcançar a camada de GUI diretamente.

Crie `PetFactory` usando o **padrão Factory**. Deve expor um único método estático `create(PetRequestDTO request)` que retorna um `Pet` completamente construído.

**Regras:**
- O construtor de `Pet` deve ser privado, apenas o Builder pode instanciá-lo
- `build()` deve lançar `PetValidationException` se qualquer campo obrigatório for nulo ou vazio
- `PetRequestDTO` e `PetResponseDTO` devem ser Records, nenhuma classe clássica

---

### Passo 3: Camada de Repositório

Crie a interface genérica `PetRepository`:

```
PetRepository<T, ID>
    void save(T entity)
    Optional<T> findById(ID id)
    List<T> findAll()
    List<T> findByType(PetType type)
    void update(T entity)
    void delete(ID id)
```

Implemente `PetRepositoryImpl` que implementa `PetRepository<Pet, Long>`. Todas as operações devem usar `PreparedStatement`. A conexão deve vir do `DatabaseConfig` (Singleton). As chaves geradas devem ser recuperadas e atribuídas de volta à entidade após o `save`. Registre cada operação no log.

Crie `DatabaseConfig` usando o **padrão Singleton** com a abordagem Eager. Deve ler os parâmetros de conexão do arquivo `.env` usando `dotenv-java`. Deve expor um único método `getConnection()`.

**Regras:**
- Nenhum `Statement` puro permitido, apenas `PreparedStatement`
- `findById` deve retornar `Optional.empty()` quando nenhum resultado for encontrado, nunca null
- Todas as `SQLExceptions` devem ser capturadas e relançadas como `DatabaseConnectionException`

---

### Passo 4: Camada de Serviço

Crie `PetValidator` com métodos estáticos. Deve validar:

- Nome: obrigatório, apenas letras, mínimo 2 caracteres
- Idade: entre 0.0 e 20.0 (valores abaixo de 1.0 representam a idade em fração de ano)
- Peso: entre 0.5 kg e 60.0 kg
- Endereço: deve conter rua, número e cidade

Crie `PetService` que depende de `PetRepository<Pet, Long>` e `NotificationServer` injetados via construtor. O serviço deve manter um cache em memória `CopyOnWriteArrayList<Pet>` populado na inicialização a partir do banco de dados.

Os seguintes métodos devem ser implementados usando **Streams e Lambdas**, nenhum loop imperativo:

`register(PetRequestDTO)` valida o request, delega a criação ao `PetFactory`, persiste via repositório, adiciona ao cache e transmite uma notificação de forma assíncrona usando `CompletableFuture.runAsync()`. Retorna `PetResponseDTO`.

`findById(Long)` retorna `Optional<PetResponseDTO>`. Lança `PetNotFoundException` se ausente.

`findAll()` retorna `List<PetResponseDTO>` do cache usando `stream().map()`.

`findByNameAndBreed(String name, String breed)` filtra o cache usando `filter()` com `contains` case-insensitive. Retorna `List<PetResponseDTO>`.

`findByType(PetType)` filtra o cache. Retorna `List<PetResponseDTO>`.

`adopt(Long id)` altera o status do pet para `ADOPTED`, atualiza via repositório, atualiza o cache e transmite uma notificação. Lança `PetNotFoundException` se o id não existir.

`countByType()` retorna `Map<PetType, Long>` usando `Collectors.groupingBy` e `Collectors.counting`.

`findOldest()` retorna `Optional<PetResponseDTO>` usando `Stream.max`.

`averageWeightByType()` retorna `Map<PetType, Double>` usando `Collectors.groupingBy` e `Collectors.averagingDouble`.

**Regras:**
- Nenhum `for` ou `while` em `PetService`, todas as operações devem usar Streams
- `findById`, `findOldest` e qualquer busca que possa não encontrar resultado devem retornar `Optional`
- `CopyOnWriteArrayList` é obrigatório para o cache, justifique a escolha em um comentário
- O broadcast de notificação deve ser assíncrono e não deve bloquear o chamador

---

### Passo 5: Servidor de Notificações

Implemente `NotificationServer` usando `ServerSocketChannel` em modo não bloqueante com `Selector`. O servidor deve rodar em uma thread dedicada iniciada quando a aplicação sobe. Deve aceitar múltiplas conexões de clientes e transmitir mensagens para todos os clientes conectados quando `broadcast(String message)` for chamado.

Implemente `NotificationClient` que se conecta ao servidor em uma thread dedicada e imprime as mensagens recebidas em um painel de log na GUI.

**Regras:**
- O servidor não deve rodar na Swing event dispatch thread
- O broadcast não deve bloquear o chamador, deve ser chamado via `CompletableFuture.runAsync()`
- Use `ConcurrentLinkedQueue` para armazenar mensagens pendentes de broadcast com segurança entre threads

---

### Passo 6: GUI com Swing

Implemente `MainFrame` como a janela da aplicação. Deve usar `JTabbedPane` com três abas:

**Aba Cadastro** — `RegisterPanel`
Formulário com `JTextField` para nome, raça, endereço e idade. `JComboBox` para tipo e sexo. Um botão de envio que chama `service.register()`. Em caso de sucesso, exibir um diálogo de confirmação e limpar o formulário. Em caso de `PetValidationException`, exibir a mensagem de validação em um diálogo de erro sem fechar o formulário. Em qualquer outra exceção, exibir um diálogo de erro genérico.

**Aba Busca** — `SearchPanel`
Formulário de busca com campos para nome e raça e um filtro de tipo via `JComboBox`. Um botão de busca que chama `service.findByNameAndBreed()` ou `service.findByType()`. Resultados exibidos em uma `JTable` construída a partir de `List<PetResponseDTO>`. Selecionar uma linha e clicar em Adotar chama `service.adopt()` e atualiza a tabela. Selecionar uma linha e clicar em Excluir chama `service.delete()` após um diálogo de confirmação SIM/NÃO e atualiza a tabela. Apenas uma linha pode ser selecionada por vez.

**Aba Relatório** — `ReportPanel`
Painel de resumo que exibe: total de pets por tipo (de `countByType()`), peso médio por tipo (de `averageWeightByType()`) e o pet mais velho cadastrado (de `findOldest()`). Um botão Atualizar recalcula todos os valores ao vivo usando Streams.

**Regras:**
- Todas as chamadas ao serviço a partir da GUI devem ser executadas na Swing event dispatch thread via `SwingUtilities.invokeLater()` ou `SwingWorker` se a operação for lenta
- A GUI nunca deve chamar o repositório diretamente — apenas o serviço
- Nenhuma verificação de null nos resultados de busca — use `Optional` e trate casos vazios com `ifPresentOrElse`

---

### Passo 7: Testes

Escreva `FinalProject.PetServiceTest` usando JUnit 5 e Mockito. O repositório e o servidor de notificações devem ser mockados. Os testes devem cobrir:

- `register` — Caminho feliz, retorna o DTO correto e chama `repository.save` exatamente uma vez
- `register` — Lança `PetValidationException` quando o nome está vazio
- `findById` — Retorna o DTO correto quando o pet existe
- `findById` — Lança `PetNotFoundException` quando o id não existe
- `adopt` — Altera o status e chama `repository.update` exatamente uma vez
- `adopt` — Lança `PetNotFoundException` quando o id não existe
- `findByNameAndBreed` — Retorna os resultados filtrados corretamente
- `countByType` — Retorna as contagens corretas por tipo

Escreva `PetRepositoryTest` cobrindo `save`, `findById`, `findAll`, `update` e `delete` contra o container MySQL real no Docker ou um banco H2 em memória.

**Regras:**
- Nenhum `@Mock` em classes com métodos estáticos — o Mockito não consegue mockar métodos estáticos
- Cada teste deve ter um único foco de assertion — um comportamento por teste
- Convenção de nomenclatura: `shouldReturnX_WhenY`
- `@BeforeEach` deve resetar o estado do serviço antes de cada teste

---

## Resumo

Este sistema integra todos os tópicos do repositório Java Training em uma única aplicação coesa. Cada conceito resolve um problema que existe naturalmente no domínio: Builder lida com uma entidade complexa com campos opcionais, Streams substituem loops imperativos nas operações de filtro e relatório, CopyOnWriteArrayList protege o cache compartilhado do acesso concorrente, Channels e Sockets entregam notificações em tempo real para terminais conectados, e Mockito isola a camada de serviço nos testes unitários substituindo o banco por um mock. Nenhum tópico é incluído de forma artificial.

---