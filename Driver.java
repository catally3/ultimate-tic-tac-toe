package UltimateTTT;

/*
name:	Alexandra Ontiveros
section number: CS2336.001

Analysis: Must have ultimate tic tac toe game with capability for PvP, PvE, and EvE.
Must have grid of 9 3 by 3 tic tac toe boards that can each be won and played on.
The box previously played on must be the board that the next player uses, unless that board is full.
Must have input validation and request new input if move is invalid.
A player wins the game once they win 3 of the subBoards in a line.

Design: 
Will have classes TTTGame for main game loop and to contain other game elements, Board will 
act as game board and contain an array of SubBoard which will each contain an array of Box. Player 
will be an abstract parent class to HumanPlayer and AIPlayer. Player will store its mark and whether it is human.
Box will store a char and be able to print that char. SubBoard will store its index in the SubBoard array,
will be able to print a given row, return if a move is valid, and will store the winner of that SubBoard. 
Board will be able to check each SubBoard for winner, call the set SubBoard winner, print the SubBoards 
row by row so that the Board displays correctly, and call the SubBoard make move. TTTGame will have main game
loop that prints the board, switches the player, gets input from the current player, checks if input is valid 
and move is available, and then checks Board for if the current player has won and repeats this until there is
a winner or Board is full.

Test: 
Tested by running the game repeatedly with HumanPlayer vs AIPlayer and AIPlayer vs AIPlayer.
The game can ignore invalid input and ask for input again and can register horizontal, vertical, and
both types of diagonal wins in the SubBoards and in the Board and can register ties.

 */

public class Driver {
	public static void main(String[] args) {
		TTTGame game = new TTTGame();
	}

}
