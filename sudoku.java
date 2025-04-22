 import java.io.*; 
 import java.util.*;
 
 public class Sudoku {    
     private int[][] grid;
     
     private boolean[][] valIsFixed;
     
     /*
      * This 3-D array allows us to determine if a given subgrid 
      * (i.e., a given 3x3 region of the puzzle) already contains a given value.
      * We use 2 indices to identify a given subgrid:
      *
      *    (0,0)   (0,1)   (0,2)
      *
      *    (1,0)   (1,1)   (1,2)
      * 
      *    (2,0)   (2,1)   (2,2)
      * 
      * For example, subgridHasVal[0][2][5] will be true if the subgrid in the 
      * upper right-hand corner already has a 5 in it, and false otherwise.
      */
     private boolean[][][] subgridHasVal;
     private boolean[][] rowHasVal;
     private boolean[][] colHasVal;
     
     public Sudoku() {
         this.grid = new int[9][9];
         this.valIsFixed = new boolean[9][9];     
         
         this.subgridHasVal = new boolean[9][9][10];        
       
         this.rowHasVal = new boolean[9][10];
         this.colHasVal = new boolean[9][10];
     }
     
     public void placeVal(int val, int row, int col) {
         this.grid[row][col] = val;
         this.subgridHasVal[row/3][col/3][val] = true;
         
         this.rowHasVal[row][val] = true;
         this.colHasVal[col][val] = true;
     }
         
     public void removeVal(int val, int row, int col) {
         this.grid[row][col] = 0;
         this.subgridHasVal[row/3][col/3][val] = false;
         
         this.rowHasVal[row][val] = false;
         this.colHasVal[col][val] = false;
     }  
         
     public void readConfig(Scanner input) {
         for (int r = 0; r < 9; r++) {
             for (int c = 0; c < 9; c++) {
                 int val = input.nextInt();
                 this.placeVal(val, r, c);
                 if (val != 0) {
                     this.valIsFixed[r][c] = true;
                 }
             }
             input.nextLine();
         }
     }
                       
     public void printGrid() {
         for (int r = 0; r < 9; r++) {
             this.printRowSeparator();
             for (int c = 0; c < 9; c++) {
                 System.out.print("|");
                 if (this.grid[r][c] == 0)
                     System.out.print("   ");
                 else
                     System.out.print(" " + this.grid[r][c] + " ");
             }
             System.out.println("|");
         }
         this.printRowSeparator();
     }
         
     private static void printRowSeparator() {
         for (int i = 0; i < 9; i++)
             System.out.print("----");
         System.out.println("-");
     }
     
     public boolean isValid(int val, int row, int col) {
         return (!this.valIsFixed[row][col]
           && !this.rowHasVal[row][val]
           && !this.colHasVal[col][val]
           && !this.subgridHasVal[row/3][col/3][val]);       
     }
             
     private boolean solveRB(int n) {
         if (n >= 9*9)
             return true;
                 
         int row = n / 9;
         int col = n % 9;
                 
         if (this.valIsFixed[row][col]) {
             return this.solveRB(n + 1);
         }
                 
         for (int val = 1; val <= 9; val++) {
             if (this.isValid(val, row, col)) {
                 this.placeVal(val, row, col);
                 
                 if (this.solveRB(n + 1)) {
                     return true;
                 }
                                 
                 this.removeVal(val, row, col);
             }
         }
                 
         return false;
     } 

     public boolean solve() { 
         boolean foundSol = this.solveRB(0);
         return foundSol;
     }
     
     public static void main(String[] args) {
         Scanner scan = new Scanner(System.in);
         Sudoku puzzle = new Sudoku();
         
         System.out.print("Enter the name of the puzzle file: ");
         String filename = scan.nextLine();
         
         try {
             Scanner input = new Scanner(new File(filename));
             puzzle.readConfig(input);
         } catch (IOException e) {
             System.out.println("error accessing file " + filename);
             System.out.println(e);
             System.exit(1);
         }
         
         System.out.println();
         System.out.println("Here is the initial puzzle: ");
         puzzle.printGrid();
         System.out.println();
         
         if (puzzle.solve()) {
             System.out.println("Here is the solution: ");
         } else {
             System.out.println("No solution could be found.");
             System.out.println("Here is the current state of the puzzle:");
         }
         puzzle.printGrid();  
     }    
 }