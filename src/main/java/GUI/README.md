<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINISHED-success.svg)](https://shields.io/)

# Graphical User Interface (GUIs)

This module contains exercises and experiments demonstrating the use of GUIs in Java with Swing, MIDI integration, and the MVC pattern applied to forms with multiple screens.

## Technologies
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Overview

GUI stands for **Graphical User Interface**. **It's everything you see and click on a screen: buttons, text fields, menus, windows, icons.**

**Before GUIs,** to use a computer you had to type text commands:

```
C:\> copy file.txt destination\file.txt
C:\> dir /s /b *.java
```

**With GUIs**, anyone could use a computer without knowing anything about programming. That's why **computers left the laboratories and entered people's homes.**

**What composes a GUI:**
- **Components** → the visual elements (buttons, fields, tables...)
- **Layouts** → how these elements are organized on screen
- **Events** → what happens when the user interacts
- **Logic** → the code that processes these interactions

> *Inner Classes are useful in GUIs when you want components that share the same Listener to have different behaviors. Listeners return event Objects.*

---

## Architecture

The project is organized as follows:

- **SimpleTests**: Study classes and isolated experiments with Swing
- **BeatBox**: Complete drum machine application with GUI + MIDI
- **Challenge**: Registration form with multiple screens and validations (MVC)

---

## Swing vs JavaFX

|  | Swing | JavaFX |
| --- | --- | --- |
| **Created** | 1990s | 2010s |
| **Installation** | Bundled with Java | Needs to be added |
| **Default look** | Dated | Modern |
| **CSS** | Not supported | Supported natively |
| **Visual/code separation** | None | Has it (FXML) |
| **Learning curve** | Smaller | Slightly steeper |
| **Market** | Legacy systems | New projects |
| **Future** | Stagnant | Active |

---

## 1. Event-Driven Programming

A **paradigm where the program flow is controlled by events** — actions that happen during execution, such as a user click, an HTTP request, or reading a file. Instead of executing code sequentially and blocking, the program waits for events through an **event loop** that continuously checks if something happened.

When an event is detected, the listener responsible for "listening" to that event is triggered, executing its **callback** — the function that defines what should happen in response.

> ***The event itself is punctual — it fires and disappears — but the event loop stays active until the program ends.***

**In Swing, the EDT (Event Dispatch Thread) handles this:**

```java
// This starts the event loop — "turns the machine on"
SwingUtilities.invokeLater(() -> {
    // Everything that creates or touches the interface must run here
    // because only the EDT can touch visual components
    JFrame window = new JFrame("My App");
    window.setVisible(true);
});
```

---

## 2. Listeners

### Listeners = Watchers

A Listener is the component that watches everything the user does on the interface and notifies when something happens.

```java
JButton button = new JButton("Save");

// Hiring the "guard":
button.addActionListener(e -> {
    System.out.println("Someone clicked the button!");
});
```

### Swing Listener Types

| Listener | Interface / Adapter | When it fires |
| --- | --- | --- |
| **General action** | ActionListener | Button clicked, Enter in TextField |
| **Keyboard** | KeyListener / KeyAdapter | Key pressed, released or typed |
| **Mouse (click/entry)** | MouseListener / MouseAdapter | Click, press, enter/exit area |
| **Mouse (movement)** | MouseMotionListener / Adapter | Mouse moving or dragging |
| **Text change** | DocumentListener | User typed or deleted — each character |
| **Value change** | ItemListener | Item selected in JComboBox, JCheckBox |
| **Focus** | FocusListener | Component gained or lost focus |
| **Window** | WindowListener / WindowAdapter | Window closed, opened, minimized |
| **Table selection** | ListSelectionListener | Row selected in JTable or JList |

```java
// ActionListener — watches button clicks
button.addActionListener(e -> System.out.println("Button clicked!"));

// KeyListener — watches the keyboard
field.addKeyListener(new KeyAdapter() {
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("Enter pressed!");
        }
    }
});

// MouseListener — watches the mouse
panel.addMouseListener(new MouseAdapter() {
    public void mouseEntered(MouseEvent e) { panel.setBackground(Color.LIGHT_GRAY); }
    public void mouseExited(MouseEvent e)  { panel.setBackground(Color.WHITE); }
});

// DocumentListener — watches real-time changes
nameField.getDocument().addDocumentListener(new DocumentListener() {
    public void insertUpdate(DocumentEvent e)  { checkLength(); }
    public void removeUpdate(DocumentEvent e)  { checkLength(); }
    public void changedUpdate(DocumentEvent e) { checkLength(); }
});

// WindowListener — watches window events
window.addWindowListener(new WindowAdapter() {
    public void windowClosing(WindowEvent e) {
        int r = JOptionPane.showConfirmDialog(null, "Do you really want to quit?");
        if (r == JOptionPane.YES_OPTION) System.exit(0);
    }
});
```

### JavaFX Listeners

```java
// Button click
button.setOnAction(e -> System.out.println("Clicked!"));

// Mouse hover
button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: lightblue;"));
button.setOnMouseExited(e  -> button.setStyle(""));

// Keyboard
field.setOnKeyPressed(e -> {
    if (e.getCode() == KeyCode.ENTER) { save(); }
});

// Property Listener — reacts to real-time changes
field.textProperty().addListener((observable, oldValue, newValue) -> {
    saveButton.setDisable(newValue.isEmpty());
});
```

---

## 3. Callbacks

### "Notify me when it's done"

**A callback is what to execute when a listener is triggered.**

**Listener** → "notify when the event happens"  
**Callback** → "what to execute when notified"

```java
button.addActionListener(e -> save());
         ↑ Listener          ↑ Callback
```

**Reusing callbacks across multiple buttons:**

```java
public void configureButton(JButton button, String message, Runnable action) {
    button.addActionListener(e -> {
        System.out.println(message);
        action.run();
    });
}

configureButton(saveButton,   "Saving...",    () -> saveUser());
configureButton(deleteButton, "Deleting...",  () -> deleteUser());
configureButton(cancelButton, "Canceling...", () -> clearFields());
```

### Functional Interfaces for Callbacks in Java

| Interface | Package | Signature | Purpose |
| --- | --- | --- | --- |
| **Runnable** | java.lang | `void run()` | Execute a simple action. "Do this when done." |
| **Consumer\<T>** | java.util.function | `void accept(T t)` | Receive a value and do something with it. |
| **Supplier\<T>** | java.util.function | `T get()` | Provide a value when requested. |
| **Function\<T,R>** | java.util.function | `R apply(T t)` | Transform one value into another. |
| **Predicate\<T>** | java.util.function | `boolean test(T t)` | Test a condition. "Is this valid?" |
| **BiConsumer\<T,U>** | java.util.function | `void accept(T t, U u)` | Receive two values and act. |
| **EventHandler\<T>** | javafx.event | `void handle(T event)` | Default JavaFX event handler. |
| **ChangeListener\<T>** | javafx.beans.value | `changed(obs, old, new)` | React to JavaFX Property changes. |

---

## 4. MVC — Model, View, Controller

**MVC is a way to organize code by separating responsibilities:**

- **Menu** (what exists) = **Model**
- **Dining room** (what the customer sees) = **View**
- **Waiter** (connects both) = **Controller**

### MVC Project Structure

```
MySystem/
├── model/
│   └── User.java               → data: name, email, phone
│
├── controller/
│   └── UserController.java     → logic: save, list, delete, validate
│
├── view/
│   ├── RegistrationScreen.java → registration form
│   ├── ListScreen.java         → table with all registrations
│   └── MainScreen.java         → main window with menu
│
└── Main.java                   → starts SwingUtilities.invokeLater()
```

---

## 5. Complete Flow

How all concepts connect in a system:

```
[Component: JButton]
        ↓ event generated
[Listener: ActionListener]
        ↓ executes
[Callback: () -> controller.save()]
        ↓ calls
[Controller: validates and processes]
        ↓ uses/creates
[Model: User]
        ↓ returns to
[View: updates JTable and JLabel]
        ↓
[EDT goes back to waiting...]
```

1. App starts → builds Components → applies Layouts → displays screen
2. EDT enters loop waiting for events ← **Event Driven**
3. User fills fields and clicks "Save"
4. Listener on button captures ActionEvent ← **Listener**
5. Callback (lambda) is executed ← **Callback**
6. View calls `controller.save()` passing data ← **MVC (View → Controller)**
7. Controller validates and creates Model ← **MVC (Controller → Model)**
8. View updates table and shows success message ← **MVC (View updates)**
9. EDT goes back to waiting for next event ← **Event Driven**

---

## 6. Java Swing

**Part of Java itself**, in the `javax.swing` package.

> The central idea: **everything is a component**; components live inside other components, forming a visual tree:

```java
JFrame (window)
  └── JPanel (container)
        ├── JLabel     (static text)
        ├── JTextField (editable field)
        └── JButton    (button)
```

### JFrame — The Main Window

```java
JFrame window = new JFrame("Registration System");
window.setSize(400, 300);
window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
window.setLocationRelativeTo(null); // centers on screen
window.setVisible(true);
```

### Swing Components

| Component | Class | Purpose |
| --- | --- | --- |
| Main window | **JFrame** | The window itself. Everything starts here. |
| Panel container | **JPanel** | Organize components with different layouts. |
| Static text | **JLabel** | Titles, descriptions, error/success messages. |
| Text field | **JTextField** | Name, email, phone — any single-line data. |
| Password field | **JPasswordField** | Passwords. Returns `char[]` for security. |
| Text area | **JTextArea** | Comments, long text. Always with JScrollPane. |
| Button | **JButton** | Save, cancel, search — any user action. |
| Checkbox | **JCheckBox** | Independent options: accept terms. |
| Radio button | **JRadioButton** | Exclusive options. Use with ButtonGroup. |
| Dropdown | **JComboBox\<T>** | Fixed option lists (dropdown). |
| Item list | **JList\<T>** | Selectable lists. Always with JScrollPane. |
| Data table | **JTable** | Display data in grid. Always with JScrollPane. |
| Ready dialogs | **JOptionPane** | Alerts, confirmations, errors — no new window. |

### Swing Layouts

| Layout | Class | When to use |
| --- | --- | --- |
| Flow | **FlowLayout** | Button groups. Automatic line wrapping. JPanel default. |
| Compass | **BorderLayout** | General window structure. JFrame default. |
| Grid | **GridLayout** | Forms with label + field. All slots equal size. |
| Advanced grid | **GridBagLayout** | Complex layouts with different sizes. |
| Stacked | **CardLayout** | Alternating screens: wizard, custom tabs. |
| Split | **JSplitPane** | User-resizable panels. |

**Combining layouts — the secret of Swing:**

```java
window.setLayout(new BorderLayout());

JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 5));
formPanel.add(nameLabel);  formPanel.add(nameField);
formPanel.add(emailLabel); formPanel.add(emailField);

JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
buttonPanel.add(cancelButton);
buttonPanel.add(saveButton);

window.add(formPanel,   BorderLayout.CENTER);
window.add(buttonPanel, BorderLayout.SOUTH);
```

---

## 7. JavaFX

### Stage and Scene

```java
public class MyApp extends Application {
    @Override
    public void start(Stage stage) {
        VBox layout = new VBox(10);
        Button button = new Button("Click here");
        Label label   = new Label("Hello!");
        layout.getChildren().addAll(label, button);

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("My App");
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}
```

### JavaFX Components

| Component | Class | Purpose |
| --- | --- | --- |
| Main window | **Stage** | The window itself. Equivalent to JFrame. |
| Window content | **Scene** | Root container of visual content. |
| Static text | **Label** | Titles, descriptions, field labels. |
| Text field | **TextField** | Name, email, phone — single-line data. |
| Password field | **PasswordField** | Passwords. Hidden text. |
| Text area | **TextArea** | Long texts. Automatic scroll. |
| Button | **Button** | Save, cancel — any clickable action. |
| Dropdown | **ComboBox\<T>** | Fixed option lists. |
| Data table | **TableView\<T>** | Display objects in table. |
| Ready dialogs | **Alert** | INFORMATION, ERROR, WARNING, CONFIRMATION. |

### JavaFX's Differentiator → Properties

```java
// In Swing you check manually
String text = nameField.getText();
if (text.isEmpty()) { ... }

// In JavaFX the field notifies itself when it changes
nameField.textProperty().addListener((observable, oldValue, newValue) -> {
    saveButton.setDisable(newValue.isEmpty());
});

// Binding — links two components automatically
counterLabel.textProperty().bind(
    nameField.textProperty().length().asString("Characters: %d")
);
```

### JavaFX Layouts

| Layout | Class | When to use |
| --- | --- | --- |
| Vertical stack | **VBox** | Forms, lists, vertical structures. |
| Horizontal row | **HBox** | Button groups, toolbar. |
| Positioned grid | **GridPane** | Forms with aligned label + field. |
| Compass | **BorderPane** | General structure: menu, content, status. |
| Overlapping stack | **StackPane** | Overlay, badge on button, loading spinner. |
| Split | **SplitPane** | User-adjustable panels. |

### FXML — Separating Visual from Code

**FXML is like the HTML of JavaFX.** Instead of creating components in Java code, you write in a separate XML file.

```xml
<VBox spacing="10" xmlns:fx="http://javafx.com/fxml"
      fx:controller="com.example.RegistrationController">
    <Label text="Name:"/>
    <TextField fx:id="nameField" promptText="Enter your name"/>
    <Button text="Save" onAction="#save"/>
    <Label fx:id="messageLabel"/>
</VBox>
```

```java
public class RegistrationController {
    @FXML private TextField nameField;
    @FXML private Label     messageLabel;

    @FXML
    public void save() {
        if (nameField.getText().isEmpty()) {
            messageLabel.setText("Please fill all fields!");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        messageLabel.setText("Saved successfully!");
        messageLabel.setStyle("-fx-text-fill: green;");
    }
}
```

**Without FXML** → designer and developer edit the same file → guaranteed conflict  
**With FXML** → designer edits .fxml, developer edits .java → clean separation

---

## 8. Framework Equivalences

| Concept | Swing | JavaFX | Android | React / Web | Flutter |
| --- | --- | --- | --- | --- | --- |
| **Root window** | `JFrame` | `Stage + Scene` | `Activity` | `<App />` | `MaterialApp` |
| **Container** | `JPanel` | `Pane / Group` | `ViewGroup` | `<div>` | `Container` |
| **Static text** | `JLabel` | `Label` | `TextView` | `<p> <span>` | `Text` |
| **Editable field** | `JTextField` | `TextField` | `EditText` | `<input>` | `TextField` |
| **Button** | `JButton` | `Button` | `Button` | `<button>` | `ElevatedButton` |
| **Dropdown** | `JComboBox` | `ComboBox` | `Spinner` | `<select>` | `DropdownButton` |
| **Table** | `JTable` | `TableView` | `RecyclerView` | `<table>` | `DataTable` |
| **Click listener** | `ActionListener` | `setOnAction()` | `OnClickListener` | `onClick` | `onPressed` |
| **Text listener** | `DocumentListener` | `textProperty()` | `TextWatcher` | `onChange` | `onChanged` |
| **UI Thread** | `EDT (invokeLater)` | `JavaFX Thread` | `Main Thread` | `Event Loop (JS)` | `Main Isolate` |
| **Vertical layout** | `BoxLayout (Y_AXIS)` | `VBox` | `LinearLayout v.` | `flex-direction: column` | `Column` |
| **Horizontal layout** | `BoxLayout (X_AXIS)` | `HBox` | `LinearLayout h.` | `flex-direction: row` | `Row` |
| **Grid layout** | `GridLayout` | `GridPane` | `ConstraintLayout` | `display: grid` | `GridView` |
| **Architecture** | `MVC` | `MVC / MVP` | `MVVM` | `Redux / Context` | `BLoC / Provider` |
| **Reactive state** | `— (manual)` | `Property + Binding` | `LiveData / Flow` | `useState / setState` | `setState / StreamBuilder` |

---

## 9. MIDI in Java

Java has native MIDI support through the **`javax.sound.midi`** package, included in Java SE since version 1.3. MIDI does not transmit audio — it transmits **control data** (notes, velocity, channel, etc.).

### Main Classes

| Class | Purpose |
| --- | --- |
| **MidiSystem** | Entry point. Provides access to devices and synthesizers. |
| **Synthesizer** | Generates MIDI sounds internally. |
| **Sequencer** | Plays and records MIDI sequences. |
| **Sequence / Track** | Data structure that stores MIDI events. |
| **ShortMessage** | Represents an individual MIDI message. |

### Useful ShortMessage Constants

| Constant | Value | Description |
| --- | --- | --- |
| `NOTE_ON` | 0x90 | Turns a note on |
| `NOTE_OFF` | 0x80 | Turns a note off |
| `CONTROL_CHANGE` | 0xB0 | Control (volume, pan...) |
| `PROGRAM_CHANGE` | 0xC0 | Changes instrument |
| `PITCH_BEND` | 0xE0 | Pitch variation |

### Example: Playing a Note

```java
Synthesizer synth = MidiSystem.getSynthesizer();
synth.open();
MidiChannel[] channels = synth.getChannels();
channels[0].noteOn(60, 93); // note C4, velocity 93
Thread.sleep(1000);
channels[0].noteOff(60);
synth.close();
```

---

## 10. Module Classes

### SimpleTests — Isolated Experiments

**`SimpleGui.java`**  
Demonstrates basic use of JFrame, JPanel, JButton and JLabel with custom painting using `Graphics2D`. Includes random color gradients and image display via `ImagePanel` (inner class). Also shows lambda usage in place of explicit `ActionListener` inner classes (commented code includes the class-based version).

**`TextGUI.java`**  
Demonstrates use of `JTextArea` with `JScrollPane` (fixed vertical scroll, horizontal disabled) and `ActionListener` on a button for real-time text append with `textArea.append()`.

**`AnimationGUI.java`**  
Demonstrates simple animation with `repaint()` in a loop, custom painting with `Graphics`, and visual element position control (`xPos`, `yPos`). The inner class `MyDrawPanel extends JPanel` overrides `paintComponent(Graphics g)`.

**`MiniMusicPlayer.java`**  
Demonstrates MIDI and GUI integration: `ControllerEventListener` triggers `repaint()` on each note played, generating random colored rectangles on the panel — synchronized with the music. The inner class `MyDrawPanel` implements `ControllerEventListener`.

### BeatBox — Complete Drum Machine

Complete application with **16 instruments × 16 steps**. Uses:
- `GridLayout` for the `JCheckBox` grid
- `BoxLayout` (Y_AXIS) for side buttons
- `sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY)` for infinite loop
- `sequence.deleteTrack(track)` + recreation to update the sequence live

### Challenge — Form with Multiple Screens

Registration exercise with **two windows** and **complete validation**, following the **MVC** pattern.

**Architecture:**
```
Challenge/
├── Controller/
│   └── CheckDatas.java       → validates all fields, returns error String or null
└── View/
    ├── RegistrationForm.java → screen 1: form with JTextField and JPasswordField
    ├── Confirmation.java     → screen 2: post-registration summary
    └── Main.java             → entry point
```

**Implemented validations:**
- Name: required, minimum 3 characters
- Email: must contain `@` and `.` (regex)
- Age: numeric, between 1 and 120
- Password: required, minimum 6 characters

**Code highlights:**
- `CheckDatas.validate()` returns `null` if everything is ok, or the error message — centralizing all validation logic in the controller
- *Early return* in the listener: `if (error != null) { JOptionPane...; return; }` — avoids nesting
- `jFrame.dispose()` before opening Screen 2 — closes without ending the program

---