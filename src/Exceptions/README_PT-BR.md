# Java Exceptions

---

## Visão Geral

Este projeto contém uma coleção de exercícios independentes sobre tratamento de exceções em Java, organizados nos pacotes Exceptions.E1, Exceptions.E2, ... Exceptions.E7.

Cada exercício inclui: 
- Uma estrutura mínima com Application, Controller e Domain
- Lógica específica para demonstrar diferentes usos de exceções
- Um arquivo **ProblemQuestion.txt** contendo o enunciado original do exercício
- Exemplos práticos de: 
	- Try/catch
	- Throws
	- Hierarquia de exceções
	- Exceções personalizadas
	- Bloco finally
	- Uso de AutoCloseable
	- Manipulação de arquivos (Reader/Writer)

O objetivo é permitir que o estudante compreenda, na prática, como as exceções funcionam e como devem ser usadas para garantir segurança, clareza e robustez no código.

------------------------------------------------------------------------

## O que são Exceções?

Exceções são eventos inesperados que ocorrem durante a execução de um programa.

Quando algo impede o fluxo normal como dividir por zero, ler um arquivo inexistente ou validar dados incorreto o Java lança uma exceção.

### Benefícios:

-   Evitam a finalização abrupta do programa
-   Permitem tratamento local de erros (via try/catch)
-   Ajudam a escrever programas mais seguros
-   Separam claramente o fluxo normal do fluxo de erro

### Dois tipos principais:

#### Checked Exceptions

Devem ser tratadas ou declaradas com **throws**.
Exemplos: 
- IOException
- FileNotFoundException
- Exceções personalizadas que estendem Exception

#### Unchecked Exceptions

Não exigem tratamento obrigatório.
Exemplos: 
- ArithmeticException
- InputMismatchException
- NullPointerException

------------------------------------------------------------------------

## Classes Utilitárias Usadas

Os exercícios fazem uso de classes utilitárias importantes:

### Scanner (java.util.Scanner)

Lê dados do teclado (entrada do usuário).

### FileReader / BufferedReader

Lê arquivos de texto.

### FileWriter / BufferedWriter

Escreve arquivos de texto.

### AutoCloseable

Permite que objetos sejam fechados automaticamente via
**try-with-resources**.

### Exceções Personalizadas

Criadas nos exercícios E3, E4 e E7.

Exemplo geral:

``` java
public class InvalidAgeException extends Exception {
    public InvalidAgeException(String msg) {
        super(msg);
    }
}
```

------------------------------------------------------------------------

# Resumo dos Exercícios

A seguir, cada exercício (E1 a E7) é descrito com o que ele demonstra.
Todos possuem um arquivo **ProblemQuestion.txt** com o enunciado completo.

------------------------------------------------------------------------

## **E1 --- Calculadora de Divisão (ArithmeticException + InputMismatchException)**

### Tópico

-   Validação de divisão por zero
-   Uso de try/catch
-   Exceções não verificadas (unchecked)

### Descrição

-   O programa pede dois números ao usuário e tenta realizar uma divisão.
-   Se o divisor for 0, uma ArithmeticException é lançada.
-   Se o usuário digitar algo inválido, ocorre InputMismatchException.

### Conteúdo técnico:

-   Método que lança explicitamente uma exceção com **throw**
-   Uso de Scanner
-   Tratamento conjunto com `catch (ArithmeticException | InputMismatchException e)`

------------------------------------------------------------------------

## **E2 --- Leitor de Arquivos (Checked Exceptions + Multiple Catches)**

### Tópico

-   Leitura de arquivos de texto
-   Tratamento de FileNotFoundException e IOException
-   Uso de BufferedReader + FileReader
-   Estrutura try-with-resources

### Descrição

-   O programa tenta abrir e ler um arquivo linha a linha.
-   Se o arquivo não existir, captura FileNotFoundException.

### Conteúdo técnico:

-   Blocos try-with-resources
-   Leitura de arquivos
-   Tratamento de múltiplas checked exceptions

------------------------------------------------------------------------

## **E3 --- Exceção Personalizada (InvalidAgeException)**

### Tópico

-   Exceções personalizadas (extends Exception)
-   Validação de idade
-   Exceção obrigatória (checked)

### Descrição

O usuário informa uma idade.
Se for menor que 0 ou maior que 120, o sistema lança
InvalidAgeException.

### Conteúdo técnico:

-   Classe de domínio validando dados no construtor
-   Tratamento de exceção personalizada

------------------------------------------------------------------------

## **E4 --- Bloco Finally + Exceção Personalizada**

### Tópico

-   Diferença entre:
    -   try
    -   catch
    -   finally
-   Garantia de liberação de recursos manualmente

### Descrição

Semelhante ao E3, mas com uso explícito de finally, que fecha o Scanner manualmente.

### Conteúdo técnico:

-   Fechamento de recursos
-   Execução garantida do finally

------------------------------------------------------------------------

## **E5 --- Escritor de Arquivos (IOException + Finally + BufferedWriter)**

### Tópico

-   Escrita em arquivos de texto
-   Tratamento de IOException
-   Uso de finally para fechamento manual

### Descrição

O sistema escreve duas linhas em um arquivo .txt.
Qualquer falha de escrita gera uma exceção.

### Conteúdo técnico:

-   BufferedWriter + FileWriter
-   Escrita linha a linha
-   Tratamento com finally e close()

------------------------------------------------------------------------

## **E6 --- AutoCloseable + try-with-resources**

### Tópico

-   Implementação de AutoCloseable
-   Fechamento automático de recursos
-   Scanner e MockConnection no mesmo bloco

### Descrição

Criação da classe mock **MockConnection** que simula abertura e fechamento de uma conexão.
Demonstra como o método close() é chamado automaticamente.

### Conteúdo técnico:

-   Estrutura try(resource1; resource2; ...)
-   Execução automática de close()

------------------------------------------------------------------------

## **E7 --- Conta Bancária (Exceção Personalizada + Regras de Negócio)**

### Tópico

-   Exceções personalizadas ligadas a regras de negócio
-   Validação de saldo insuficiente
-   Menu interativo

### Descrição

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

------------------------------------------------------------------------

# Objetivos de Aprendizado

-   Diferença entre checked e unchecked exceptions
-   Try, catch, finally
-   Throw vs throws
-   Criação de exceções personalizadas
-   Leitura e escrita de arquivos
-   Fechamento manual e automático de recursos
-   Implementação de AutoCloseable
-   Validação de dados e regras de negócio
-   Menu interativo com Scanner
-   Organização modular em Application, Controller e Domain

---