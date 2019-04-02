/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brhchess;

/**
 *
 * @author mantis
 */
public class ChessBoardPrinter {
    
    public final static char lightShade = '\u2591';
    public final static char fullBlock = '\u2588';
  
  public static void printABC(){
    System.out.println("    A    B    C    D   E    F   G    H");
  }
  
  public static void printBoard(){
    System.out.println("  ---------------------------------------");
    for (int row = 1; row <= 8; row++) {
      System.out.print(row+" ");
      for (int column = 1; column <= 8; column++) {
        
        System.out.print(Chess.board[row][column] == ' ' ?  "| " + square(row+column) + " " :
                                     "| " + Chess.board[row][column] + " ");
        //\u2B1B, \u2588, \u2591
      }
      System.out.print("|");
      System.out.println();
      System.out.println("  ---------------------------------------");
    }
    printABC();
  }

  public static char square(int i) {
    return i%2!=0? lightShade : fullBlock; 
  }
  
}
