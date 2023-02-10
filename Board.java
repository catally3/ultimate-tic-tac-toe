package UltimateTTT;

public class Board {
	private SubBoard[] boards;
	private int row;
	private int col;
	
	// default constructor
	Board(){
		this(3,3);
	}
	
	// constructor called by default constructor
	Board(int row, int col){
		this.setSize(row, col);
	}

	// sets size of Board and calls initialize()
	public void setSize(int row, int col) {
		if(row < 3 || col < 3) {
			System.out.println("min board size is 3");
		} 
		else {
			this.row = row;
			this.col = col;
			initialize();
		}
		
	}
	
	// sets up array of SubBoards and assigns them index values as names
	private void initialize() {
		boards = new SubBoard[this.row * this.col];
		
		// instantiating each SubBoard with board number as constructor argument
		for(int i = 0; i < boards.length; i++) {
			SubBoard b = new SubBoard(i);
			boards[i] = b;
		}
	}
	
	// prints the whole Board
	public void print() {
		// printing the boards row by row for each board
		for (int i = 0; i < this.col; i++) {
			for (int row = 0; row < this.col; row++) {
				for (int board = 0; board < this.col; board++) {
					System.out.print("Board " + (board+(i*this.col)) + "  ");
					this.boards[board+(i*this.col)].printRow(row);
					System.out.print("  ");
					if((board+1)%this.col == 0)
						System.out.println();
				}
			}
		}
	}

	// returns the mark of the given box index on the given board
	public char getMark(int board, int index) {
		return boards[board].getMark(index);
	}

	// returns true if move is successful for given player char on given box index of given board
	public boolean makeMove(char player, int board, int index) {
		return boards[board].makeMove(player, index);
	}
	
	// returns size of Board
	public int getSize() {
		return row*col;
	}

	// returns true if every SubBoard is full
	public boolean isFull() {
		int counter = 0;
		// if every subBoard is full returns true
		for (int i = 0; i < boards.length; i++) {
			if (boards[i].isFull())
				counter++;
		}
		if (counter == boards.length)
			return true;
		return false;
	}
	
	// returns true if the given SubBoard is full
	public boolean isFull(int board) {
		return boards[board].isFull();
	}
	
	// returns if given board has winner for given char
	public boolean hasWinner(int board, char mark) {
		boolean isWon = false;
		// return true if already won by this player
		if (boards[board].getWinner() == mark) 
			return true;
		
		// check each row, col, and both diagonals
		for (int i = 0; i < row; i++) {
			if(checkRow(board, i, mark)) 
				isWon = true;
		}
		
		for (int i = 0; i < col; i++) {
			if(checkCol(board, i, mark))
				isWon = true;
		}
		
		if (checkDiagRL(board, mark))
			isWon = true;
		
		if (checkDiagLR(board, mark))
			isWon = true;
		
		// if board is won set empty spaces to * and set winner to this player
		if(isWon) {
			for (int i = 0; i < boards.length; i++) {
				boards[board].makeMove('*', i);
			}
			boards[board].setWinner(mark);
		}
		
		return isWon;
	}
	
	// checks given row of given board for winner of given player
	private boolean checkRow(int board, int row, char mark) {
		int counter = 0;
		for (int i = 0; i < this.col; i++) {
			if(boards[board].getMark((i*3)+row) == mark)
				counter++;
			if (counter == this.row)
				return true;
		}
		return false;
	}
	
	// checks given col of given board for winner of given player
	private boolean checkCol(int board, int col, char mark) {
		int counter = 0;
		for (int i = 0; i < row; i++) {
			if(boards[board].getMark(i + (col*3)) == mark) 
				counter++;
			if (counter == this.col)
				return true;
		}	
		return false;
	}
	
	// checks right left diag of given board for winner of given player
	private boolean checkDiagRL(int board, char mark) {
		int counter = 0;
		for (int i = 0; i < col; i++) {
			if(boards[board].getMark((i+1)*(col-1)) == mark)
				counter++;
		}
		if (counter == this.row)
			return true;
		return false;
	}
	
	// checks left right diag of given board for winner of given player
	private boolean checkDiagLR(int board, char mark) {
		int counter = 0;
		for (int i = 0; i < boards.length; i+=4) {
			if (boards[board].getMark(i) == mark)
				counter++;
		}
		
		if (counter == this.row)
			return true;
		return false;
	}
	
	// returns the winner player of the given board or "T" if board has no winner
	public char getWinner(int board) {
		return boards[board].getWinner();
	}
	
}
