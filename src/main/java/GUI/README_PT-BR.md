<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINALIZADO-success.svg)](https://shields.io/)

# Graphical User Interface (GUIs)

Este módulo contém exercícios e experimentos demonstrando o uso de GUIs em Java com Swing, integração com MIDI, e o padrão MVC aplicado a formulários com múltiplas telas.

## Tecnologias
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Visão Geral

GUI significa **Graphical User Interface**. **É tudo que você vê e clica numa tela: botões, campos de texto, menus, janelas, ícones.**

**Antes das GUIs,** para usar um computador você precisava digitar comandos de texto:

```
C:\> copy arquivo.txt destino\arquivo.txt
C:\> dir /s /b *.java
```

**Com as GUIs**, qualquer pessoa passou a conseguir usar um computador sem saber nada de programação. É por isso que **o computador saiu dos laboratórios e foi para as casas.**

**O que compõe uma GUI:**
- **Componentes** → os elementos visuais (botões, campos, tabelas...)
- **Layouts** → como esses elementos se organizam na tela
- **Eventos** → o que acontece quando o usuário interage
- **Lógica** → o código que processa essas interações

> *Inner Classes são úteis em GUI, quando quero que componentes que têm o mesmo Listener tenham comportamentos diferentes. Listeners devolvem event Objects.*

---

## Arquitetura

O projeto está organizado da seguinte forma:

- **SimpleTests**: Classes de estudo e experimentos isolados com Swing
- **BeatBox**: Aplicação completa de drum machine com GUI + MIDI
- **Challenge**: Formulário de cadastro com múltiplas telas e validações (MVC)

---

## Swing vs JavaFX

|  | Swing | JavaFX |
| --- | --- | --- |
| **Criação** | Anos 90 | Anos 2010 |
| **Instalação** | Já vem no Java | Precisa adicionar |
| **Visual padrão** | Datado | Moderno |
| **CSS** | Não suporta | Suporta nativamente |
| **Separação visual/código** | Não tem | Tem (FXML) |
| **Curva de aprendizado** | Menor | Um pouco maior |
| **Mercado** | Sistemas legados | Projetos novos |
| **Futuro** | Estagnado | Ativo |

---

## 1. Orientação a Eventos (Event-Driven)

É um **paradigma onde o fluxo do programa é controlado por eventos** — ações que acontecem durante a execução, como um clique do usuário, uma requisição HTTP ou a leitura de um arquivo. Em vez de executar o código de forma sequencial e bloqueante, o programa fica aguardando eventos através de um **event loop**, que roda continuamente verificando se algo aconteceu.

Quando um evento é detectado, o listener responsável por "escutar" aquele evento é acionado, executando seu **callback** — a função que define o que deve acontecer em resposta.

> ***O evento em si é pontual, ele dispara e desaparece, mas o event loop permanece ativo até o programa encerrar.***

**No Swing quem faz isso é a EDT (Event Dispatch Thread):**

```java
// Isso aqui inicia o loop de eventos — "liga a máquina"
SwingUtilities.invokeLater(() -> {
    // Tudo que cria ou mexe na interface precisa rodar aqui
    // porque só a EDT pode tocar nos componentes visuais
    JFrame janela = new JFrame("Meu App");
    janela.setVisible(true);
});
```

---

## 2. Listeners

### Listeners = Ouvintes

O Listener é o componente que fica observando tudo que o usuário faz na interface e avisa quando algo acontece.

```java
JButton botao = new JButton("Salvar");

// Contratando o "segurança":
botao.addActionListener(e -> {
    System.out.println("Alguém clicou no botão!");
});
```

### Tipos de Listeners no Swing

| Listener | Interface / Adapter | Quando dispara |
| --- | --- | --- |
| **Ação geral** | ActionListener | Botão clicado, Enter em TextField |
| **Teclado** | KeyListener / KeyAdapter | Tecla pressionada, solta ou digitada |
| **Mouse (clique/entrada)** | MouseListener / MouseAdapter | Clique, pressionar, entrar/sair da área |
| **Mouse (movimento)** | MouseMotionListener / Adapter | Mouse movendo ou arrastando |
| **Mudança de texto** | DocumentListener | Usuário digitou ou apagou — cada caractere |
| **Mudança de valor** | ItemListener | Item selecionado em JComboBox, JCheckBox |
| **Foco** | FocusListener | Componente ganhou ou perdeu foco |
| **Janela** | WindowListener / WindowAdapter | Janela fechada, aberta, minimizada |
| **Seleção em tabela** | ListSelectionListener | Linha selecionada na JTable ou JList |

```java
// ActionListener — vigia cliques em botões
botao.addActionListener(e -> System.out.println("Botão clicado!"));

// KeyListener — vigia o teclado
campo.addKeyListener(new KeyAdapter() {
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("Enter pressionado!");
        }
    }
});

// MouseListener — vigia o mouse
painel.addMouseListener(new MouseAdapter() {
    public void mouseEntered(MouseEvent e) { painel.setBackground(Color.LIGHT_GRAY); }
    public void mouseExited(MouseEvent e)  { painel.setBackground(Color.WHITE); }
});

// DocumentListener — vigia mudanças em tempo real
campoNome.getDocument().addDocumentListener(new DocumentListener() {
    public void insertUpdate(DocumentEvent e)  { checarTamanho(); }
    public void removeUpdate(DocumentEvent e)  { checarTamanho(); }
    public void changedUpdate(DocumentEvent e) { checarTamanho(); }
});

// WindowListener — vigia eventos da janela
janela.addWindowListener(new WindowAdapter() {
    public void windowClosing(WindowEvent e) {
        int r = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?");
        if (r == JOptionPane.YES_OPTION) System.exit(0);
    }
});
```

### Listeners no JavaFX

```java
// Clique em botão
botao.setOnAction(e -> System.out.println("Clicado!"));

// Mouse hover
botao.setOnMouseEntered(e -> botao.setStyle("-fx-background-color: lightblue;"));
botao.setOnMouseExited(e  -> botao.setStyle(""));

// Teclado
campo.setOnKeyPressed(e -> {
    if (e.getCode() == KeyCode.ENTER) { salvar(); }
});

// Property Listener — reage a mudanças em tempo real
campo.textProperty().addListener((observable, valorAntigo, valorNovo) -> {
    botaoSalvar.setDisable(valorNovo.isEmpty());
});
```

---

## 3. Callbacks

### "Me avisa quando ficar pronto"

**Callback é o que executar quando o listener for acionado.**

**Listener** → "avisa quando o evento acontecer"  
**Callback** → "o que executar quando for avisado"

```java
botao.addActionListener(e -> salvar());
          ↑ Listener              ↑ Callback
```

**Reutilizando callbacks em vários botões:**

```java
public void configurarBotao(JButton botao, String mensagem, Runnable acao) {
    botao.addActionListener(e -> {
        System.out.println(mensagem);
        acao.run();
    });
}

configurarBotao(botaoSalvar,   "Salvando...",   () -> salvarUsuario());
configurarBotao(botaoDeletar,  "Deletando...",  () -> deletarUsuario());
configurarBotao(botaoCancelar, "Cancelando...", () -> limparCampos());
```

### Interfaces Funcionais para Callbacks em Java

| Interface | Pacote | Assinatura | Para que serve |
| --- | --- | --- | --- |
| **Runnable** | java.lang | `void run()` | Executar uma ação simples. "Faça isso quando terminar." |
| **Consumer\<T>** | java.util.function | `void accept(T t)` | Receber um valor e fazer algo com ele. |
| **Supplier\<T>** | java.util.function | `T get()` | Fornecer um valor quando pedido. |
| **Function\<T,R>** | java.util.function | `R apply(T t)` | Transformar um valor em outro. |
| **Predicate\<T>** | java.util.function | `boolean test(T t)` | Testar uma condição. "Isso é válido?" |
| **BiConsumer\<T,U>** | java.util.function | `void accept(T t, U u)` | Receber dois valores e agir. |
| **EventHandler\<T>** | javafx.event | `void handle(T event)` | Handler padrão do JavaFX. |
| **ChangeListener\<T>** | javafx.beans.value | `changed(obs, old, new)` | Reagir a mudanças de Property no JavaFX. |

---

## 4. MVC — Model, View, Controller

**MVC é uma forma de organizar o código separando responsabilidades:**

- **Cardápio** (o que existe) = **Model**
- **Salão** (o que o cliente vê) = **View**
- **Garçom** (conecta os dois) = **Controller**

### Estrutura de um Sistema com MVC

```
MeuSistema/
├── model/
│   └── Usuario.java            → dados: nome, email, telefone
│
├── controller/
│   └── UsuarioController.java  → lógica: salvar, listar, deletar, validar
│
├── view/
│   ├── TelaCadastro.java       → formulário de cadastro
│   ├── TelaListagem.java       → tabela com todos os cadastros
│   └── TelaPrincipal.java      → janela principal com menu
│
└── Main.java                   → inicia o SwingUtilities.invokeLater()
```

---

## 5. Fluxo Completo

Como todos os conceitos se conectam num sistema:

```
[Componente: JButton]
        ↓ evento gerado
[Listener: ActionListener]
        ↓ executa
[Callback: () -> controller.salvar()]
        ↓ chama
[Controller: valida e processa]
        ↓ usa/cria
[Model: Usuario]
        ↓ retorna para
[View: atualiza JTable e JLabel]
        ↓
[EDT volta a esperar...]
```

1. App inicia → monta Componentes → aplica Layouts → exibe a tela
2. EDT entra em loop esperando eventos ← **Event Driven**
3. Usuário preenche os campos e clica em "Salvar"
4. O Listener no botão captura o ActionEvent ← **Listener**
5. O Callback (lambda) é executado ← **Callback**
6. A View chama `controller.salvar()` passando os dados ← **MVC (View → Controller)**
7. O Controller valida e cria o Model ← **MVC (Controller → Model)**
8. A View atualiza a tabela e exibe mensagem de sucesso ← **MVC (View atualiza)**
9. EDT volta a esperar o próximo evento ← **Event Driven**

---

## 6. Java Swing

**Faz parte do próprio Java**, no pacote `javax.swing`.

> A ideia central: **tudo é um componente**, componentes ficam dentro de outros componentes, formando uma árvore visual:

```java
JFrame (janela)
  └── JPanel (container)
        ├── JLabel     (texto fixo)
        ├── JTextField (campo editável)
        └── JButton    (botão)
```

### JFrame — A Janela Principal

```java
JFrame janela = new JFrame("Sistema de Cadastro");
janela.setSize(400, 300);
janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
janela.setLocationRelativeTo(null); // centraliza na tela
janela.setVisible(true);
```

### Componentes do Swing

| Componente | Classe | Para que serve |
| --- | --- | --- |
| Janela principal | **JFrame** | A janela em si. Tudo começa aqui. |
| Painel container | **JPanel** | Organizar componentes com layouts diferentes. |
| Texto fixo | **JLabel** | Títulos, descrições, mensagens de erro/sucesso. |
| Campo de texto | **JTextField** | Nome, email, telefone — qualquer dado de uma linha. |
| Campo de senha | **JPasswordField** | Senhas. Retorna `char[]` por segurança. |
| Área de texto | **JTextArea** | Comentários, textos longos. Sempre com JScrollPane. |
| Botão | **JButton** | Salvar, cancelar, pesquisar — qualquer ação. |
| Caixa de marcar | **JCheckBox** | Opções independentes: aceitar termos. |
| Botão de opção | **JRadioButton** | Opções excludentes. Usar com ButtonGroup. |
| Lista suspensa | **JComboBox\<T>** | Listas de opções fixas (dropdown). |
| Lista de itens | **JList\<T>** | Listas selecionáveis. Sempre com JScrollPane. |
| Tabela de dados | **JTable** | Exibir dados em grade. Sempre com JScrollPane. |
| Diálogos prontos | **JOptionPane** | Avisos, confirmações, erros — sem nova janela. |

### Layouts no Swing

| Layout | Classe | Quando usar |
| --- | --- | --- |
| Fila | **FlowLayout** | Grupos de botões. Quebra linha automaticamente. Padrão do JPanel. |
| Bússola | **BorderLayout** | Estrutura geral da janela. Padrão do JFrame. |
| Grade | **GridLayout** | Formulários com rótulo + campo. Todos os slots iguais. |
| Grade avançada | **GridBagLayout** | Layouts complexos com tamanhos diferentes. |
| Empilhado | **CardLayout** | Telas que alternam: wizard, abas customizadas. |
| Divisão | **JSplitPane** | Painéis redimensionáveis pelo usuário. |

**Combinando layouts — o segredo do Swing:**

```java
janela.setLayout(new BorderLayout());

JPanel painelFormulario = new JPanel(new GridLayout(3, 2, 10, 5));
painelFormulario.add(labelNome);  painelFormulario.add(campoNome);
painelFormulario.add(labelEmail); painelFormulario.add(campoEmail);

JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
painelBotoes.add(botaoCancelar);
painelBotoes.add(botaoSalvar);

janela.add(painelFormulario, BorderLayout.CENTER);
janela.add(painelBotoes,     BorderLayout.SOUTH);
```

---

## 7. JavaFX

### Stage e Scene

```java
public class MinhaApp extends Application {
    @Override
    public void start(Stage stage) {
        VBox layout = new VBox(10);
        Button botao = new Button("Clique aqui");
        Label label  = new Label("Olá!");
        layout.getChildren().addAll(label, botao);

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Meu App");
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}
```

### Componentes do JavaFX

| Componente | Classe | Para que serve |
| --- | --- | --- |
| Janela principal | **Stage** | A janela em si. Equivale ao JFrame. |
| Conteúdo da janela | **Scene** | Container raiz do conteúdo visual. |
| Texto fixo | **Label** | Títulos, descrições, rótulos de campos. |
| Campo de texto | **TextField** | Nome, email, telefone — dados de uma linha. |
| Campo de senha | **PasswordField** | Senhas. Visual oculto. |
| Área de texto | **TextArea** | Textos longos. Scroll automático. |
| Botão | **Button** | Salvar, cancelar — qualquer ação clicável. |
| Lista suspensa | **ComboBox\<T>** | Listas de opções fixas. |
| Tabela de dados | **TableView\<T>** | Exibir objetos em tabela. |
| Diálogos prontos | **Alert** | INFORMATION, ERROR, WARNING, CONFIRMATION. |

### Diferencial do JavaFX → Properties

```java
// No Swing você verifica manualmente
String texto = campoNome.getText();
if (texto.isEmpty()) { ... }

// No JavaFX o campo avisa sozinho quando muda
campoNome.textProperty().addListener((observable, valorAntigo, valorNovo) -> {
    botaoSalvar.setDisable(valorNovo.isEmpty());
});

// Binding — vincula dois componentes automaticamente
labelContador.textProperty().bind(
    campoNome.textProperty().length().asString("Caracteres: %d")
);
```

### Layouts no JavaFX

| Layout | Classe | Quando usar |
| --- | --- | --- |
| Pilha vertical | **VBox** | Formulários, listas, estruturas verticais. |
| Fila horizontal | **HBox** | Grupos de botões, toolbar. |
| Grade posicionada | **GridPane** | Formulários com rótulo + campo alinhados. |
| Bússola | **BorderPane** | Estrutura geral: menu, conteúdo, status. |
| Pilha sobreposta | **StackPane** | Overlay, badge em botão, loading spinner. |
| Divisão | **SplitPane** | Painéis ajustáveis pelo usuário. |

### FXML — Separando o Visual do Código

**FXML é como o HTML do JavaFX.** Em vez de criar componentes no código Java, você escreve num arquivo XML separado.

```xml
<VBox spacing="10" xmlns:fx="http://javafx.com/fxml"
      fx:controller="com.exemplo.CadastroController">
    <Label text="Nome:"/>
    <TextField fx:id="campoNome" promptText="Digite seu nome"/>
    <Button text="Salvar" onAction="#salvar"/>
    <Label fx:id="labelMensagem"/>
</VBox>
```

```java
public class CadastroController {
    @FXML private TextField campoNome;
    @FXML private Label     labelMensagem;

    @FXML
    public void salvar() {
        if (campoNome.getText().isEmpty()) {
            labelMensagem.setText("Preencha todos os campos!");
            labelMensagem.setStyle("-fx-text-fill: red;");
            return;
        }
        labelMensagem.setText("Salvo com sucesso!");
        labelMensagem.setStyle("-fx-text-fill: green;");
    }
}
```

**Sem FXML** → designer e programador editam o mesmo arquivo → conflito garantido  
**Com FXML** → designer edita o .fxml, programador edita o .java → separação clara

---

## 8. Equivalências entre Frameworks

| Conceito | Swing | JavaFX | Android | React / Web | Flutter |
| --- | --- | --- | --- | --- | --- |
| **Janela raiz** | `JFrame` | `Stage + Scene` | `Activity` | `<App />` | `MaterialApp` |
| **Container** | `JPanel` | `Pane / Group` | `ViewGroup` | `<div>` | `Container` |
| **Texto fixo** | `JLabel` | `Label` | `TextView` | `<p> <span>` | `Text` |
| **Campo editável** | `JTextField` | `TextField` | `EditText` | `<input>` | `TextField` |
| **Botão** | `JButton` | `Button` | `Button` | `<button>` | `ElevatedButton` |
| **Dropdown** | `JComboBox` | `ComboBox` | `Spinner` | `<select>` | `DropdownButton` |
| **Tabela** | `JTable` | `TableView` | `RecyclerView` | `<table>` | `DataTable` |
| **Listener de clique** | `ActionListener` | `setOnAction()` | `OnClickListener` | `onClick` | `onPressed` |
| **Listener de texto** | `DocumentListener` | `textProperty()` | `TextWatcher` | `onChange` | `onChanged` |
| **Thread da interface** | `EDT (invokeLater)` | `JavaFX Thread` | `Main Thread` | `Event Loop (JS)` | `Main Isolate` |
| **Layout vertical** | `BoxLayout (Y_AXIS)` | `VBox` | `LinearLayout v.` | `flex-direction: column` | `Column` |
| **Layout horizontal** | `BoxLayout (X_AXIS)` | `HBox` | `LinearLayout h.` | `flex-direction: row` | `Row` |
| **Layout grade** | `GridLayout` | `GridPane` | `ConstraintLayout` | `display: grid` | `GridView` |
| **Arquitetura** | `MVC` | `MVC / MVP` | `MVVM` | `Redux / Context` | `BLoC / Provider` |
| **Estado reativo** | `— (manual)` | `Property + Binding` | `LiveData / Flow` | `useState / setState` | `setState / StreamBuilder` |

---

## 9. MIDI em Java

Java possui suporte nativo a MIDI através do pacote **`javax.sound.midi`**, incluído no Java SE desde a versão 1.3. MIDI não transmite áudio — transmite **dados de controle** (notas, velocidade, canal, etc.).

### Classes principais

| Classe | Para que serve |
| --- | --- |
| **MidiSystem** | Ponto de entrada. Fornece acesso a dispositivos e sintetizadores. |
| **Synthesizer** | Gera sons MIDI internamente. |
| **Sequencer** | Reproduz e grava sequências MIDI. |
| **Sequence / Track** | Estrutura de dados que armazena eventos MIDI. |
| **ShortMessage** | Representa uma mensagem MIDI individual. |

### Constantes úteis de ShortMessage

| Constante | Valor | Descrição |
| --- | --- | --- |
| `NOTE_ON` | 0x90 | Liga uma nota |
| `NOTE_OFF` | 0x80 | Desliga uma nota |
| `CONTROL_CHANGE` | 0xB0 | Controle (volume, pan...) |
| `PROGRAM_CHANGE` | 0xC0 | Troca de instrumento |
| `PITCH_BEND` | 0xE0 | Variação de afinação |

### Exemplo: Tocar uma nota

```java
Synthesizer synth = MidiSystem.getSynthesizer();
synth.open();
MidiChannel[] channels = synth.getChannels();
channels[0].noteOn(60, 93); // nota Dó4, velocidade 93
Thread.sleep(1000);
channels[0].noteOff(60);
synth.close();
```

---

## 10. Classes do Módulo

### SimpleTests — Experimentos Isolados

**`SimpleGui.java`**  
Demonstra uso básico de JFrame, JPanel, JButton e JLabel com custom painting usando `Graphics2D`. Inclui gradiente de cores aleatório e exibição de imagem via `ImagePanel` (inner class). Mostra também o uso de lambdas no lugar de `ActionListener` com inner class explícita (código comentado inclui a versão com classe).

**`TextGUI.java`**  
Demonstra uso de `JTextArea` com `JScrollPane` (scroll vertical fixo, horizontal desabilitado) e `ActionListener` em botão para append de texto em tempo real com `textArea.append()`.

**`AnimationGUI.java`**  
Demonstra animação simples com `repaint()` em loop, custom painting com `Graphics` e controle de posição (`xPos`, `yPos`). A inner class `MyDrawPanel extends JPanel` sobrescreve `paintComponent(Graphics g)`.

**`MiniMusicPlayer.java`**  
Demonstra integração entre MIDI e GUI: `ControllerEventListener` dispara `repaint()` a cada nota tocada, gerando retângulos coloridos aleatórios no painel — sincronizados com a música. A inner class `MyDrawPanel` implementa `ControllerEventListener`.

### BeatBox — Drum Machine Completa

Aplicação completa com **16 instrumentos × 16 passos**. Utiliza:
- `GridLayout` para a grade de `JCheckBox`
- `BoxLayout` (Y_AXIS) para os botões laterais
- `sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY)` para loop infinito
- `sequence.deleteTrack(track)` + recriação para atualizar a sequência ao vivo

### Challenge — Formulário com Múltiplas Telas

Exercício de cadastro com **duas janelas** e **validação completa**, seguindo o padrão **MVC**.

**Arquitetura:**
```
Challenge/
├── Controller/
│   └── CheckDatas.java       → valida todos os campos, retorna String de erro ou null
└── View/
    ├── RegistrationForm.java → tela 1: formulário com JTextField e JPasswordField
    ├── Confirmation.java     → tela 2: resumo pós-cadastro
    └── Main.java             → ponto de entrada
```

**Validações implementadas:**
- Nome: obrigatório, mínimo 3 caracteres
- Email: deve conter `@` e `.` (regex)
- Idade: numérica, entre 1 e 120
- Senha: obrigatória, mínimo 6 caracteres

**Destaques do código:**
- `CheckDatas.validate()` retorna `null` se tudo ok, ou a mensagem de erro — centralizando toda lógica de validação no controller
- *Early return* no listener: `if (error != null) { JOptionPane...; return; }` — evita aninhamento
- `jFrame.dispose()` antes de abrir a Tela 2 — fecha sem encerrar o programa

---