# Primeiro Grande Desafio

## Requisitos

Confira a descrição do problema aqui:  
https://github.com/Eduardo-Salvador/Java-Training/blob/main/src/PROGRAMMING_Challenge_RegistrationSystem/ProblemQuestion.md

---

## O Que Foi Feito

### 1. Classes ENUM

- Criadas as classes ENUM `PetSex` e `PetType`, conforme solicitado.
- Ambas incluem os atributos necessários.

---

### 2. Classe Pet

Implementada com os atributos exigidos e duas regras de negociação:

#### Função `createPet`

- Lê o arquivo Forms e aplica regras às respostas.
    - Apenas o Nome é obrigatório.
    - Todos os outros campos, se não respondidos, são preenchidos com `NO_INFORMED`.
- Também lê as perguntas extras.

**Classes utilitárias usadas:**
- `BufferedReader`, `Paths`, `Regex`, `Pattern`, `Matcher`
- Controles de loop e exceções para tratar erros de entrada do usuário

#### Criação do Objeto Pet

- Chama a função `fillPet` em um loop para validar e atribuir valores aos atributos.

**Classes utilitárias usadas:**
- Controles de loop

#### Função `savePet`

- Cria o arquivo PET `.txt` usando o `Locale` atual.
- Usa `BufferedWriter` para escrever no arquivo.
- Para perguntas extras, chama `responseQuestions`, que recupera as respostas do usuário e as adiciona diretamente ao arquivo (já que não são atributos do objeto Pet).

**Classes utilitárias usadas:**
- `BufferedReader`, `DateTimeFormatter`, `Paths`, `BufferedWriter`, `FileWriter`, `Files`
- Controles de loop e exceções para tratar erros de entrada do usuário

## EXTRA:
Um recurso adicional não exigido pelo desafio:  
Ao criar o Pet, o arquivo `.txt` é definido como **somente leitura** para evitar alterações indesejadas. Ele só pode ser modificado através do método designado.  
(Isto não foi exigido no desafio original.)

---

### 3. Classe PetSystem

Controla os menus do usuário no método `main`.

#### Classe Menu

- Gerencia o acesso aos dois menus do sistema.
- Usa `switch` e tratamento de exceções para erros.

#### Classe `menuForms`

Gerencia o sistema de Forms, permitindo criação, edição e exclusão de perguntas extras.

##### `createQuestionForms`

- Adiciona novas perguntas ao arquivo Forms.
- Sempre insere abaixo da pergunta anterior, atribuindo interativamente o próximo número sequencial.

**Classes utilitárias usadas:**
- `BufferedReader`, `BufferedWriter`, Wrappers

##### `changeQuestionForms` (Editar)

- Edita uma pergunta sem alterar a resposta.
- Exibe perguntas a partir do número 8 (perguntas extras), pergunta qual editar, recebe a nova pergunta e atualiza o arquivo.

**Classes utilitárias usadas:**
- `BufferedReader`, `BufferedWriter`, Wrappers, `Lists`

##### `changeQuestionForms` (Excluir)

- Exibe perguntas a partir do número 8, pergunta qual excluir, remove do arquivo e reordena os números das perguntas interativamente.

**Classes utilitárias usadas:**
- `BufferedReader`, `BufferedWriter`, Wrappers, `Lists`

#### Classe `menuPet`

Controla o menu Pet, permitindo:

##### Registro de Pet

- Chama a classe Pet para registrar um novo animal.

##### Buscar Pets

- Método `searchPet`: pergunta o tipo de animal e o critério de busca (nome, idade, etc.).
- Suporta combinação de critérios.
- Lê todos os arquivos e exibe os resultados correspondentes.

##### Alterar Pets

- Realiza a mesma busca acima.
- Salva os resultados em um `String[]` usando `SearchingArrays`.
- Pergunta qual pet modificar e armazena os atributos.

- Chama `changePet`, que recebe o nome do pet do array e pergunta qual atributo alterar.
- Armazena as alterações em uma `List` e percorre todas as respostas salvas no arquivo.

## EXTRA:
Cria um novo arquivo com o mesmo nome, mas inclui o timestamp da alteração.  
Exclui o arquivo anterior.  
(Isto não foi exigido no desafio original.)

**Classes utilitárias usadas:**
- As mesmas acima, com ênfase em `File` e `Files` para operações de arquivo

##### Excluir Pet

- A função `deletePet` usa a busca para selecionar um pet.
- Pede confirmação e exclui o arquivo.

##### Listar Todos os Pets

- Usa `DirectoryStream` para recuperar todos os arquivos.
- Lê cada arquivo usando `FileReader` e `BufferedReader`.
- Usa `Regex` para extrair e exibir dinamicamente apenas o atributo Nome.

---

### Classe Main

- Chama a classe `PetSystem` e inicia o menu principal.

---

## Resultados de Aprendizagem

- Programação Orientada a Objetos (POO), Polimorfismo e lógica avançada
- Manipulação robusta de arquivos
- Implementação completa das regras de negócio
- Criatividade na entrega de recursos extras e valiosos
