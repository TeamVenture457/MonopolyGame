package cs414.a4.monopoly.frontEnd;

import java.util.ArrayList;
import java.util.List;

public class ViewController {

	public ViewController() {
		// TODO Auto-generated constructor stub
	}

	public List<String> getPlayerNames(){
		List<String> playerNames = new ArrayList<String>();
		//get player names from UI
		//use this list until UI Implemented
		playerNames.add("Eli");
		playerNames.add("Ben");
		playerNames.add("AJ");
		playerNames.add("Jameson");
		return playerNames;
	}
	
	public List<String> getPlayerTokens(List<String> playerNames){
		List<String> tokens = new ArrayList<String>();
		tokens.add("car");
		tokens.add("shoe");
		tokens.add("hat");
		tokens.add("iron");
		return tokens;
	}
	
}
