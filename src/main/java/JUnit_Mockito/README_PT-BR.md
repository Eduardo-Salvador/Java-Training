<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINALIZADO-success.svg)](https://shields.io/)

# JUnit_Mockito 5 e Mockito

Este módulo cobre testes unitários em Java usando JUnit_Mockito 5 e Mockito. Testes unitários validam o comportamento de unidades isoladas de código sem depender de bancos de dados, redes ou qualquer recurso externo.

## Tecnologias
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit5-f5f5f5?style=for-the-badge&logo=junit5&logoColor=dc524a)

</div>

---

## Arquitetura

- **JUnit_Mockito**: `CalculatorRepository`, `CalculatorService`, `CalculatorTest`

---

## 1. Por que Testes Unitários

Sem testes, a única forma de verificar que o código funciona é rodar a aplicação manualmente e testá-la pelo terminal ou pela interface. Essa abordagem não escala. Conforme o projeto cresce, uma mudança em um lugar pode quebrar outro lugar silenciosamente, e o problema só aparece em produção.

Testes unitários resolvem isso de três formas. Primeiro, garantem que o comportamento esperado continua funcionando após qualquer modificação, o que se chama de prevenção de regressão. Segundo, forçam o código a ser escrito de forma testável, o que naturalmente leva a classes menores, métodos com responsabilidade única e menos acoplamento. Terceiro, documentam o comportamento de forma executável: um teste bem escrito é mais confiável do que qualquer comentário.

---

## 2. Estrutura de Teste

A estrutura universal para qualquer teste é o padrão AAA: Arrange, Act e Assert.

```java
@Test
void shouldReturnCorrectResultOnSum() {
    // Arrange — monta o cenário
    int a = 2;
    int b = 3;

    // Act — executa o que está sendo testado
    int result = service.sum(a, b);

    // Assert — verifica o resultado
    assertEquals(5, result);
}
```

---

## 3. Anotações Principais

`@Test` marca o método como um teste. `@BeforeEach` executa antes de cada teste, usado para montar o estado inicial. `@AfterEach` executa depois de cada teste, usado para limpar recursos. `@BeforeAll` executa uma vez antes de todos os testes da classe. `@AfterAll` executa uma vez depois de todos os testes. `@DisplayName` define um nome legível para o teste. `@Disabled` desativa um teste temporariamente.

---

## 4. Assertions Principais

```java
assertEquals(expected, actual);
assertNotEquals(expected, actual);
assertTrue(condition);
assertFalse(condition);
assertNull(object);
assertNotNull(object);
assertThrows(Exception.class, () -> method());
assertAll(
    () -> assertEquals(1, result.getId()),
    () -> assertEquals("name", result.getName())
);
```

`assertThrows` valida que o código lança a exception correta em cenários de erro. `assertAll` agrupa várias assertions e executa todas mesmo que uma falhe, em vez de parar na primeira.

---

## 5. Bons Testes vs Maus Testes

Um teste ruim passa mesmo quando o código está errado. O exemplo mais clássico:

```java
@Test
void shouldSave() {
    service.save(book);
    assertTrue(true); // passa sempre, não testa nada
}
```

Cobertura de 100% significa apenas que cada linha foi executada, não que o comportamento foi validado. Um teste pode executar um método sem fazer nenhuma assertion significativa e ainda assim contar como cobertura.

Um bom teste valida comportamento, não execução. Cada teste deve ter uma única razão para falhar. Se um teste valida três comportamentos diferentes e falha, não fica claro qual dos três quebrou. Um comportamento por teste.

Testes não devem depender de outros testes. Cada teste deve ser capaz de rodar isolado em qualquer ordem e produzir o mesmo resultado.

O nome do teste é a primeira documentação. Quando um teste falha em CI, o nome `shouldThrowExceptionWhenIdIsNegative` explica imediatamente o que quebrou sem precisar ler o código.

---

## 6. Casos que a Cobertura Não Detecta

Cobertura mede linhas executadas, não combinações de estado. Os casos que mais escapam são valores de limite, string nula versus string vazia, lista vazia versus lista populada, e condições compostas.

```java
// o código tem essa lógica
if (id == null || id <= 0) throw new IllegalArgumentException();

// teste ruim — cobre a linha mas não as duas condições
@Test
void shouldThrowWhenIdIsInvalid() {
    assertThrows(IllegalArgumentException.class,
        () -> service.delete(-1));
}

// bom — testa cada condição separadamente
@Test
void shouldThrowWhenIdIsNull() {
    assertThrows(IllegalArgumentException.class,
        () -> service.delete(null));
}

@Test
void shouldThrowWhenIdIsZero() {
    assertThrows(IllegalArgumentException.class,
        () -> service.delete(0));
}

@Test
void shouldThrowWhenIdIsNegative() {
    assertThrows(IllegalArgumentException.class,
        () -> service.delete(-1));
}
```

---

## 7. @BeforeEach e Variáveis de Instância

`@BeforeEach` executa antes de cada teste e é usado para montar o estado inicial que se repete em vários testes. A regra é simples: se três ou mais testes precisam do mesmo objeto no mesmo estado, ele pertence ao `@BeforeEach`.

```java
class CalculatorTest {

    CalculatorRepository repository;
    CalculatorService service;

    @BeforeEach
    void setUp() {
        repository = new CalculatorRepository();
        service = new CalculatorService(repository);
    }

    @Test
    void shouldReturnCorrectResultOnSum() {
        assertEquals(5, service.sum(2, 3));
    }
}
```

O JUnit_Mockito cria uma nova instância da classe de teste antes de cada teste, portanto variáveis de instância não carregam estado entre testes mesmo sem `@BeforeEach`.

---

## 8. Mockito

Testes devem ser isolados. Quando o método testado depende de outra classe, como um repositório que acessa o banco de dados, essa dependência precisa ser simulada. Mockito é a biblioteca padrão para isso.

Um mock é um objeto vazio que não executa nada. Quando `repository.save(book)` é chamado em um mock, ele não salva nada em lugar nenhum. O Mockito intercepta a chamada e permite que o teste configure o que o mock retorna e verifique que métodos específicos foram chamados.

```java
@ExtendWith(MockitoExtension.class)
class CalculatorTest {

    @Mock
    private CalculatorRepository repository;

    @InjectMocks
    private CalculatorService service;

    @Test
    @DisplayName("Should call repository with correct result on sum")
    void shouldCallRepositoryWithCorrectResultOnSum() {
        service.sum(2, 3);
        verify(repository, times(1)).save(5);
    }
}
```

`@Mock` cria uma versão simulada da dependência. `@InjectMocks` cria a classe sendo testada e injeta os mocks automaticamente. `verify()` confirma que o método foi chamado o número de vezes esperado. `when().thenReturn()` configura o que o mock retorna para uma determinada chamada.

O Mockito funciona criando uma subclasse do repositório em tempo de execução e sobrescrevendo seus métodos para interceptar as chamadas. Isso só funciona com métodos de instância. Métodos estáticos pertencem à classe e não ao objeto, portanto o Mockito não tem instância para substituir. Por isso código que usa chamadas estáticas a dependências não pode ser testado com Mockito, e injeção de dependência via construtor é a prática padrão para código testável.

---

## 9. Convenção de Nomenclatura

O nome do teste deve descrever o comportamento esperado, não o nome do método. O padrão mais usado é `should[Resultado]When[Condição]`:

```
shouldReturnCorrectResultOnSum
shouldThrowExceptionWhenDivisorIsZero
shouldCallRepositoryWithCorrectResult
shouldReturnEmptyWhenIdDoesNotExist
```

---

## 10. Dependências Maven

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.0</version>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>5.14.0</version>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-junit-jupiter</artifactId>
    <version>5.14.0</version>
    <scope>test</scope>
</dependency>
```

Plugin Surefire necessário para rodar os testes com Maven:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.1.2</version>
    <configuration>
        <argLine>-XX:+EnableDynamicAgentLoading</argLine>
    </configuration>
</plugin>
```

---

## 11. Exercício

### 11.1. CalculatorService

Implementação de um serviço de calculadora demonstrando testes unitários com JUnit_Mockito 5. O `CalculatorRepository` simula persistência imprimindo cada resultado. O `CalculatorService` recebe o repositório via construtor e expõe quatro operações: `sum`, `subtract`, `multiply` e `divide`. Cada operação chama `repository.save()` com o resultado antes de retorná-lo. `sum` lança `IllegalArgumentException` para valores negativos e `divide` lança `IllegalArgumentException` quando o divisor é zero.

A classe `CalculatorTest` cobre dez cenários validando tanto resultados corretos quanto o comportamento de exceções em todas as operações.

**Conceitos chave:** padrão AAA, `assertEquals`, `assertThrows`, `@BeforeEach`, variáveis de instância, um comportamento por teste, convenção de nomenclatura, injeção de dependência via construtor.

---

## 12. Resultados

- Padrão AAA como estrutura universal para qualquer teste
- Distinção entre cobertura e validação significativa
- Testes de valores de limite e condições compostas além da cobertura de linhas
- `@BeforeEach` para configuração compartilhada sem vazamento de estado entre testes
- Mockito para isolar dependências da unidade sendo testada
- Métodos estáticos como incompatíveis com injeção via Mockito
- Injeção de dependência via construtor como pré-requisito para código testável
- Convenção de nomenclatura que descreve comportamento em vez de nomes de métodos

---