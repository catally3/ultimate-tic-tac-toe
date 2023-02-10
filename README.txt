To run game:
	With HumanPlayer:
	enter number 1-9 to select SubBoard to play on when prompted
	enter number 1-9 to select Box to play on when prompted
	if input is invalid or selected Box or SubBoard is full user will be prompted again
	
	In General:
	after each player's turn the board will be printed and the program will check for wins
	if a win is found the SubBoard winner will be printed and the unused Boxes in that SubBoard will become *
	if a win of the whole Board is found the winner will be printed and the game will end
	the SubBoard that the next player uses is the number of the Box that the previous player used, 
		unless the previous SubBoard is full, in which case the next player will choose the SubBoard to play on
	play will continue with players switching until a win is found or all SubBoards are full
	if all SubBoards are full and there is no overall win, the game will be a tie
	
Design:
	TTTGame for main game loop and to contain other game elements, 
	Board acts as game board and contains an array of SubBoards
	SubBoards each contain an array of Boxs
	Player is an abstract parent class to HumanPlayer and AIPlayer
	Player stores its mark and whether it is human
	Box stores a char and is able to print that char 
	SubBoard stores its index in the SubBoard array, can print a given row, return if a move is valid,
		and stores the winner of that SubBoard 
	Board is able to check each SubBoard for winner, call the set SubBoard winner, print the SubBoards 
		row by row so that the Board displays correctly, and call the SubBoard make move 
	TTTGame has the main game loop that prints the board, switches the player, gets input from the current player, 
		checks if input is valid and move is available, and then checks Board for if the current player has won
		and repeats this until there is a winner or Board is full.

If I Had To Do This Project Again:
	I would start with a more clear plan if I had to do this project again. I think the code could be better 
	organized if I had started with a better idea of all the functionality that each class would need. 
	I feel like the printing of the SubBoard winners should ideally be done in the Board class but because 
	of the way that the TTTGame is calling the Board.hasWinner multiple times that would not work without
	additional rearranging of the code. However if I had started with the intention of printing the SubBoard
	winners from the Board class then I could have avoided calling Board.hasWinner multiple times and made the
	code better organized in general.
