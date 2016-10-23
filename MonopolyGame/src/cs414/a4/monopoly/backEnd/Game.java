package cs414.a4.monopoly.backEnd;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Game {
	
	private List<Player> players;
	private Board board;
	List<String> tokens;

	public Game(String [] playerNames) {
		players = new ArrayList<Player>();
		for(String name : playerNames){
			players.add(new Player(name));
		}
		Collections.shuffle(players);
	}
	
	//Gives an arraylist of player names in turn order for view to use
	public List<String> getPlayerNamesInOrder(){
		List<String> playerNames = new ArrayList<String>();
		for(Player player : players){
			playerNames.add(player.getName());
		}
		return playerNames;
	}
	
	//takes an arraylist of tokens in player turn order from view in order to set player tokens
	public void setPlayerTokensInOrder(List<String> playerTokens){
		for(String token : playerTokens){
			int index = playerTokens.indexOf(token);
			players.get(index).setToken(token);
		}
	}
	
	public static void main (String [] args){
		String [] playerNames = {"Joe", "Alex", "Sarah", "Ralph"};
		Game game = new Game(playerNames);
	}

}
