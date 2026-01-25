<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINALIZADO-<COLOR>.svg)](https://shields.io/)

# Exceptions

Este módulo fornece uma introdução completa ao tratamento de Exceptions em Java, com foco em recursos ofertados pelas exceções e seus tipos.

## Tecnologias
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Visão Geral

Este projeto contém:
- Uma coleção de exercícios independentes sobre tratamento de exceções em Java, organizados nos pacotes Exceptions.E1, Exceptions.E2, ... Exceptions.E7.
- Lógica específica para demonstrar diferentes usos de exceções.
- Exemplos práticos de:
	- Try/catch
	- Throws
	- Hierarquia de exceções
	- Exceções personalizadas
	- Bloco finally
	- Uso de AutoCloseable
	- Manipulação de arquivos (Reader/Writer)

O objetivo é permitir é entender como as exceções funcionam e como devem ser usadas para garantir segurança, clareza e robustez no código.

---

## Arquitetura

- Uma estrutura mínima com Application, Services e Domain
- Um arquivo **ProblemQuestion.txt** contendo o enunciado original do exercício

---

### 1. O que são Exceções?

Exceções são condições anormais do programa que ocorrem em tempo de execução de um programa.

Quando algo impede o fluxo normal como dividir por zero, ler um arquivo inexistente ou validar dados incorreto o Java lança uma exceção.

Exceções vem da classe **Throwable** que são instânciados em Exceptions.

### 1.1. Benefícios:

- Evitam a finalização abrupta do programa
- Permitem tratamento local de erros (via try/catch)
- Ajudam a escrever programas mais seguros
- Separam claramente o fluxo normal do fluxo de erro

---

### 2. Existem dois tipos principais:

#### 2.1. Checked Exceptions

Filhas da classe Exception são exceções do tipo checked:

Devem ser tratadas ou declaradas com **throws**.

Exemplos: 
- IOException
- FileNotFoundException
- Exceções personalizadas que estendem Exception

#### 2.2. Unchecked Exceptions

Não exigem tratamento obrigatório, mas pode ser feito.

São vinculadas a erros de código, então trata-lás é opcional, pois nem deveriam estar ali.

Consiste em uma classe chamada RunTimeException e suas subclasses, quando o programa nem compila pelo lançamento da exceção, quando foi feito algo no código que não pode ser feito, seja na sintáxe ou na lógica.

Exemplos: 
- ArithmeticException
- InputMismatchException
- NullPointerException

---

### 3. Lançando uma exceção:

``` java
private void withdraw(double amount) throws InvalidInputException {
	if(amount > 1000) {
		throw new InvalidInputException("The value is greater than the maximum");
	}
}
```

A assinatura `throws` no método avisa que quem usar terá que tratar uma possível exceção com `tryCatch`.

---

### 4. Finally:

Bloco que sempre vai ser executado dentro do `tryCatch`, independente se for lançada a exceção ou não.

Utilizado para fechar recursos, ou fazer alguma operação necessária.

``` java
try {
	System.out.println("Abrindo Arquivo");
} catch (Exception e) {
	e.printStackTrace();
} finally {
	System.out.println("Fechando Arquivo");
}
```

---

### 5. Capturas Múltiplas:

Posso capturar várias Exceptions em um bloco só:

``` java
try {
	throw new ArrayIndexOutOfBoundException();
} catch (ArrayIndexOutOfBoundException e) {
} catch (IndexOutOfBoundException e) {
} catch (IllegalArgumentException e) {
} catch (ArithmeticException | IllegalArgumentException e) {
}
```

**Regras:**

1. *Não podemos colocar o tipo mais genérico de Exception a frente de outro mais especifico.*
2. *Deve começar do menos genérico ate o mais genérico.*
3. *Para usar o | os Exceptions devem estar na mesma linha de herança (não podem ter relação de herança direta (um não pode ser filho do outro)).*

---

### 6. Try With Resources:

Try com recursos: Serve para fechar conexões, requisições, etc.

``` java
try(Reader reader = new BufferedReader(new FileReader("teste.txt")){
} catch (IOException e) {
}
// -> O própio try fecha o buffer.
// -> Posso declarar várias referências separadas por ; desde que implementem closable.
```

---

### 7. AutoCloseable

Permite que objetos sejam fechados automaticamente (Objetos que implementam AutoClosable necessitam abertura e fechamento) via
**try-with-resources**.

---

### 8. Exceptions Customizadas:

Crie uma classe com fim nomeException e defina se é Checked ou Unchecked:

``` java
public class InsufficientBalanceException extends Exception {
    
    // Construtor padrão
    public InsufficientBalanceException() {
        super("Saldo insuficiente para realizar esta operação.");
    }

    // Construtor que aceita uma mensagem customizada
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
//-> Checked tratamento obrigátorio (Exception)

// BankAccount.java
public class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Tentativa de saque de R$" + amount + " com saldo de R$" + balance);
        }
        balance -= amount;
        System.out.println("Saque realizado. Novo saldo: R$" + balance);
    }
}

// Main.java
public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(100.0);
        try {
            account.withdraw(150.0);
        } catch (InsufficientBalanceException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
```

---

### 9. Exceptions são Polimórficas:

*Exceptions são objetos, não tem nada de especial, salvo que pode ser lançada*. **Sendo assim podem ser polimorficamente referênciadas.** A vantagem é que **um método não precisa declarar explicitamente todas as exceções possíveis, pode declarar apenas uma superclasse das exceções.**

O mesmo vale para o **catch, não precisa de um catch para cada exceção, desde que o catch declarado seja uma superclasse que consiga lidar com todas as exceções lançadas.**

```java
public void doLandry() throws ClothingException{} 
//-> Toda subclasse de Clothing pode ser lançada.

	try {
	} catch (ClothingException e) {}
	//-> Toda subclasse de Clothing que estiver no método pode ser recuperada.
	
	ClothingException e = new SubClasseException(); 
	//-> Basicamente isso que fazemos com throw new e catch.
```

*Se o seu código lida com uma `TeeShirtException` de modo diferente de uma `LingerieException`, escreva um bloco catch para cada uma. Mas se você tratar todos os outros tipos de `ClothingException` do mesmo jeito, adicione um catch `ClothingException` para lidar com o restante.*

---

### 10. Classes Utilitárias Usadas

Os exercícios fazem uso de classes utilitárias importantes:

####  10.1. Scanner (java.util.Scanner)

Lê dados do teclado (entrada do usuário).

#### 10.2. FileReader / BufferedReader

Lê arquivos de texto.

### 10.3. FileWriter / BufferedWriter

Escreve arquivos de texto.

---

### 11. Resumo dos Exercícios

A seguir, cada exercício (E1 a E7) é descrito com o que ele demonstra.
Todos possuem um arquivo **ProblemQuestion.txt** com o enunciado completo.

#### 11.1. **E1 - Calculadora de Divisão (ArithmeticException + InputMismatchException)**

#### Tópico

-   Validação de divisão por zero
-   Uso de try/catch
-   Exceções não verificadas (unchecked)

#### Descrição

-   O programa pede dois números ao usuário e tenta realizar uma divisão.
-   Se o divisor for 0, uma ArithmeticException é lançada.
-   Se o usuário digitar algo inválido, ocorre InputMismatchException.

#### Conteúdo técnico:

-   Método que lança explicitamente uma exceção com **throw**
-   Uso de Scanner
-   Tratamento conjunto com `catch (ArithmeticException | InputMismatchException e)`

#### 11.2. **E2 - Leitor de Arquivos (Checked Exceptions + Multiple Catches)**

#### Tópico

-   Leitura de arquivos de texto
-   Tratamento de FileNotFoundException e IOException
-   Uso de BufferedReader + FileReader
-   Estrutura try-with-resources

#### Descrição

-   O programa tenta abrir e ler um arquivo linha a linha.
-   Se o arquivo não existir, captura FileNotFoundException.

#### Conteúdo técnico:

-   Blocos try-with-resources
-   Leitura de arquivos
-   Tratamento de múltiplas checked exceptions

### 11.3. **E3 - Exceção Personalizada (InvalidAgeException)**

#### Tópico

-   Exceções personalizadas (extends Exception)
-   Validação de idade
-   Exceção obrigatória (checked)

#### Descrição

O usuário informa uma idade.
Se for menor que 0 ou maior que 120, o sistema lança
InvalidAgeException.

#### Conteúdo técnico:

-   Classe de domínio validando dados no construtor
-   Tratamento de exceção personalizada


#### 11.4. **E4 - Bloco Finally + Exceção Personalizada**

#### Tópico

-   Diferença entre:
    -   try
    -   catch
    -   finally
-   Garantia de liberação de recursos manualmente

#### Descrição

Semelhante ao E3, mas com uso explícito de finally, que fecha o Scanner manualmente.

#### Conteúdo técnico:

-   Fechamento de recursos
-   Execução garantida do finally

#### 11.5. **E5 - Escritor de Arquivos (IOException + Finally + BufferedWriter)**

#### Tópico

-   Escrita em arquivos de texto
-   Tratamento de IOException
-   Uso de finally para fechamento manual

#### Descrição

O sistema escreve duas linhas em um arquivo .txt.
Qualquer falha de escrita gera uma exceção.

#### Conteúdo técnico:

-   BufferedWriter + FileWriter
-   Escrita linha a linha
-   Tratamento com finally e close()

#### 11.6. **E6 - AutoCloseable + try-with-resources**

#### Tópico

-   Implementação de AutoCloseable
-   Fechamento automático de recursos
-   Scanner e MockConnection no mesmo bloco

#### Descrição

Criação da classe mock **MockConnection** que simula abertura e fechamento de uma conexão.
Demonstra como o método close() é chamado automaticamente.

#### Conteúdo técnico:

-   Estrutura try(resource1; resource2; ...)
-   Execução automática de close()

#### 11.7. **E7 - Conta Bancária (Exceção Personalizada + Regras de Negócio)**

#### Tópico

-   Exceções personalizadas ligadas a regras de negócio
-   Validação de saldo insuficiente
-   Menu interativo

#### Descrição

O usuário inicia uma conta com depósito inicial e escolhe: 
	
	1. Depositar
	2. Sacar
	3. Ver saldo
	4. Sair

Tentativas de saque acima do saldo lançam
**BalanceInsufficientException**.

### Conteúdo técnico:

-   Programação orientada a objetos
-   Regras de negócio + exceção personalizada
-   Menu interativo
-   Scanner + controle de fluxo

---

### 12. Resumo:

-   Diferença entre checked e unchecked exceptions
-   Try, catch, finally
-   Throw vs throws
-   Criação de exceções personalizadas
-   Leitura e escrita de arquivos
-   Fechamento manual e automático de recursos
-   Implementação de AutoCloseable
-   Validação de dados e regras de negócio
-   Menu interativo com Scanner
-   Organização modular em Application, Services e Domain

---