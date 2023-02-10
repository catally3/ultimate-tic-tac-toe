package UltimateTTT;

public class HumanPlayer extends Player{

	// calls Player constructor with given mark and true for isHuman
	HumanPlayer(char mark) {
		super(true, mark);
	}
	
	// overrides isHuman from Player to always return true for HumanPlayer
	public boolean isHuman() {
		return true;
	}

}
