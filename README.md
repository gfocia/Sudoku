# Sudoku Solver (Recursive Backtracking)

This project implements a classic **recursive backtracking algorithm** to solve 9x9 Sudoku puzzles. The program reads an unsolved Sudoku board from a `.txt` file and attempts to find a solution that satisfies all Sudoku rules.

---

## Overview

The main file, `Sudoku.java`, defines a `Sudoku` class that:

- Represents a 9x9 Sudoku board using a 2D integer array
- Tracks fixed values (from the original puzzle)
- Uses auxiliary structures to track which values already exist in each row, column, and 3x3 subgrid
- Implements a recursive backtracking method (`solveRB`) to fill in the puzzle
- Provides methods to read a puzzle configuration from a file, validate moves, print the board, and solve the puzzle

---

## File Format

The program expects a text file containing a Sudoku board formatted like this:

```
5 3 0 0 7 0 0 0 0
6 0 0 1 9 5 0 0 0
0 9 8 0 0 0 0 6 0
8 0 0 0 6 0 0 0 3
4 0 0 8 0 3 0 0 1
7 0 0 0 2 0 0 0 6
0 6 0 0 0 0 2 8 0
0 0 0 4 1 9 0 0 5
0 0 0 0 8 0 0 7 9
```

- Each line represents a row
- Values are space-separated
- `0` represents an empty cell

---

## How to Run

### 1. Compile the Java file:
```bash
javac Sudoku.java
```

### 2. Run the program:
```bash
java Sudoku
```

### 3. When prompted, enter the path to your puzzle file:
```
Enter the name of the puzzle file: puzzles/sample1.txt
```

The program will:
- Display the initial state of the puzzle
- Attempt to solve it using recursive backtracking
- Display the solved board if a solution is found
- Inform you if the puzzle has no valid solution

---

## Example Output

```
Enter the name of the puzzle file: puzzles/sample1.txt

Here is the initial puzzle:
-----------------------------
| 5 | 3 |   |   | 7 |   |   |   |   |
| 6 |   |   | 1 | 9 | 5 |   |   |   |
...

Here is the solution:
-----------------------------
| 5 | 3 | 4 | 6 | 7 | 8 | 9 | 1 | 2 |
| 6 | 7 | 2 | 1 | 9 | 5 | 3 | 4 | 8 |
...
```

---

## Notes

- The board uses helper matrices (`rowHasVal`, `colHasVal`, `subgridHasVal`) for fast validation
- Recursive backtracking avoids placing numbers that violate Sudoku constraints
- The board is displayed with horizontal and vertical separators for readability
