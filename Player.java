package UltimateTTT;

public abstract class Player {
	private boolean human;
	private char mark;
	
	// constructor requires the Player's mark and if human
	Player(boolean isHuman, char mark) {
		this.setHuman(isHuman);
		this.setMark(mark);
	}
	
	// returns if Player is human
	public boolean isHuman() {
		return human;
	}

	// sets if Player is human
	private void setHuman(boolean isHuman) {
		this.human = isHuman;
	}

	// returns this Player's mark
	public char getMark() {
		return mark;
	}

	// sets this Player's mark
	public void setMark(char mark) {
		this.mark = mark;
	}
}
