# Book Management System

A console-based book management system written in Java. It simulates the administration of a library with multiple book types, allowing you to add printed books and audio books, display records, compute statistics, delete entries, and save or load the collection from a file.

--- 

## Features
 -**Two book types** — PrintedBook (priced by pages) and AudioBook (priced by minutes)
 
 -**Full book records** — title, author, genre, base cost, and type-specific attributes
 
 -**Patient admission** — register a new patient into any free bed
 
 -**Add books** — register printed or audio books with all required information
 
 -**Delete books** — remove a book by index or by title
 
 -**Display options** — view all books, the last six books, or the last three of each specific type
 
 -**Statistics report** — count books by genre, compute total cost, average pages, and average audio length
 
 -**File persistence** — save the library to a file and load it back
 
 -**Interactive menu** — a looped console menu with 14 options for full library management

---

## Requirements
 -Java 8+ (uses standard library only)
 
 -No third-party dependencies

 ---

## Compilation & Usage
 
## Linux / macOS

bash
javac *.java
java Test
Windows
bash
javac *.java
java Test

**On Windows**, the program runs directly in the console; no special screen-clearing commands are used.

---

## Menu Options
When you run the program, you'll see the main menu:

text

===========================================

Book Application Menu:

1.- Add Printed Book

2.- Add Audio Book

3.- Display Last Six Books

4.- Get Number of Books by Genre

5.- Get Total Cost of ALL Books

6.- Display Last Three Printed Books

7.- Display Last Three Audio Books

8.- Compute Average Pages of Printed Books

9.- Compute Average Length of Audio Books

10.- Save Books to File

11.- Load Books from File

12.- Delete a Book

13.- Display All Books

14.- Exit

===========================================

Choose an option:

---

## Option	Description

1	Add Printed Book — register a new printed book

2	Add Audio Book — register a new audio book

3	Display Last Six Books — show the six most recent books

4	Get Number of Books by Genre — count books of a given genre

5	Get Total Cost of ALL Books — sum of all calculated costs

6	Display Last Three Printed Books — last three printed books

7	Display Last Three Audio Books — last three audio books

8	Compute Average Pages — average pages of printed books

9	Compute Average Length — average minutes of audio books

10	Save Books to File — write the library to a file

11	Load Books from File — rebuild the library from a file

12	Delete a Book — remove by index or by title

13	Display All Books — list every book with an index

14	Exit — close the system

---

## Project Structure
The program is organized into several classes, each representing a domain concept:


| Class                   | Responsibility                                                       |
|-------------------------|-----------------------------------------------------------------------|
| `BookInterface`         | Defines the contract and default displayLastSix method                |
| `Book`                  | Abstract base class with shared fields, file I/O, deletion, statistics|
| `PrintedBook`           | Concrete book type priced by pages                                    |
| `Audiobook`             | Concrete book type priced by minutes                                  |
| `Test`                  | Console UI and main loop                                              |
| `TestCases`             | Simple test harness to verify functionality                           |

	
---

## Book Types
**PrintedBook: cost = totalPages × 10.0**

**AudioBook: cost = totalMinutes × 5.0**

Each book also stores a base cost, title, author, and genre.

---

## Data Handling Notes
All data lives in memory only until saved to a file.

Deleting a book removes it from the shared list and its type-specific list, and updates the static counters.

Loading from a file clears the current library and rebuilds it from the saved entries.

Input is read through Scanner; numeric fields are parsed with validation for file loading.

---


## How It Works

1. **Startup** — the main menu is displayed in a loop.
3. **Menu loop** — the user selects an option; the corresponding action is executed.
4. **Adding** — the user enters title, author, genre, cost, and the type-specific value (pages or minutes).
5. **Display** / Reports — iterate over the shared or type-specific lists and print the requested information.
6. **Save** / Load — serialize each book via toFileString() and reconstruct via readFromFile().
7. **Exit** — option 14 closes the loop and ends the program.

---

## Security Notes

This tool is intended for local use and educational purposes. It does not include user authentication or encryption for saved files — avoid storing sensitive data.
