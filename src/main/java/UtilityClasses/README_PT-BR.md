<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINALIZADO-success.svg)](https://shields.io/)

# Utility Classes

Este módulo contém uma série abrangente de exercícios demonstrando o uso de Classes Utilitárias em Java, ferramentas essenciais que melhoram a qualidade do código, performance e manutenibilidade através de funcionalidades pré-construídas e otimizadas.

## Tecnologias
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Visão Geral

Classes Utilitárias são **classes que contêm apenas métodos estáticos e constantes**, servindo como **coleções de funcionalidades relacionadas**. Elas **não devem ser instanciadas e geralmente resolvem problemas comuns de programação**.

**Importante:** Classes utilitárias **ajudam a melhorar a performance da aplicação e a qualidade do código** fornecendo implementações bem testadas e otimizadas. O Java inclui muitas classes utilitárias nativas, e você pode criar personalizadas seguindo as melhores práticas.

---

## Arquitetura

O projeto está organizado da seguinte forma:

- **ProblemQuestion**: Contém as implementações dos exercícios
- **Main**: Ponto de entrada para executar cada exercício
- **Player** (apenas Serialization): Classe modelo para demonstrar persistência de objetos
- **18 Exercícios Progressivos**: Do básico ao avançado, cobrindo todas as principais categorias de classes utilitárias

---

## 1. O que são Classes Utilitárias

Classes utilitárias contêm **métodos estáticos e constantes** que fornecem funcionalidades reutilizáveis sem exigir instanciação de objetos. Elas seguem uma estrutura específica:

### 1.1. Estrutura Típica
```java
public final class StringUtils {
    // Construtor privado previne instanciação
    private StringUtils() {
        throw new AssertionError("Cannot instantiate utility class");
    }
    
    // Todos os métodos são estáticos
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
```

### 1.2. Características Principais

- Todos os métodos são `static`
- Construtor privado para prevenir instanciação
- Geralmente marcadas como `final` (opcional)
- Contêm apenas lógica auxiliar, sem estado
- Promovem reuso de código e manutenibilidade

### 1.3. Classes Utilitárias Nativas do Java

**Coleções & Arrays:**
- `java.util.Arrays` — manipulação de arrays
- `java.util.Collections` — operações em coleções
- `java.util.Objects` — verificações de null, equals, hash

**Matemática & Números:**
- `java.lang.Math` — funções matemáticas
- Classes Wrapper — Integer, Double, Boolean, etc.

**Texto & Strings:**
- `java.lang.String` — manipulação de texto
- `StringBuilder/StringBuffer` — construção eficiente de strings

**Data & Hora:**
- Legado: `Date`, `Calendar`, `DateFormat`, `SimpleDateFormat`
- Moderno: `LocalDate`, `LocalTime`, `LocalDateTime`, `Instant`
- Formatação: `DateTimeFormatter`
- Cálculos: `Period`, `ChronoUnit`, `TemporalAdjusters`

**I/O & Arquivos:**
- Legado: `File`, `FileReader`, `FileWriter`, `BufferedReader`, `BufferedWriter`
- Moderno: `Path`, `Paths`, `Files` (NIO)

**Internacionalização:**
- `Locale` — configurações de região/idioma
- `ResourceBundle` — mensagens multilíngue

**Correspondência de Padrões:**
- `Pattern` — compilação de regex
- `Matcher` — operações de correspondência de padrões

**Entrada:**
- `Scanner` — análise de texto com delimitadores

**Serialização:**
- `ObjectOutputStream` — salvar objetos em bytes
- `ObjectInputStream` — restaurar objetos de bytes

### 1.4. Boas Práticas

1. **Nomenclatura descritiva** — geralmente termina com "Utils" ou "Helper"
2. **Coesão** — agrupe apenas métodos relacionados
3. **Métodos puros** — evite efeitos colaterais quando possível
4. **Documentação clara** — JavaDoc para todos os métodos públicos
5. **Validação de parâmetros** — verifique nulls e valores inválidos

### 1.5. Quando Usar

Classes utilitárias são **ideais para funcionalidades que não pertencem naturalmente a nenhuma classe de domínio**, como conversões, validações ou operações matemáticas específicas. Evite criar "God Classes" que fazem coisas demais — mantenha cada classe utilitária focada em um propósito específico.

---

## 2. Tabela de Referência Rápida

### 2.1. Classes Wrapper

| Primitivo | Wrapper | Métodos Principais |
|-----------|---------|-------------------|
| `byte` | `Byte` | `parseByte()`, `valueOf()` |
| `short` | `Short` | `parseShort()`, `valueOf()` |
| `int` | `Integer` | `parseInt()`, `valueOf()`, `compare()`, `toBinaryString()` |
| `long` | `Long` | `parseLong()`, `valueOf()`, `toHexString()` |
| `float` | `Float` | `parseFloat()`, `valueOf()`, `isNaN()` |
| `double` | `Double` | `parseDouble()`, `valueOf()`, `isInfinite()` |
| `char` | `Character` | `isDigit()`, `isLetter()`, `toUpperCase()` |
| `boolean` | `Boolean` | `parseBoolean()`, `valueOf()` |

### 2.2. Operações com String

| Operação | Método | Uso |
|----------|--------|-----|
| **Consulta** | `length()`, `charAt()`, `isEmpty()` | Obter informações |
| **Comparação** | `equals()`, `equalsIgnoreCase()`, `compareTo()` | Comparar strings |
| **Busca** | `indexOf()`, `contains()`, `startsWith()` | Encontrar texto |
| **Modificação** | `substring()`, `replace()`, `toLowerCase()` | Transformar |
| **Dividir/Juntar** | `split()`, `String.join()` | Dividir/combinar |
| **Formatação** | `format()`, `formatted()` | Templates de strings |

### 2.3. Data & Hora (API Moderna)

| Classe | Representa | Uso |
|--------|------------|-----|
| `LocalDate` | Apenas data | Aniversários, prazos |
| `LocalTime` | Apenas hora | Horários, agendamentos |
| `LocalDateTime` | Data + Hora | Eventos, logs |
| `Instant` | Timestamp (UTC) | Backend, bancos de dados |
| `ZonedDateTime` | Data + Hora + Fuso | Apps internacionais |
| `Period` | Duração baseada em data | Anos, meses, dias |
| `ChronoUnit` | Unidades de tempo | Calcular diferenças |

### 2.4. Operações com Arquivos

| API | Classes | Uso |
|-----|---------|-----|
| **Legado** | `File`, `FileReader`, `FileWriter` | Sistemas antigos |
| **Buffered** | `BufferedReader`, `BufferedWriter` | I/O eficiente |
| **NIO (Moderno)** | `Path`, `Paths`, `Files` | Recomendado |

### 2.5. Correspondência de Padrões

| Classe | Propósito | Métodos Principais |
|--------|-----------|-------------------|
| `Pattern` | Compilar regex | `compile()` |
| `Matcher` | Corresponder texto | `find()`, `matches()`, `group()`, `replaceAll()` |

---

## 3. Exercícios

### 3.1. Wrappers & Autoboxing

**Processamento de Números com Coleções**

Converte números separados por vírgula e realiza operações.

**Conceitos-chave:**
- `Integer.parseInt()` para conversão String → int
- Autoboxing (int → Integer) e Unboxing (Integer → int)
- `ArrayList<Integer>` requer wrapper, não primitivo
- `Integer.compare()` para comparação
- Riscos de NullPointerException com unboxing

**Entrada:** "10,20,30,40"  
**Saída:** Soma: 100, Maior: 40

---

### 3.2. Strings

**Validador de CPF**

Valida formato de CPF brasileiro usando métodos de String.

**Conceitos-chave:**
- `replace()` para remover caracteres de formatação
- `length()` para validar tamanho
- `substring()` para extrair partes
- `equals()` vs `==` para comparação
- Imutabilidade de String

**Entrada:** "123.456.789-10"  
**Processar:** "12345678910"  
**Validar:** Tamanho e formato

---

### 3.3. StringBuilder

**Gerador de Relatório**

Gera relatório de 1000 linhas eficientemente.

**Conceitos-chave:**
- `StringBuilder` vs concatenação de String
- Mutabilidade e performance
- `append()` para construir strings
- `String.format()` para padding
- Convertendo para String com `toString()`

**Comentário:** Sem `StringBuilder`, 1000 concatenações criam 1000 objetos temporários! `StringBuilder` modifica o mesmo objeto, sendo muito mais eficiente.

---

### 3.4. Date (Legada)

**Aritmética de Datas**

Trabalha com timestamps e comparação de datas.

**Conceitos-chave:**
- `Date` trabalha com milissegundos desde 1970
- `getTime()` retorna timestamp
- `setTime()` modifica data (mutável!)
- `before()` e `after()` para comparação
- Por que Date é deprecada

**Comentário:** Date é legada mas ainda aparece em sistemas antigos. É mutável (pode causar bugs!) e tem métodos deprecados confusos.

---

### 3.5. Calendar (Legada)

**Calculador de Idade**

Calcula idade usando API Calendar.

**Conceitos-chave:**
- `Calendar.getInstance()` para data atual
- `set()` para definir datas específicas
- `get(Calendar.YEAR)`, `get(Calendar.MONTH)`
- `add()` para modificar datas
- Mês começa em 0 (Janeiro=0, Dezembro=11)

**Comentário:** Calendar tentou corrigir os problemas de Date mas ainda é legada. O mês começando em 0 é muito confuso e foi corrigido em `java.time`.

---

### 3.6. DateFormat & SimpleDateFormat

**Conversor de Formato de Data**

Converte entre formatos de data e locales.

**Conceitos-chave:**
- `SimpleDateFormat` para padrões personalizados
- `parse()` converte String → Date
- `format()` converte Date → String
- `setLenient(false)` para rejeitar datas inválidas
- Símbolos de padrão: dd, MM, yyyy, HH, mm, ss

**Comentário:** `SimpleDateFormat` NÃO é thread-safe! Compartilhar a mesma instância entre threads pode causar resultados errados. Sempre crie novas instâncias ou use `DateTimeFormatter` (Java 8+).

---

### 3.7. Locale

**Formatador Internacional de Preço**

Formata moeda para diferentes países.

**Conceitos-chave:**
- `Locale.of("pt", "BR")` para Português/Brasil
- `NumberFormat.getCurrencyInstance(locale)`
- Diferentes separadores decimais (. vs ,)
- Diferentes símbolos de moeda (R$, $, ¥, €)
- Formatação de data e número por região

**Comentário:** Locale é essencial para aplicações internacionais. Cada país tem suas próprias regras para formatar datas, números e moeda. O Java já tem isso pronto!

**Entrada:** 1234.56  
**Saídas:**
- Brasil: R$ 1.234,56
- EUA: $1,234.56
- Japão: ¥1,235
- Alemanha: 1.234,56 €

---

### 3.8. LocalDate, LocalTime, LocalDateTime

**Agendador de Consultas**

Combina datas e horas usando API moderna.

**Conceitos-chave:**
- `LocalDate.of()` para datas específicas
- `LocalTime.of()` para horas específicas
- `LocalDateTime.of()` combinando ambos
- `plusWeeks()`, `plusDays()` para cálculos
- `getDayOfWeek()` para verificar dia da semana
- `format()` com `DateTimeFormatter`

---

### 3.9. Period & ChronoUnit

**Calculador de Tempo de Empresa**

Calcula duração de emprego.

**Conceitos-chave:**
- `Period.between()` para diferença legível
- Retorna anos, meses, dias separadamente
- `ChronoUnit.DAYS.between()` para dias totais
- Quando usar Period vs ChronoUnit

**Comentário:** `Period` te dá a diferença "humana" (5 anos, 2 meses, 10 dias), enquanto `ChronoUnit` te dá o total absoluto (1868 dias). Ambos são úteis dependendo do contexto!

---

### 3.10. TemporalAdjusters

**Gerador de Datas de Folha de Pagamento**

Encontra datas críticas de negócio automaticamente.

**Conceitos-chave:**
- `TemporalAdjusters.lastDayOfMonth()`
- `TemporalAdjusters.next(DayOfWeek.MONDAY)`
- `firstDayOfMonth()`, `firstDayOfYear()`
- Evitando cálculos manuais de calendário
- Implementações de regras de negócio

**Comentário:** `TemporalAdjusters` evita que você tenha que fazer cálculos manuais complexos com calendário. Muito útil para regras de negócio!

---

### 3.11. ZonedDateTime & ZoneId

**Conversor de Fuso Horário**

Converte horários entre diferentes fusos horários.

**Conceitos-chave:**
- `ZoneId.of("America/Sao_Paulo")`
- `ZonedDateTime` armazena informação completa de fuso
- `withZoneSameInstant()` para conversão
- Diferença entre `ZonedDateTime` e `OffsetDateTime`
- UTC vs horário local

**Comentário:** `ZonedDateTime` guarda o fuso completo (America/Sao_Paulo), enquanto `OffsetDateTime` guarda só o offset (-03:00). Use `ZonedDateTime` quando precisar de conversões corretas de fuso!

---

### 3.12. DateTimeFormatter

**Analisador de Log**

Extrai e reformata datas de entradas de log.

**Conceitos-chave:**
- `DateTimeFormatter.ofPattern()`
- Padrões personalizados: "yyyy-MM-dd HH:mm:ss"
- `parse()` converte String → LocalDateTime
- `format()` converte LocalDateTime → String
- Formatação consciente de Locale

**Entrada:** "2026-02-11 14:30:45 - Erro no sistema"  
**Saída:** "11/02/2026 às 14h30"

---

### 3.13. ResourceBundle

**Sistema Multilíngue**

Carrega mensagens baseadas no locale do usuário.

**Conceitos-chave:**
- Arquivos `.properties` para cada idioma
- `messages_pt_BR.properties`, `messages_en_US.properties`
- `ResourceBundle.getBundle("messages", locale)`
- `getString(key)` para recuperar mensagens
- Fallback para arquivo properties padrão

**Comentário:** ResourceBundle é fundamental para internacionalização (i18n). Em produção, você teria centenas de chaves para traduzir toda a aplicação!

**Arquivos:**
- `messages_pt_BR.properties`: welcome=Bem-vindo
- `messages_en_US.properties`: welcome=Welcome

---

### 3.14. Regex - Pattern & Matcher

**Extrator de Email**

Encontra e extrai endereços de email de texto.

**Conceitos-chave:**
- `Pattern.compile(regex)` para compilar padrão
- `Matcher.find()` para buscar em loop
- `group()` para extrair texto correspondido
- `start()` e `end()` para posições
- `replaceAll()` para substituição
- Padrões comuns: `\d`, `\w`, `\.`, `+`, `*`

**Comentário:** Regex é poderoso mas complexo. Comece simples e vá evoluindo. `find()` procura padrões no texto, enquanto `matches()` valida se o texto INTEIRO bate com o padrão.

**Entrada:** "Contato: joao@email.com ou maria@empresa.com"  
**Regex:** `[\w.-]+@[\w.-]+\.\w+`

---

### 3.15. Scanner com Delimitadores

**Leitor de CSV**

Analisa texto delimitado eficientemente.

**Conceitos-chave:**
- `useDelimiter()` para mudar separador de token
- Delimitador padrão é espaço em branco
- `next()` lê próximo token
- `hasNext()` verifica mais tokens
- `nextInt()`, `nextDouble()` para leitura tipada
- Delimitadores personalizados com regex

**Comentário:** Scanner é ótimo para parsing simples. O delimitador define onde "quebrar" o texto. Por padrão é espaço, mas você pode mudar para qualquer regex!

**Entrada:** "João;25;São Paulo|Maria;30;Rio de Janeiro"  
**Delimitador:** `\\|` para pessoa, `;` para campos

---

### 3.16. File & IO Básico

**Sistema de Log Simples**

Cria, escreve e lê arquivos de log.

**Conceitos-chave:**
- `File.exists()` para verificar existência
- `mkdir()` para criar diretório
- `BufferedWriter` para escrita eficiente
- Modo append com `FileWriter(file, true)`
- `BufferedReader` com `readLine()` para leitura
- `File.length()` para tamanho do arquivo
- Try-with-resources para fechamento automático

**Comentário:** Sempre use `BufferedReader/Writer` em produção! Ler/escrever caractere por caractere com `FileReader/Writer` é muito lento. O buffer acumula dados na memória antes de gravar no disco.

---

### 3.17. NIO - Path, Paths, Files

**Organizador de Arquivos**

Operações modernas de arquivo com API NIO.

**Conceitos-chave:**
- `Paths.get()` para criar Path
- `Files.write()` para criar e escrever
- `Files.readAllLines()` para ler todo conteúdo
- `Files.list()` para listar diretório
- `Path.resolve()` para combinar caminhos
- `Files.copy()` para copiar arquivos
- NIO é a API moderna e recomendada

**Comentário:** NIO é a API moderna! `Files` tem métodos para TUDO: criar, deletar, copiar, mover, ler, escrever. Muito mais simples que `File` da API antiga.

**Operações:**
- Criar 3 arquivos texto
- Listar todos arquivos .txt
- Ler conteúdo
- Criar diretório backup
- Copiar arquivos para backup

---

### 3.18. Serialização

**Sistema de Salvamento de Jogo**

Persiste objetos em disco e os restaura.

**Conceitos-chave:**
- Interface marcadora `implements Serializable`
- `serialVersionUID` para controle de versão
- `ObjectOutputStream.writeObject()` para salvar
- `ObjectInputStream.readObject()` para restaurar
- Palavra-chave `transient` exclui campos
- Try-with-resources para gerenciamento de stream
- Cast necessário ao desserializar

**Comentário:** Serialização transforma objetos em bytes. Hoje em dia, JSON é mais usado, mas serialização Java ainda aparece em cache, sessões e sistemas legados. `transient` é importante para não salvar dados sensíveis como senhas!

**Classe Player:**
- Serializado: nome, nível, pontos
- NÃO serializado (transient): senha

---

## 4. Resultados

**Domínio completo de classes wrapper**
- Mecanismos de autoboxing e unboxing
- Conversão entre primitivos e objetos
- Considerações de performance
- Consciência de NullPointerException

**Expertise em manipulação de String**
- Compreensão de imutabilidade
- Construção eficiente de strings com StringBuilder
- Operações de formatação e parsing
- Expressões regulares para correspondência de padrões

**Proficiência em data e hora**
- API legada (Date, Calendar) para manutenção
- API moderna (java.time) para novo desenvolvimento
- Manipulação de fuso horário
- Cálculos e ajustes de período
- Formatação consciente de Locale

**Domínio de I/O de arquivos**
- Compreensão da API File legada
- NIO moderno para código de produção
- Operações buffered para performance
- Gerenciamento de recursos com try-with-resources

**Habilidades de internacionalização**
- Locale para formatação específica de região
- ResourceBundle para apps multilíngue
- Formatação de moeda e número
- Formatação de data entre culturas

**Persistência de objetos**
- Mecanismo de serialização
- Controle de versão com serialVersionUID
- Proteção de dados sensíveis com transient
- Gerenciamento de stream

**Boas práticas**
- Escolha de classes utilitárias apropriadas
- Otimização de performance
- Legibilidade e manutenibilidade do código
- Consciência de thread-safety
- Gerenciamento de recursos

**Aplicações do mundo real**
- Validação e conversão de dados
- Geração de relatórios
- Processamento de logs
- Gerenciamento de arquivos
- Aplicações internacionais
- Manutenção de sistemas legados

---