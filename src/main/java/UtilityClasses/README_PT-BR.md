# Classes Utilitárias

---

## 1. Visão Geral
Este módulo consolida múltiplos exercícios projetados para demonstrar o uso prático das classes utilitárias principais do Java. O conteúdo abrange APIs de data e hora, manipulação de texto, internacionalização, formatação numérica, operações com arquivos (IO e NIO) e sua integração dentro de pequenos sistemas orientados a domínio.

## 2. Escopo
Esta documentação se aplica a todos os exercícios contidos em:
- **Challenge 1 (E1–E15)**
- **Challenge 2 (E1–E4)**

Cada exercício inclui:
- Código-fonte
- *ProblemQuestion.txt* descrevendo a especificação
- Demonstrações da classe utilitária ou API Java relacionada

## 3. Objetivos
O módulo foca em:
1. Fazer parse, formatar, comparar e manipular datas e horas.
2. Formatar valores numéricos e moedas em vários *locales*.
3. Executar normalização de texto e processamento de strings.
4. Realizar operações de leitura e escrita de arquivos usando as APIs Java IO e NIO.
5. Integrar essas utilidades dentro de componentes orientados ao domínio, como faturas, calendários e relatórios de consumo.

## 4. Visão Geral da Arquitetura
O módulo é dividido em dois segmentos principais:

### 4.1 Challenge 1 (Exercícios Independentes)
Cada exercício contém uma única classe Main e um arquivo *ProblemQuestion.txt* definindo os requisitos.  
Não existem dependências entre arquivos.

### 4.2 Challenge 2 (Arquitetura em Camadas)
Cada exercício segue uma Arquitetura em Camadas mínima:
- **Application Layer**
  - Inicia a execução (classe Main).
- **Controller Layer**
  - Lida com interação do usuário, roteamento de comandos e validação.
- **Domain Layer**
  - Contém a lógica de negócio central e estruturas de dados.
- **ProblemQuestion.txt**
  - Define os requisitos funcionais.

Essa arquitetura promove separação de responsabilidades, manutenibilidade e clareza.

## 5. Tecnologias e Classes Utilitárias Utilizadas

### 5.1 Date and Time API (*java.time*)
- LocalDate  
- LocalTime  
- LocalDateTime  
- Instant  
- ZonedDateTime  
- ZoneId  
- DateTimeFormatter  
- Duration  
- Period  
- ChronoUnit  

### 5.2 Manipulação de Texto
- StringBuilder  
- Normalizer  
- Utilitários de classificação de caracteres  

### 5.3 Internacionalização e Formatação
- Locale  
- NumberFormat  
- DateFormat  

### 5.4 Manipulação de Arquivos (IO e NIO)
Usado em exercícios selecionados para persistência de texto e operações com arquivos.

**IO (java.io)**  
- FileReader  
- FileWriter  
- BufferedReader  
- BufferedWriter  

**NIO e Files (java.nio.file)**  
- Path  
- Paths  
- Files  

Usados para:
- Manipulação de caminhos  
- Leitura/escrita de arquivos inteiros  
- Verificação de existência de arquivos  
- Tratamento de caminhos independentes de SO  

## 6. Challenge 1 – Resumo Funcional (E1–E15)
- E1: Formatação de LocalDate  
- E2: *Parsing* de string para data  
- E3: Diferença de dias (ChronoUnit)  
- E4: Diferença baseada em Period  
- E5: Aritmética de datas (adição/subtração)  
- E6: Cálculos com Duration  
- E7: Formatação numérica  
- E8: Formatação dependente de locale  
- E9: Operações com StringBuilder  
- E10: Normalização de texto  
- E11: Ordenação de datas  
- E12: Comparação de prazos  
- E13: Formatação de moeda em diversos locales  
- E14: Cálculo de tempo restante  
- E15: Registro de strings usando StringBuilder  

## 7. Challenge 2 – Resumo Funcional (E1–E4)

### E1 – Gerenciamento de Faturas
Implementa o ciclo de vida de uma fatura usando comparação de datas, formatação e cálculo de multa.

### E2 – Calendário de Eventos
Gerencia eventos com *parsing* de data e hora, validação e operações de listagem.

### E3 – Registro de Consumo do Cliente
Rastreia consumo por categoria e agrega resultados para um determinado ano.

### E4 – Simulação de Empréstimo
Simula juros compostos e cálculos de desconto por pagamento antecipado.

## 8. Validação e Manipulação de Entrada
Ao longo do módulo:
- Os formatos de data são validados antes do *parse*.
- Entradas numéricas são verificadas para compatibilidade de formato.
- Métodos de domínio aplicam restrições (ex.: valores não negativos, datas não expiradas).  
O uso de exceções é limitado à necessidade técnica de impor essas restrições.

## 9. Limitações Conhecidas
- Nenhum mecanismo de persistência (banco de dados, arquivos JSON etc.) está incluído.
- A validação de entrada presume uso via console e entrada síncrona.

## 10. Estrutura de Diretórios
```
UtilityClasses/
├── Challenge1/
│    ├── E1/
│    ├── E2/
│    ├── E3/
│    └── ...
├── Challenge2/
│    ├── E1/
│    ├── E2/
│    ├── E3/
│    └── E4/
```

Cada pasta contém:
- Main.java  
- Subpacotes (Application, Controller, Domain) para Challenge 2  
- ProblemQuestion.txt  

## 13. Conclusão
Este módulo demonstra o uso eficaz das classes utilitárias padrão do Java tanto em exercícios isolados quanto em sistemas estruturados com múltiplas classes. Ele serve como base para desenvolvedores que necessitam de operações confiáveis de data, hora, formatação, manipulação de texto e manipulação de arquivos em ambientes corporativos.