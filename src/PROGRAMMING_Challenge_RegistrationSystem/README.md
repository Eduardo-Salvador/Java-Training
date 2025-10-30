# First Major Learning Challenge

## Requirements

Check the problem description here:  
https://github.com/Eduardo-Salvador/Java-Training/blob/main/src/PROGRAMMING_Challenge_RegistrationSystem/ProblemQuestion.txt

---

## What Was Done

### 1. ENUM Classes

- Created the `PetSex` and `PetType` ENUM classes as requested.
- Both include the necessary attributes.

---

### 2. Pet Class

Implemented with the required attributes and two negotiation rules:

#### `createPet` Function

- Reads the Forms file and applies rules to the answers.
  - Only the Name is mandatory.
  - All other fields, if not answered, are filled with `NO_INFORMED`.
- Also reads the extra questions.

**Utility classes used:**
- `BufferedReader`, `Paths`, `Regex`, `Pattern`, `Matcher`
- Loop and exception controls to handle user input errors

#### Pet Object Creation

- Calls the `fillPet` function in a loop to validate and assign values to attributes.

**Utility classes used:**
- Loop controls

#### `savePet` Function

- Creates the PET `.txt` file using the current `Locale`.
- Uses `BufferedWriter` to write to the file.
- For extra questions, calls `responseQuestions`, which retrieves the user's answers and appends them directly to the file (since they are not attributes of the Pet object).

**Utility classes used:**
- `BufferedReader`, `DateTimeFormatter`, `Paths`, `BufferedWriter`, `FileWriter`, `Files`
- Loop and exception controls to handle user input errors

## EXTRA: 
An additional feature not required by the challenge:  
When creating the Pet, the `.txt` file is set to **read-only** to prevent unwanted changes. It can only be modified through the designated method.
(This was not required in the original challenge.)

---

### 3. PetSystem Class

Controls the user menus in the `main` method.

#### Menu Class

- Manages access to the two system menus.
- Uses `switch` and exception handling for errors.

#### `menuForms` Class

Manages the Forms system, allowing creation, editing, and deletion of extra questions.

##### `createQuestionForms`

- Adds new questions to the Forms file.
- Always appends below the previous question, interactively assigning the next sequential number.

**Utility classes used:**
- `BufferedReader`, `BufferedWriter`, Wrappers

##### `changeQuestionForms` (Edit)

- Edits a question without changing the answer.
- Displays questions from number 8 onward (extra questions), asks which one to edit, receives the new question, and updates the file.

**Utility classes used:**
- `BufferedReader`, `BufferedWriter`, Wrappers, `Lists`

##### `changeQuestionForms` (Delete)

- Displays questions from number 8 onward, asks which one to delete, removes it from the file, and reorders the question numbers interactively.

**Utility classes used:**
- `BufferedReader`, `BufferedWriter`, Wrappers, `Lists`

#### `menuPet` Class

Controls the Pet menu, allowing:

##### Pet Registration

- Calls the Pet class to register a new pet.

##### Search Pets

- `searchPet` method: asks for the animal type and search criteria (name, age, etc.).
- Supports combining criteria.
- Reads all files and displays matching results.

##### Change Pets

- Performs the same search as above.
- Saves results in a `String[]` using `SearchingArrays`.
- Asks which pet to modify and stores the attributes.

- Calls `changePet`, which receives the pet name from the array and asks which attribute to change.
- Stores changes in a `List` and loops through all saved answers in the file.

## EXTRA: 
Creates a new file with the same name but includes the timestamp of the change.  
Deletes the previous file.  
(This was not required in the original challenge.)

**Utility classes used:**
- Same as above, with emphasis on `File` and `Files` for file operations

##### Delete Pet

- `deletePet` function uses search to select a pet.
- Asks for confirmation and deletes the file.

##### List All Pets

- Uses `DirectoryStream` to retrieve all files.
- Reads each file using `FileReader` and `BufferedReader`.
- Uses `Regex` to extract and display only the Name attribute dynamically.

---

### Main Class

- Calls the `PetSystem` class and launches the main menu.

---

## Learning Outcomes

- Object-Oriented Programming (OOP), Polymorphism, and advanced logic
- Robust file manipulation
- Full implementation of business rules
- Creativity in delivering extra, valuable features
