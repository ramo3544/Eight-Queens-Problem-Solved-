import java.util.*;

public class Agent { 
	private int GOAL_STATE = 0; //constant for the goal state of the agent
	private final int BOARD_SIZE; //constant for the size of the ChessBoard object
	private ChessBoard primeBoard; // the board that represent the state of the environment
	private int numBetterBoards;
	private int stateChanges = 0;
	private int numRestarts = 0; //number of restarts from the hill climb method
	
	public Agent(ChessBoard board) {
		this.BOARD_SIZE = board.getBoardSize();
		
		// takes in the original board created by the ChessBoard object
		primeBoard = checkBoard(board);
		if(!checkGoalState(primeBoard)) {
			AgentCalculation();
		}
	}
	public ChessBoard checkBoard(ChessBoard board) {
		return new ChessBoard(board);
	}
	
	//getter methods
	public ChessBoard getPrimeBoard() {
		return primeBoard;
	}
	
	public int getHeuristic(ChessBoard testBoard) {
		int h = 0;
		h+= getRowsHeuristic(testBoard);
		h+= getDiagonalUpHeuristic(testBoard);
		h+= getDiagonalDownHeuristic(testBoard);
		return h;
	}
	
	public int getRowsHeuristic(ChessBoard board) {
		int h_value = 0;
		int row;
		
		for(int y = 0; y < BOARD_SIZE; y++) {
			row = 0; 
			
			for(int x = 0; x < BOARD_SIZE; x++) {
				row+=board.getBoard()[x][y];
			}
			if(row > 1) {
				h_value+= row-1;
			}
		}
		return h_value;
	}
	public int getDiagonalDownHeuristic(ChessBoard board) {
        int h_value = 0;
        int x = BOARD_SIZE - 1;
        int y;
        int diagonal;
	        // first half
	        while(x > 0) {
	            diagonal = 0;
	            y = BOARD_SIZE-1;
	            int temp = x;
	            while(temp>=0){
	                diagonal += board.getBoard()[y][temp];
	                temp--;
	                y--;
	            }
	 
	            if (diagonal > 1) {h_value += diagonal - 1;}
	            x--;
	        }
	 
	        // second half
	        y = BOARD_SIZE - 1;
	        while(y > 0) {
	            diagonal = 0;
	            x = BOARD_SIZE-1;
	            int columnTemp = y;
	            while(columnTemp >= 0){
	                diagonal+= board.getBoard()[columnTemp][x];
	                columnTemp--;
	                x--;
	            }
	 
	            if (diagonal > 1) {h_value += diagonal - 1;}
	            y--;
	        }
	 
	        return h_value;
    }
	public int getDiagonalUpHeuristic(ChessBoard board) {
        int h_value = 0;
        int x = 0;
        int y;
        int diagonal;
        
        // first half
        while(x < BOARD_SIZE) {
            diagonal = 0;
            y = 0;
            int temp = x;
 
            while(temp >= 0) {
                diagonal += board.getBoard()[temp][y];
                temp--;
                y++;
            }
 
            if (diagonal > 1) {h_value += diagonal - 1;}
            x++;
        }
        // second half
        y = 1;
        while(y < BOARD_SIZE) {
            diagonal = 0;
            x = BOARD_SIZE - 1;
            int temp = y;
 
            while(temp <= BOARD_SIZE - 1) {
                diagonal += board.getBoard()[x][temp];
                temp++;
                x--;
            }
 
            if (diagonal > 1) {h_value += diagonal - 1;}
            y++;
        }
 
        return h_value;
	}
	
	public boolean checkGoalState(ChessBoard board) {
		return GOAL_STATE == getHeuristic(board);
	}
	public void AgentCalculation() {
		while(!checkGoalState(primeBoard)) {
			System.out.println("Heuristic value :\t" + getHeuristic(primeBoard));
			System.out.println("Current State");
			System.out.println(primeBoard);
			ChessBoard bestBoard = findBestState(primeBoard);
			System.out.println("Neighbors Found with Lower Heuristic:\t" + numBetterBoards);
			
			if(numBetterBoards > 0) {
				System.out.println("Setting new current state...\n\n");
				primeBoard = new ChessBoard(bestBoard);
			}
			else {
				System.out.println("RESTART");
				primeBoard = new ChessBoard();
				numRestarts++;
			}
			stateChanges++;
		}
		System.out.println("Current State");
		System.out.println(primeBoard);
		System.out.println("SOLUTION FOUND");
		System.out.println("State Changes:\t" + stateChanges);
		System.out.println("Restarts:\t" + numRestarts);
	}
	public ChessBoard findBestState(ChessBoard board) {
		int bestH = getHeuristic(board);
		numBetterBoards = 0;
		ChessBoard initialBoard = new ChessBoard(board);//the original board that is used to compare to better boards
		ChessBoard bestBoard = new ChessBoard(board); //the best board with the lowest heuristic value
		ChessBoard testBoard;
		
		for(int x = 0; x < BOARD_SIZE; x++) {
			testBoard = new ChessBoard(initialBoard);
			
			for(int y = 0; y < BOARD_SIZE; y++) {
				testBoard.getBoard()[x][y] = 0;
			}
			for(int c = 0; c < BOARD_SIZE; c++) {
				testBoard.getBoard()[x][c] = 1;
				int tempH = getHeuristic(testBoard);
				if(tempH < getHeuristic(initialBoard)) {
					numBetterBoards++;
					if(tempH < bestH) {
						bestBoard = new ChessBoard(testBoard);
					}
				}
				
				testBoard.getBoard()[x][c] = 0;
			}
		}
		return bestBoard;
	}
	
}
