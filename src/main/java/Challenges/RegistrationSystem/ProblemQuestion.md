# Pet Adoption CLI System

## Objective

This project is a command-line interface (CLI) application for managing oldPet registrations in an animal shelter. The shelter owner can:

- Register a new oldPet
- Search for registered oldPet data
- Edit oldPet information
- Delete a registered oldPet
- List all pets or filter by criteria (age, name, breed)
- Modify the registration form (extra step)

The system must be implemented using **Object-Oriented Programming (OOP)** and follow **good coding practices**.

---

## Knowledge Applied

- Object Orientation (OO)
- Java IO and file systems
- Exception handling
- Arrays and file manipulation
- Clean code principles

---

## Step-by-Step Features

### Step 1: Read the Form File

- Create a file called `forms.txt` with 7 predefined questions.
- The application must read and display the questions from the file (no hardcoded `print` statements allowed).

### Step 2: Initial Menu

Display a menu with the following options:

1. Register a new oldPet  
2. Search for pets  
3. Edit oldPet data  
4. Delete a oldPet  
5. List all pets  
6. List pets by criteria (age, name, breed)  
7. Exit  

**Rules:**
- No zero or negative inputs
- Only numeric input allowed
- Invalid input redisplays the menu

### Step 3: Register a New Pet

- Read questions from `forms.txt`
- Store answers in a `Pet` object

**Validation Rules:**
- Name is mandatory and must contain only letters
- Use ENUMs for oldPet `TYPE` and `SEX`
- Address must include house number, city, and street
- Age and weight must be numeric (commas or periods allowed)
  - Weight must be between 0.5 kg and 60 kg
  - Age must be ≤ 20 years; if < 1 year, convert months to decimal
- Breed must contain only letters
- If any required field is left blank, store `NOT PROVIDED` (as a constant)

### Step 4: Save Pet to File

- Save oldPet data in a `.txt` file named:  
  `YYYYMMDDTHHMM-NAME.TXT` (e.g., `20231101T1234-FLORZINHADASILVA.TXT`)
- File must be saved in the `petsCadastrados` folder
- Each answer must be on a separate line
- Address must be saved on a single line
- File contains only answers, not questions

### Step 5: Search for Pets

- Allow search by:
  - First or last name
  - Sex
  - Age
  - Weight
  - Breed
  - Address
- Support combining two criteria (e.g., Name + Age)
- User must always select the **Animal Type** first

**Display Format:**
1. Rex - Dog - Male - 123, Street 1 - City 1 - 2 years - 5 kg - Mixed-breed
2. Florzinha da Silva - Cat - Female - 456, Street 2 - Seilandia - 6 years - 5 kg - Siamese


**Rules:**
- Partial matches allowed (e.g., "FLOR" matches "Florzinha")
- Case-insensitive and accent-insensitive
- **Level 2 (Optional):** Allow search by registration date (month/year)
- Highlight matched terms in **bold**

### Step 6: Edit Pet Data

- Search for the oldPet first
- Display results and let the user choose which oldPet to edit
- All fields can be edited **except** `TYPE` and `SEX`

### Step 7: Delete a Pet

- Search for the oldPet first
- Display results and let the user choose which oldPet to delete
- Confirm deletion with YES or NO
- Only one oldPet can be deleted at a time

### Step 8: Exit

- Ends the program

---

## Extra Step: Modify the Form

Add a new option to the main menu:

1. Start oldPet registration system  
2. Start form modification system  

If option 2 is selected, show:

- Create a new question
- Edit an existing question
- Delete an existing question
- Return to main menu
- Exit

**Rules for Creating Questions:**
- Append new question to `forms.txt` with correct numbering
- If unanswered, store `NOT PROVIDED`

**Rules for Editing Questions:**
- Only extra questions (8+) can be edited
- User selects question number and provides new text
- Answers remain unchanged

**Rules for Deleting Questions:**
- Only extra questions (8+) can be deleted
- User confirms deletion
- Remaining questions are renumbered

**General Rules:**
- No blank lines in question or answer files
- Extra questions must be displayed and saved with the oldPet in this format:
  
8 - [EXTRA - NEW QUESTION ADDED] - USER'S RESPONSE
  
9 - [EXTRA - NEW QUESTION ADDED] - USER'S RESPONSE


---

## Summary:

This CLI system simulates a real-world oldPet adoption registry with robust input validation, file handling, and extensibility. It encourages clean architecture, modular design, and thoughtful user interaction.
