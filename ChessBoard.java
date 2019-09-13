import java.util.*;

public class ChessBoard{
    
    private int[][] board;
    private static int  BOARD_SIZE = 8; //constant
    
    public ChessBoard(){
        board = shuffleBoard();
    }
    
    public ChessBoard(ChessBoard duplicate) {
    	int[][] copyBoard = new int[BOARD_SIZE][BOARD_SIZE];
    	for(int x = 0; x < BOARD_SIZE; x++) {
    		for(int y = 0; y < BOARD_SIZE; y++) {
    			copyBoard[x][y] = duplicate.getBoard()[x][y];
    		}
    	}
    	this.board = copyBoard;
    }
    public int[][] shuffleBoard(){
        int[][] initBoard = new int[BOARD_SIZE][BOARD_SIZE];
        Random randInt = new Random();
        int randNum;
        for(int x = 0; x < BOARD_SIZE; x++){
            
            //int randomNum = (int) (Math.random() * 8);
            randNum = randInt.nextInt(BOARD_SIZE);
            
            for(int y = 0; y < BOARD_SIZE; y++){
                initBoard[x][y] = 0;
                if(y == randNum){
                    initBoard[x][y] = 1;
                }
                
            }
        }
        
        return initBoard;
    }
    //getter methods
    
    public int[][] getBoard(){
    	return board;
    }
    
    public int getBoardSize() {
    	return BOARD_SIZE;
    }
    
    //toString method
    public String toString(){
        String array = "";
        for(int x = 0; x < BOARD_SIZE; x++){
            for(int y = 0; y < BOARD_SIZE; y++){
                array+= board[y][x];
                if(y < BOARD_SIZE -1){
                    array+=", ";
                }
                //array = array.concat(board[x][y] + ",");
            }
            if(x < BOARD_SIZE-1){
                array+="\n";
            }
            //array = array.concat("\n");
        }
        return array;
    }
}

