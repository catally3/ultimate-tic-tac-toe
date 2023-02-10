package UltimateTTT;

public class Box {
	private char placeHolder;
	
	// constructor for Box taking char to store and display
	Box(char mark) {
		this.placeHolder = mark;
	}
	
	// prints the char of this Box and a space
	void print() {
		System.out.print(placeHolder + " ");
	}
	
	// returns true if the char in this box is a number or * and hasn't been played on yet
	boolean isAvailable() {
		// box is available if it is a a number or a *
		return (Character.isDigit(placeHolder) || placeHolder == '*');
	}

	// returns the char stored in this Box
	public char getPlaceHolder() {
		return this.placeHolder;
	}

	// returns true if the char is successfully changed
	public boolean setPlaceHolder(char placeHolder) {
		// only set placeholder if box is available, else return false
		if(isAvailable()) {
			this.placeHolder = placeHolder;
			return true;
		}
		return false;
	}
}
