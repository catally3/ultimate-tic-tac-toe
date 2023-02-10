package UltimateTTT;

import java.util.Scanner;

public class TTTGame {
	private Board board;
	private Player[] players;
	private char[] marks = {'X', 'O'};
	private int row;
	private int col;
	private int currentPlayer = 0;
	private int scoreToWin = 3;
	private Scanner input = new Scanner(System.in);
	
	// default constructor
	public TTTGame() {
		this(3,3);
	}
	
	// constructor
	public TTTGame(int row, int col) {
		this.col = col;
		this.row = row;
		setPlayer();
		setBoard();
		start();
	}
	
	// main game loop
	private void start() {
		int boardNum;
		int index;
		int previousBoard = -1;
		
		System.out.println("Game started...");
		board.print();
		
		do {
			switchPlayer();
			System.out.println("Current Player is : " + players[currentPlayer].getMark());
			
			// if previous board is full or it is first turn, get board num from player
			if(previousBoard < 0 || board.isFull(previousBoard)) {
				boardNum = getBoardNum();
			}
			else 
				boardNum = previousBoard;
			
			System.out.println("Selected Board : " + boardNum);
			
			// get box index on board from player
			index = getBoardIndex(boardNum);
			System.out.println("Selected Square : " + index);
			
			// set previous board to box index used
			previousBoard = index;
			
		} while(!gameOver());
		
		System.out.println("Exiting game");
	}
	
	// returns a valid board number from current player
	private int getBoardNum() {
		boolean inputIsValid = false;
		String inputString;
		int boardNum = -1;
		// if current player is human get input from player input
		if(players[currentPlayer].isHuman()) {
			while(!inputIsValid) {
				System.out.println("Please select a valid board:");
				inputString = input.next();
				if (Character.isDigit(inputString.charAt(0))) {
					boardNum = Character.digit(inputString.charAt(0), 10);
					// if input is valid and board is not full return board num
					if (boardNum < row*col && boardNum >= 0)	
						inputIsValid = !board.isFull(boardNum);
				}
				else
					inputIsValid = false;
				if(!inputIsValid)
					System.out.println("Invalid input");
			}
		}
		// if current player is not human get input from Math.random()
		else {
			System.out.println("Please select a valid board:");
			while(!inputIsValid) {
				boardNum = (int) (Math.random()*(row*col));
				inputIsValid = !board.isFull(boardNum);
				}
		}
		return boardNum;
	}
	
	// returns a valid board index on the given boardNum from current player
	private int getBoardIndex(int boardNum) {
		boolean inputIsValid = false;
		String inputString;
		int index = -1;
		// if current player is human get input from player input
		if(players[currentPlayer].isHuman()) {
			while(!inputIsValid) {
				System.out.println("Please select a valid square on the selected board:");
				inputString = input.next();
				if (Character.isDigit(inputString.charAt(0))) {
					index = Character.digit(inputString.charAt(0), 10);
					// if input is valid and move can be made make move and return box index
					if (index < row*col && index >= 0)
						inputIsValid = board.makeMove(players[currentPlayer].getMark(), boardNum, index);
				}
				else
					inputIsValid = false;
				if(!inputIsValid)
					System.out.println("Invalid input");
			}
		}
		// if current player is not human get input from Math.random()
		else {
			System.out.println("Please select a valid square on the selected board:");
			while(!inputIsValid) {
				index = (int) (Math.random()*(row*col));
				inputIsValid = board.makeMove(players[currentPlayer].getMark(), boardNum, index);
				}
		}
		return index;
	}
	
	// returns if game is over by checking for winners and if board is full
	private boolean gameOver() {
		if (hasWinner()) {
			System.out.println("Player " + players[currentPlayer].getMark() + " wins the game!");
			return true;
		}
		else if (board.isFull()) {
			System.out.println("It's a tie!");
			return true;
		}
		return false;
	}
	
	
	// returns if the game has a winner and prints winners of subBoards
	private boolean hasWinner() {
		// check rows, cols, and diags of subBoards
		for (int i = 0; i < row; i++) {
			if(checkRow(i)) 
				return true;
		}
		
		for (int i = 0; i < col; i++) {
			if(checkCol(i))
				return true;
		}
		
		if (checkDiagRL())
			return true;
		if (checkDiagLR())
			return true;
		
		// print board and winners for subBoards
		board.print();
		for(int i = 0; i < row*col; i++) {
			if(board.getWinner(i) == players[0].getMark() || board.getWinner(i) == players[1].getMark())
				System.out.println("The Board "+ i +" winner is " + board.getWinner(i));
		}
		System.out.println("");
		
		return false;
	}
	
	// checks for winners in rows of Board
	private boolean checkRow(int row) {
		int counter = 0;
		for (int i = 0; i < col; i++) {
			if(board.hasWinner((i*3)+row, players[currentPlayer].getMark()))
				counter++;
			if (counter == scoreToWin)
				return true;
		}
		return false;
	}
	
	// checks for winners in cols of Board
	private boolean checkCol(int col) {
		int counter = 0;
		for (int i = 0; i < row; i++) {
			if(board.hasWinner(i+(col*3), players[currentPlayer].getMark())) 
				counter++;
			if (counter == scoreToWin)
				return true;
		}	
		return false;
	}
	
	// checks for winners in right to left diag of Board
	private boolean checkDiagRL() {
		int counter = 0;
		for (int i = 0; i < col; i++) {
			if(board.hasWinner((i+1)*(col-1), players[currentPlayer].getMark()))
				counter++;
		}
		if (counter == scoreToWin)
			return true;
		return false;
	}
	
	// checks for winners in left to right diag of Board
	private boolean checkDiagLR() {
		int counter = 0;
		for (int i = 0; i < (row*col); i+=4) {
			if (board.hasWinner(i, players[currentPlayer].getMark()))
				counter++;
		}
		
		if (counter == scoreToWin)
			return true;
		return false;
	}
	
	// switches the current player
	private void switchPlayer() {
		if (currentPlayer < 1)
			currentPlayer += 1;
		else
			currentPlayer = 0;
	}
	
	// creates the Board
	private void setBoard() {
		this.board = new Board(row, col);
	}

	// initializes the Player array with 2 Players
	public void setPlayer() {
		players = new Player[2];
		boolean inputValid = false;
		String inputString;
		int playMode = 0;
		// getting play mode
		while(!inputValid) {
			System.out.println("Please enter number of AI players, 0, 1, or 2:");
			inputString = input.next();
			if (Character.isDigit(inputString.charAt(0))) {
				playMode = Character.digit(inputString.charAt(0), 10);
				if (playMode >= 0 && playMode <= 2)
					inputValid = true;
			}
			if (!inputValid)
				System.out.println("Invalid input");
		}
		
		if (playMode == 0) {
			players[0] = new HumanPlayer(marks[0]);
			players[1] = new HumanPlayer(marks[1]);
		}
		else if (playMode == 1) {
			players[0] = new AIPlayer(marks[0]);
			players[1] = new HumanPlayer(marks[1]);
		}
		else {
			players[0] = new AIPlayer(marks[0]);
			players[1] = new AIPlayer(marks[1]);
		}
	}
}
