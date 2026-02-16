<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINALIZADO-success.svg)](https://shields.io/)

# Sistema de Adoção de Pets (CLI) v.1.0

Sistema completo de gerenciamento de registros de animais para abrigos, desenvolvido em Java com interface de linha de comando (CLI).

Veja o que foi pedido aqui: [Problem Question PT_BR](https://github.com/Eduardo-Salvador/Java-Training/blob/main/src/main/java/StudyChallenges/RegistrationSystem/ProblemQuestion_PT-BR.md)

## Tecnologias
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Índice

- [Visão Geral](#-visão-geral)
- [Funcionalidades](#-funcionalidades)
- [Arquitetura do Projeto](#-arquitetura-do-projeto)
- [Conhecimentos Aplicados](#-conhecimentos-aplicados)
- [Estrutura de Dados](#-estrutura-de-dados)
- [Detalhamento Técnico](#-detalhamento-técnico)
- [Recursos Extras Implementados](#-recursos-extras-implementados)
- [Como Executar](#-como-executar)
- [Resultados de Aprendizagem](#-resultados-de-aprendizagem)

---

## Visão Geral

Este projeto é uma aplicação CLI robusta para gerenciar registros de pets em abrigos de animais. O sistema permite ao administrador do abrigo realizar operações completas de CRUD (Create, Read, Update, Delete) com validações rigorosas e um sistema de formulários customizável.

**Objetivo Principal:** Facilitar o gerenciamento de informações de animais resgatados, permitindo registro detalhado, busca avançada, edição e exclusão de dados de forma organizada e segura.

---

## Funcionalidades

### Funcionalidades Principais

1. **Registrar um novo oldPet**
  - Formulário com 7 perguntas base + perguntas extras customizáveis
  - Validação rigorosa de dados (nome, idade, peso, raça, endereço)
  - Suporte para perguntas adicionais criadas pelo usuário

2. **Pesquisar pets**
  - Busca por múltiplos critérios: nome, sexo, idade, peso, raça, endereço, data de registro
  - Combinação de até 2 critérios simultaneamente
  - Busca parcial, case-insensitive e accent-insensitive
  - Destaque visual dos termos encontrados (negrito)

3. **Editar dados de pets**
  - Modificação de qualquer atributo (exceto Tipo e Sexo)
  - Busca prévia para seleção do oldPet
  - Preservação do histórico com timestamp

4. **Excluir um oldPet**
  - Exclusão segura com confirmação
  - Busca prévia para seleção

5. **Listar todos os pets**
  - Visualização rápida de todos os registros
  - Formatação clara e numerada

6. **Sistema de Formulários Customizável** (Extra)
  - Criar novas perguntas
  - Editar perguntas extras (8+)
  - Excluir perguntas extras com renumeração automática

---

## Arquitetura do Projeto

```
RegistrationSystem/
│
├── Application/
│   └── Main.java                 # Ponto de entrada da aplicação
│
├── Domain/
│   ├── Pet.java                  # Modelo de dados do Pet
│   ├── Address.java              # Modelo de dados do Endereço
│   ├── PetType.java              # Enum: DOG, CAT, NO_INFORMED
│   └── PetSex.java               # Enum: MALE, FEMALE, NO_INFORMED
│
├── Services/
│   └── PetSystem.java            # Lógica de negócio e controle de menus
│
├── RegisteredPets/               # Diretório com arquivos dos pets
│   └── [YYYYMMDDTHHMM-NOME.txt]
│
└── Forms.txt                     # Arquivo de perguntas do formulário
```

### Padrão de Arquitetura

O projeto segue uma arquitetura em camadas:

- **Application Layer**: Inicialização da aplicação
- **Domain Layer**: Modelos de domínio (entidades e enums)
- **Service Layer**: Lógica de negócio, validações e operações de I/O

---

## Conhecimentos Aplicados

- **Programação Orientada a Objetos (POO)**
  - Encapsulamento
  - Classes e objetos
  - Enumerations (ENUMs)
  - Métodos estáticos e de instância

- **Manipulação de Arquivos (Java I/O)**
  - `BufferedReader` e `BufferedWriter`
  - `FileReader` e `FileWriter`
  - `Files` e `Paths` (NIO)
  - `DirectoryStream`
  - Atributos de arquivo (readonly)

- **Estruturas de Dados**
  - Arrays
  - Lists (ArrayList)
  - String manipulation

- **Validação e Regex**
  - `Pattern` e `Matcher`
  - Expressões regulares complexas
  - Normalização de texto (remoção de acentos)

- **Tratamento de Exceções**
  - Try-catch blocks
  - IOException
  - Validação de entrada do usuário

- **Formatação de Data/Hora**
  - `LocalDateTime`
  - `DateTimeFormatter`

- **Princípios de Código Limpo**
  - Métodos pequenos e específicos
  - Nomenclatura clara
  - Separação de responsabilidades
  - Constantes para valores fixos

---

### Formato do Arquivo de Pet

```
1 - [Nome do Pet]
2 - [Tipo: Dog/Cat/No informed]
3 - [Sexo: Male/Female/No informed]
4 - Street: [Rua] - Number: [Número] - City: [Cidade]
5 - [Idade] years
6 - [Peso] kgs
7 - [Raça]
8 - [Resposta Extra] [EXTRA]
9 - [Resposta Extra] [EXTRA]
...
```

**Nome do Arquivo:** `YYYYMMDDTHHMM-NOMEPET.txt`  
**Exemplo:** `20231101T1234-FLORZINHADASILVA.txt`

---

## Detalhamento Técnico

### 1. Registro de Pets (`Pet.createPet`)

**Fluxo de Execução:**

1. Lê perguntas do arquivo `Forms.txt`
2. Coleta respostas do usuário com validação
3. Cria objeto `Pet` e preenche atributos
4. Salva em arquivo `.txt` com proteção readonly
5. Processa perguntas extras dinamicamente

**Validações Implementadas:**

| Campo | Regras |
|-------|--------|
| **Nome** | Obrigatório, apenas letras e espaços, sem caracteres especiais |
| **Tipo** | ENUM: Dog, Cat ou No informed |
| **Sexo** | ENUM: Male, Female ou No informed |
| **Endereço** | Três campos (rua, número, cidade), aceita vazio |
| **Idade** | Numérico, ≤ 20 anos, aceita vírgula ou ponto decimal |
| **Peso** | Numérico, entre 0.5kg e 60kg |
| **Raça** | Apenas letras, aceita vazio |

**Tecnologias Usadas:**
- Regex para validação de formato
- Pattern/Matcher para parsing
- BufferedReader para leitura de arquivo
- BufferedWriter para escrita
- Files.setAttribute para readonly

### 2. Busca de Pets (`searchPet`)

**Critérios de Busca:**

- Primeiro ou último nome
- Sexo
- Idade
- Peso
- Raça
- Endereço (rua, número ou cidade)
- Data de registro (mês/ano)
- **Combinação de 2 critérios**

**Recursos Avançados:**

- Normalização de texto (remove acentos)
- Case-insensitive matching
- Busca parcial (substring)
- Destaque visual com ANSI codes (`\033[1m`)
- DirectoryStream para leitura de múltiplos arquivos

**Exemplo de Saída:**

```
1. **FLOR**ZINHA DA SILVA - GATO - FÊMEA - Street: Rua 456 - Number: 10 - City: Seilandia - 6.0 YEARS - 5.0 KGS - SIAMÊS
```

### 3. Edição de Pets (`changePet`)

**Fluxo:**

1. Busca o oldPet usando `searchPet`
2. Usuário seleciona qual oldPet editar
3. Menu de opções de edição:
  - Nome
  - Endereço
  - Idade
  - Peso
  - Raça
4. Cria novo arquivo com timestamp atualizado
5. Exclui arquivo antigo

**Campos Não Editáveis:**
- Tipo (Dog/Cat)
- Sexo (Male/Female)

**Recurso Extra:**
- Versionamento por timestamp no nome do arquivo
- Preservação de histórico implícita

### 4. Exclusão de Pets (`deletePet`)

**Segurança:**

- Confirmação obrigatória (SIM/NÃO)
- Busca prévia para seleção
- Apenas um oldPet por vez

### 5. Sistema de Formulários

#### Criar Pergunta (`createQuestionForms`)

- Lê última pergunta do arquivo
- Incrementa numeração automaticamente
- Adiciona com tag `[EXTRA - ...]`
- Limite de 19 perguntas totais

#### Editar Pergunta (`changeQuestionForms`)

- Apenas perguntas 8+ (extras)
- Preserva respostas existentes
- Atualização in-place no arquivo

#### Excluir Pergunta (`deleteQuestionForms`)

- Apenas perguntas 8+ (extras)
- Confirmação obrigatória
- **Renumeração automática** das perguntas restantes

---

## Recursos Extras Implementados

Além dos requisitos base, foram implementados:

### 1. **Sistema de Proteção de Arquivos**
- Arquivos de pets são marcados como **readonly** após criação
- Evita alterações acidentais
- Modificações apenas através dos métodos oficiais

### 2. **Versionamento por Timestamp**
- Toda edição gera novo arquivo com timestamp atualizado
- Nome do arquivo: `YYYYMMDDTHHMM-NOME.txt`
- Histórico implícito (arquivos antigos são excluídos, mas o padrão permite rastreio)

### 3. **Busca Avançada com Destaque Visual**
- Termos encontrados aparecem em **negrito**
- Usa ANSI escape codes para formatação terminal

### 4. **Validação Robusta de Entrada**
- Tratamento de exceções em todos os inputs
- Mensagens de erro descritivas
- Loops de retry para inputs inválidos

### 5. **Normalização de Texto**
- Remove acentos para busca
- Comparações case-insensitive
- Suporte para diferentes formatos de data

---

## Como Executar

### Pré-requisitos

- Java JDK 8 ou superior
- Terminal com suporte a ANSI codes (para destaque visual)

### Estrutura de Arquivos Necessária

```
src/PROGRAMMING_Challenge_RegistrationSystem/
├── Forms.txt                    # Criar com 7 perguntas base
└── RegisteredPets/              # Criar diretório vazio
```

### Exemplo de `Forms.txt`

```
1 - What is the oldPet's first and last name?
2 - What type of animal is it? (Dog or Cat)
3 - What is the oldPet's sex? (Male or Female)
4 - Where was the oldPet found?
5 - What is the approximate age?
6 - What is the approximate weight? (in kg)
7 - What is the breed?
```

### Executar

```bash
javac StudyChallenges/RegistrationSystem/Application/Main.java
java StudyChallenges.RegistrationSystem.Main
```

### Menu Principal

```
What do you want to do?
1 - Start the system to register pets
2 - Start the system to modify the form
3 - Exit
```

---

## Resultados de Aprendizagem

Ao concluir este projeto, foram consolidados os seguintes conhecimentos:

### Programação Orientada a Objetos
- Design de classes com responsabilidades claras
- Uso adequado de ENUMs para tipos fixos
- Encapsulamento e getters/setters
- Métodos estáticos vs. de instância

### Manipulação de Arquivos
- Leitura e escrita com BufferedReader/Writer
- Navegação de diretórios com DirectoryStream
- Gestão de atributos de arquivo (readonly)
- Criação de estruturas de diretórios

### Validação e Processamento de Dados
- Expressões regulares complexas
- Pattern matching com grupos
- Normalização de strings (acentos, case)
- Parsing de diferentes formatos numéricos

### Tratamento de Exceções
- Try-catch em operações de I/O
- Validação de entrada do usuário
- Mensagens de erro informativas
- Recovery gracioso de erros

### Experiência do Usuário (CLI)
- Menus interativos claros
- Confirmações para ações destrutivas
- Feedback visual (destaque em negrito)
- Mensagens de sucesso/erro apropriadas

### Boas Práticas
- Constantes para valores fixos
- Métodos pequenos e específicos
- Nomenclatura descritiva
- Comentários onde necessário
- Separação de camadas (Domain/Service/Application)

---

## Notas Técnicas

### Limitações Conhecidas

- Sistema baseado em arquivos (não usa banco de dados)
- Busca sequencial (pode ser lenta com muitos pets)
- ANSI codes podem não funcionar em todos os terminais Windows

### Problemas encontrados na revisão algum tempo depois:

- Na revisão dos projetos foi identificado gargálos de quando fiz o sistema pela primeira vez.
- Com a melhoria do meu conhecimento, sei que preciso refatorar o sistema:
- Alguns erros de arquitetura e classes com muitas responsabilidades.
- Strings mágicas e falta de boas práticas em alguns momentos (Muitos try/catchs, e muitas verificações repetidas).
- Tudo isso será corrigido na v2.0, quando conectarmos o sistema a um Banco de Dados com mais funcionalidades.

---