# Sistema de Adoção de Pets (CLI)

---

## Objetivo
Este projeto é uma aplicação de interface de linha de comando (CLI) para gerenciar registros de pets em um abrigo de animais. O dono do abrigo pode:

- Registrar um novo oldPet  
- Pesquisar dados de pets registrados  
- Editar informações de pets  
- Excluir um oldPet registrado  
- Listar todos os pets ou filtrar por critérios (idade, nome, raça)  
- Modificar o formulário de registro (passo extra)  

O sistema deve ser implementado utilizando Programação Orientada a Objetos (POO) e seguir boas práticas de codificação.

---

## Conhecimentos Aplicados
- Orientação a Objetos (OO)  
- Java IO e sistemas de arquivos  
- Tratamento de exceções  
- Arrays e manipulação de arquivos  
- Princípios de código limpo  

---

## Funcionalidades Passo a Passo

### Passo 1: Ler o Arquivo de Formulário
Criar um arquivo chamado `forms.txt` com 7 perguntas pré-definidas.  
A aplicação deve ler e exibir as perguntas do arquivo (não são permitidos `print` fixos no código).

---

### Passo 2: Menu Inicial
Exibir um menu com as seguintes opções:

- Registrar um novo oldPet  
- Pesquisar pets  
- Editar dados de pets  
- Excluir um oldPet  
- Listar todos os pets  
- Listar pets por critério (idade, nome, raça)  
- Sair  

**Regras:**
- Não permitir entradas zero ou negativas  
- Apenas entradas numéricas são aceitas  
- Entrada inválida deve redisplayar o menu  

---

### Passo 3: Registrar um Novo Pet
Ler perguntas do `forms.txt`  
Armazenar respostas em um objeto `Pet`  

**Regras de Validação:**
- Nome é obrigatório e deve conter apenas letras  
- Usar ENUMs para o **TIPO** e **SEXO** do oldPet  
- Endereço deve incluir número da casa, cidade e rua  
- Idade e peso devem ser numéricos (vírgulas ou pontos permitidos)  
- Peso deve estar entre 0,5 kg e 60 kg  
- Idade deve ser ≤ 20 anos; se < 1 ano, converter meses para decimal  
- Raça deve conter apenas letras  
- Se algum campo obrigatório for deixado em branco, armazenar `NÃO INFORMADO` (constante)  

---

### Passo 4: Salvar Pet em Arquivo
Salvar dados do oldPet em um arquivo `.txt` nomeado:  

`YYYYMMDDTHHMM-NOME.TXT`  
Exemplo: `20231101T1234-FLORZINHADASILVA.TXT`  

- Arquivo deve ser salvo na pasta `petsCadastrados`  
- Cada resposta deve estar em uma linha separada  
- Endereço deve ser salvo em uma única linha  
- Arquivo contém apenas respostas, não perguntas  

---

### Passo 5: Pesquisar Pets
Permitir pesquisa por:  
- Primeiro ou último nome  
- Sexo  
- Idade  
- Peso  
- Raça  
- Endereço  

Suporta combinação de dois critérios (ex.: Nome + Idade).  
Usuário deve sempre selecionar primeiro o Tipo de Animal.  

**Formato de Exibição:**

`Rex - Cachorro - Macho - Rua 123 - Cidade 1 - 2 anos - 5 kg - Sem Raça Definida`

`Florzinha da Silva - Gato - Fêmea - Rua 456 - Seilandia - 6 anos - 5 kg - Siamês`


**Regras:**
- Permitir correspondências parciais (ex.: "FLOR" corresponde a "Florzinha")  
- Case-insensitive e accent-insensitive  
- Nível 2 (Opcional): permitir pesquisa por data de registro (mês/ano)  
- Destacar termos correspondentes em **negrito**  

---

### Passo 6: Editar Dados de Pets
- Pesquisar o oldPet primeiro  
- Exibir resultados e permitir ao usuário escolher qual oldPet editar  
- Todos os campos podem ser editados, exceto **TIPO** e **SEXO**  

---

### Passo 7: Excluir um Pet
- Pesquisar o oldPet primeiro  
- Exibir resultados e permitir ao usuário escolher qual oldPet excluir  
- Confirmar exclusão com SIM ou NÃO  
- Apenas um oldPet pode ser excluído por vez  

---

### Passo 8: Sair
Finaliza o programa.

---

### Passo Extra: Modificar o Formulário
Adicionar uma nova opção ao menu principal:

- Iniciar sistema de registro de pets  
- Iniciar sistema de modificação de formulário  

Se a opção 2 for selecionada, exibir:  
- Criar uma nova pergunta  
- Editar uma pergunta existente  
- Excluir uma pergunta existente  
- Retornar ao menu principal  
- Sair  

**Regras para Criar Perguntas:**
- Adicionar nova pergunta ao `forms.txt` com numeração correta  
- Se não respondida, armazenar `NÃO INFORMADO`  

**Regras para Editar Perguntas:**
- Apenas perguntas extras (8+) podem ser editadas  
- Usuário seleciona o número da pergunta e fornece novo texto  
- Respostas permanecem inalteradas  

**Regras para Excluir Perguntas:**
- Apenas perguntas extras (8+) podem ser excluídas  
- Usuário confirma exclusão  
- Perguntas restantes são renumeradas  

**Regras Gerais:**
- Não permitir linhas em branco nos arquivos de perguntas ou respostas  
- Perguntas extras devem ser exibidas e salvas com o oldPet neste formato:  

`8 - [EXTRA - NOVA PERGUNTA ADICIONADA] - RESPOSTA DO USUÁRIO`

`9 - [EXTRA - NOVA PERGUNTA ADICIONADA] - RESPOSTA DO USUÁRIO`

---

## Resumo
Este sistema CLI simula um registro real de adoção de pets com validação robusta de entradas, manipulação de arquivos e extensibilidade.  
Ele incentiva arquitetura limpa, design modular e interação cuidadosa com o usuário.

---
