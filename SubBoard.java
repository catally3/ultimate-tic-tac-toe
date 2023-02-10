package UltimateTTT;

public class SubBoard {
	private Box[] boxes;
	private int name;
	private int row;
	private int col;
	private char winner = 'T';
	
	// constructor takes index of SubBoard within array to use as name
	SubBoard(int name){
		this(3,3, name);
	}
	
	// constructor called by primary constructor
	SubBoard(int row, int col, int name){
		this.name = name;
		this.setSize(row, col);
	}

	// sets size of Box array and calls initialize
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
	
	// initializes Box array to have each Box contain the char for the index that it is at
	private void initialize() {
		boxes = new Box[this.row * this.col];
		// for each box, set mark to character form of index number
		for(int i = 0; i < boxes.length; i++) {
			Box b = new Box(Character.forDigit(i, 10));
			boxes[i] = b;
		}
	}
	
	// prints each box in the SubBoard
	public void print() {
		// prints each box row by row
		for (int i = 0; i < this.boxes.length; i++) {
			this.boxes[i].print();
			if((i+1)%col == 0)
				System.out.println(); 
		}
	}
	
	// prints the given row for the SubBoard
	public void printRow(int r) {
		// prints each box in the given row
		for (int i = (r*this.col); i < ((r*this.col) + this.col); i++) {
			this.boxes[i].print();
		}
	}

	// returns the name index of the SubBoard
	public int getName() {
		return name;
	}

	// returns the char stored in the given Box index
	public char getMark(int index) {
		return boxes[index].getPlaceHolder();
	}

	// returns true if move is successfully made for given player on given Box
	public boolean makeMove(char player, int index) {
		return boxes[index].setPlaceHolder(player);
	}
	
	// returns size of Box array
	public int getSize() {
		return row*col;
	}

	// returns true if every Box is not available
	public boolean isFull() {
		int counter = 0;
		// returns false if no boxes are available
		for (int i = 0; i < boxes.length; i++) {
			if (!boxes[i].isAvailable())
				counter++;
		}
		if (counter == boxes.length)
			return true;
		return false;
	}

	// returns the winner player in this SubBoard
	public char getWinner() {
		return winner;
	}

	// sets the winner player in this SubBoard
	public void setWinner(char winner) {
		this.winner = winner;
	}
	
}
