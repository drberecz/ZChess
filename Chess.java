/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brhchess;

public class Chess {
    
    public final static char SPACE = '\u0020';
    static boolean castlingReq = false;
    
    
  public static final char WHITE_KING = '\u2654';
  public static final char BLACK_KING = '\u265A';
  public static final char WHITE_QUEEN = '\u2655';
  public static final char BLACK_QUEEN = '\u265B';

  public static final char WHITE_ROOK = '\u2656';
  public static final char BLACK_ROOK = '\u265C';
  public static final char WHITE_KNIGHT = '\u2658';
  public static final char BLACK_KNIGHT = '\u265E';
  public static final char WHITE_BISHOP = '\u2657';
  public static final char BLACK_BISHOP = '\u265D';

  public static final char WHITE_PAWN = '\u2659';
  public static final char BLACK_PAWN = '\u265F';

  public static char[][] board = new char[9][9];

  public static final char[] WHITE_FIGURES = {WHITE_PAWN, WHITE_ROOK, WHITE_KNIGHT, WHITE_BISHOP, WHITE_QUEEN, WHITE_KING};
  public static final char[] BLACK_FIGURES = {BLACK_PAWN, BLACK_ROOK, BLACK_KNIGHT, BLACK_BISHOP, BLACK_QUEEN, BLACK_KING};

  public static char[] wfq = {' ', WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN,};
  public static char[] bfq = {' ', BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN,};

  public static char[] wrq = {' ', WHITE_ROOK, WHITE_KNIGHT, WHITE_BISHOP, WHITE_QUEEN, WHITE_KING, WHITE_BISHOP, WHITE_KNIGHT, WHITE_ROOK,};
  public static char[] brq = {' ', BLACK_ROOK, BLACK_KNIGHT, BLACK_BISHOP, BLACK_QUEEN, BLACK_KING, BLACK_BISHOP, BLACK_KNIGHT, BLACK_ROOK,};

  public static void init() {
    
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        board[i][j] = SPACE;
      }
    }
    board[1] = wrq;
    board[2] = wfq;
    board[7] = bfq;
    board[8] = brq;
  }


  public static boolean move(int player_flag, int from_row, int from_column, int to_row, int to_column) {
    char figure = board[from_row][from_column];
    char target = board[to_row][to_column];

    if ( !isValidFigure(player_flag, figure)){
           System.out.println("Azon a mezőn nincs saját figurád ");
           return false;
    }
    if ( isValidFigure(player_flag, target) | target==WHITE_KING | target==BLACK_KING){
        System.out.println("Ezt a bábut (" + target + ") nem ütheted");
      return false;
    }
    
    
    boolean isMoveValid = checkRule(figure, from_row, from_column, to_row, to_column);
        
        if (castlingReq){
            boolean isCastValid =castling(player_flag, from_row, from_column, to_row, to_column);
            castlingReq = false;
            
            if ( isCastValid ){
                int vectorX = to_row - from_row;
                board[from_row][from_column] = ' ';
                board[to_row][to_column+vectorX] = figure;                
            }
            
            String msg = (isCastValid) ? "Sancolas ok." : "nem rosalhatsz";
            System.out.println(msg);
            return isCastValid;
        }    
    
    if (isMoveValid){   
        board[from_row][from_column] = ' ';
        board[to_row][to_column] = figure;
        return true;
    }
    return false;
  }
  
  
  
  
  //Ha valid akkor a játékos érvényes bábut választott.
  public static boolean isValidFigure(int player_flag, char figure) {
    if (figure == ' ') {
      return false;
    }
    int numberOfFigure = 0;
    //Ha fehér figura
    if (player_flag == 0) {
        //Végig szaladunka fehér figurák tömbön. Addig amig a paraméterben kapott figura meg nem egyezik az egyik tömbben figurával.
        //Ha megvan a figura akkor annak számát megörizzük és kilépünk a ciklusból.
        //Igy biztos kisebb számunk lesz mint a figurák tömb hossza.
      while (numberOfFigure < WHITE_FIGURES.length && figure != WHITE_FIGURES[numberOfFigure]) {
        numberOfFigure++;
      }
    //Egyébként fekete figura
    } else {
      while (numberOfFigure < BLACK_FIGURES.length && figure != BLACK_FIGURES[numberOfFigure]) {
        numberOfFigure++;
      }
    }
    return numberOfFigure < BLACK_FIGURES.length ? true : false;
  }

  static boolean checkRule(char figure, int from_row, int from_column, int to_row, int to_column) {

      int vectorY = to_row-from_row;
      int vectorX = to_column-from_column;
      
      if ( vectorY ==0 & vectorX ==0) {     
           System.out.println("Debug: Hiba, helyben maradna a bábu");
           return false; 
      }

    
      
    if (figure == WHITE_KING || figure == BLACK_KING){
      return checkKingRule( vectorY, vectorX );
    }      
    if (figure == WHITE_PAWN){
      return true;
    }
    if (figure == BLACK_PAWN){
      return true;
    }
    if (figure == WHITE_KNIGHT || figure == BLACK_KNIGHT){
      return checkKnightRule(vectorY, vectorX);
    }
    if (figure == BLACK_BISHOP || figure == WHITE_BISHOP){
      return checkBishopRule(from_row, from_column, to_row, to_column);
    }
    if (figure == BLACK_ROOK || figure == WHITE_ROOK){
      return checkRookRule(vectorY, vectorX, from_row, from_column, to_row, to_column);
    }
    if (figure == BLACK_QUEEN || figure == WHITE_QUEEN){

        boolean isValidQueenMove = ( vectorY==0 | vectorX==0 ) ?
                checkRookRule(vectorY, vectorX, from_row, from_column, to_row, to_column) :
                checkBishopRule(from_row, from_column, to_row, to_column);
      
        return isValidQueenMove;
    }    
    
    return true;
  }    


  
  
  private static boolean checkRookRule(int vectorY,int vectorX,int y1, int x1, int y2, int x2) {
  
return true;
  }

  
  
  
  
  
  

    static boolean checkKnightRule(int vectorY,int vectorX){
 


    return true;
    }
  
  
  
  
  
  
  

    //Az még nincs megvalósitva, hogy a futó mit csinál ha van valaki a lépése útjában.
    private static boolean checkBishopRule(int from_row, int from_column, int to_row, int to_column) {

      
        //Irányvektor v(1, 1)
        int v1 = 1, v2 = 1;
        //Irányvektor z(1, -1)
        int z1 = 1, z2 = -1;
        // Pont p(x0, y0) azaz (from_row, from_column) 
        int x0 = from_row, y0 = from_column;
        // pont p1(x, y) azaz (to_row, to_column)
        int x = to_row, y = to_column;
        //Egyenes egyenlete
        // v2 x - v1 y = v2x0 - v1y0 a jobb oldal legyen c = v2x0 - v1y0
        int c1 = (v2 * x0) - (v1 * y0); //A v irányvektorra
        System.out.println("c1: "+c1);
        System.out.println((v2 * x) - (v1 * y));
        //Rajta van-e a p1(to_row, to_column) 
        if ( (c1 == (v2 * x) - (v1 * y))){
          return true;
        }
        int c2 = (z2 * x0) - (z1 * y0); //A z irányvektorra
        System.out.println("c2: "+c2);
        System.out.println((z2 * x) - (z1 * y));
        //Rajta van-e a p1(to_row, to_column)
        if ( (c2 == (z2 * x) - (z1 * y)) ){
          return true;
        }
   
      
      return false;
    }
    
    
    
    
    private static boolean checkKingRule(int vectorY,int vectorX) {

        if ( Math.abs(vectorX) == 2 ) {castlingReq = true; return true; }
        if ( Math.abs(vectorY)>1 | Math.abs(vectorX)>1 ) return false;
        
        return true;
    }

    
    
    static boolean castling (int player_flag,int  from_row,int  from_column,int  to_row,int  to_column){
        
//2. Rosálás lépés: azaz a király kettő mezőt lép, de azt csak ugyanabban a sorban teheti.
        
        if(((to_row-from_row)==0) && ((int)Math.abs(to_column-from_column)==2)){
            //Ha világos lépés van
            if(player_flag == 0){
                //Ha a király még nem lépett (From mező: E1)
                if(board[from_row][from_column] == WHITE_KING){
                    //-Ha hosszú rosálás van (To mező: C1)
                    if(to_column == 3){
                        //Az A1 mezőn levő bástya még nem lépett. (Az A1 mezőn világos bástya van.)
                        if(board[1][1] == WHITE_ROOK){
                            //A király (E1) és a bástya (A1) közötti terület üres.
                            if(board[1][2]==SPACE && board[1][3]==SPACE && board[1][4]==SPACE)
                                //A király (E1) és a bástya (A1) közötti üres mezők nem támadottak-e (minden mezőre checkMate() függvény)
                                    return true;
                        }    
                    }
                    //-Ha rövid rosálás van (To mező: G1)
                    if(to_column == 7){
                        //A H1 mezőn levő bástya még nem lépett. (Az H1 mezőn világos bástya van.)
                        if(board[1][8] == WHITE_ROOK){
                            //A király (E1) és a bástya (H1) közötti terület üres.
                            if(board[1][6]==SPACE && board[1][7]==SPACE){
                                //A király (E1) és a bástya (H1) közötti üres mezők nem támadottak-e (minden mezőre checkMate() függvény)  
                                    return true;
                            }
                        }
                    }
                }                    
            }
            //Ha sötét lépés vaan
            if(player_flag == 1){
                //Ha a király még nem lépett (From mező: E8)
                if(board[from_row][from_column] == BLACK_KING){
                    //-Ha hosszú rosálás van (To mező: C8)
                    if(to_column == 3){
                        //Az A8 mezőn levő bástya még nem lépett. (Az A8 mezőn sötét bástya van.)
                        if(board[8][1] == BLACK_ROOK){
                            //A király (E8) és a bástya (A8) közötti terület üres.
                            if(board[8][2]==SPACE && board[8][3]==SPACE && board[8][4]==SPACE){
                                //A király (E8) és a bástya (A8) közötti üres mezők nem támadottak-e (minden mezőre checkMate() függvény)
                                    return true;
                            }
                        }
                    }
                    //-Ha rövid rosálás van (To mező: G8)
                    if(to_column == 7){
                        //A H8 mezőn levő bástya még nem lépett. (Az H8 mezőn sötét bástya van.)
                        if(board[8][8] == BLACK_ROOK){
                            //A király (E8) és a bástya (H8) közötti terület üres.
                            if(board[8][6]==SPACE && board[8][7]==SPACE){
                                //A király (E8) és a bástya (H8) közötti üres mezők nem támadottak-e (minden mezőre checkMate() függvény)
                                    return true;
                            }
                        }
                    }
                }                 
            }
        }
        
     return false;   
    }
    
    
    
    
    
    
static boolean isRouteFree (int y1, int x1, int y2, int x2, int signY, int signX){
    
      while ( y1!=y2 | x1!=x2  ){
          y1+= signY; 
          x1+= signX;
          char piece = board[y1][x1];
          if (  piece!= ' ' &  (y1!=y2 | x1!=x2)) {
              System.out.println("debug: Útban van egy bábu: "+ piece);
              return false;
          }
      } 
  return true;
}

    
    
    
    
}
