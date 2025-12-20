# Comportamentos Parametrizados

---

## Overview
Este módulo contém um exercício demonstrando o uso de **Comportamentos Parametrizados** em Java, aplicando o padrão Strategy para representar diferentes formas de pagamento.  
A ideia é mostrar como parametrizar comportamentos dinamicamente, evitando condicionais rígidas e permitindo maior flexibilidade no código.

**Breve explicação:**  
O padrão Strategy define uma família de algoritmos, encapsula cada um deles e os torna intercambiáveis.  
Neste exercício, diferentes estratégias de pagamento são implementadas como comportamentos parametrizados, aplicando descontos específicos conforme o valor.

O exercício está organizado em três pacotes principais:
- **Domain** — contém a interface `PaymentStrategy`  
- **Controller** — contém a classe `PaymentProcessor` responsável por processar pagamentos  
- **Application** — contém a classe `Main` demonstrando o uso das estratégias de pagamento  
Há também um arquivo **ProblemQuestion.txt** com o enunciado original do problema.

---

## Folder Structure

### 1. ParameterizingBehaviors.Domain
Contém a interface que define o contrato para as estratégias de pagamento.

**Interface: PaymentStrategy**
- Método: `void pay(double amount)`  
- Representa o comportamento de pagamento que pode ser parametrizado dinamicamente

---

### 2. ParameterizingBehaviors.Controller
Contém a classe responsável por processar pagamentos usando uma estratégia fornecida.

**Class: PaymentProcessor**
- Método: `processPayment(double amount, PaymentStrategy strategy)`  
  - Recebe o valor e a estratégia de pagamento  
  - Chama `strategy.pay(amount)` para executar o comportamento parametrizado  

---

### 3. ParameterizingBehaviors.Application
Demonstra múltiplos cenários reais de uso de comportamentos parametrizados.

**Class: Main**
Executa diferentes estratégias de pagamento:

- **Estratégia de Cartão de Crédito**
  - Aplica 2% de desconto se o valor > 3000  
  - Exibe mensagem de pagamento via cartão  

- **Estratégia de Pix**
  - Aplica 10% de desconto se o valor > 3000  
  - Exibe mensagem de pagamento via Pix  

- **Estratégia de Boleto**
  - Aplica 5% de desconto se o valor > 3000  
  - Exibe mensagem de pagamento via boleto  

**Cenários demonstrados:**
- Processamento de um pagamento único com `PaymentProcessor`  
- Iteração sobre uma lista de estratégias (`List<PaymentStrategy>`)  
- Execução de múltiplos pagamentos com diferentes comportamentos parametrizados  

---

## Purpose
- Ensinar o uso de comportamentos parametrizados com Strategy  
- Demonstrar boas práticas para evitar condicionais rígidas  
- Mostrar como encapsular diferentes lógicas de negócio em estratégias independentes  
- Incentivar design modular e extensível  

---

## Files Included
- **Domain/PaymentStrategy.java**  
- **Controller/PaymentProcessor.java**  
- **Application/Main.java**  
- **ProblemQuestion.txt**

---

## How to Run
1. Navegue até a pasta **ParameterizingBehaviors/Application**  
2. Execute a classe **Main**  
3. Observe a saída no console mostrando os diferentes comportamentos de pagamento e descontos aplicados  

---

## Requirements
- **Java 17+**  
- Conhecimento básico de interfaces e POO  
- Familiaridade com listas e laços de repetição  

---

## Notes
- Uso consistente do padrão Strategy para parametrizar comportamentos  
- Evita condicionais fixas e repetitivas  
- Demonstra como diferentes estratégias podem ser aplicadas dinamicamente  
- Mostra claramente como o design orientado a objetos facilita extensibilidade e manutenção  

---
