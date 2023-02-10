package UltimateTTT;

public class AIPlayer extends Player{

	// calls Player constructor with given mark and false for isHuman
	AIPlayer(char mark) {
		super(false, mark);
	}
	
	// overrides isHuman from Player to always return false for AIPlayer
	public boolean isHuman() {
		return false;
	}
}
