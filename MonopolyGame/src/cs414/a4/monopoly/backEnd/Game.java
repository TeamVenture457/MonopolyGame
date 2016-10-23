package cs414.a4.monopoly.backEnd;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Game {
	
	private List<Player> players;

	public Game(List<String> playerNames) {
		players = new ArrayList<Player>();
		for(String name : playerNames){
			players.add(new Player(name));
		}
		Collections.shuffle(players);
	}
	
	public Board setup(){
		Board board = new Board(players);
		return board;
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
		List<String> playerNames = new ArrayList<String>();
		playerNames.add("Joe");
		playerNames.add("Alex");
		playerNames.add("Sarah");
		playerNames.add("Ralph");
		System.out.println(playerNames.toString());
		Game game = new Game(playerNames);
		playerNames = game.getPlayerNamesInOrder();
		System.out.println(playerNames.toString());
		List<String> playerTokens = new ArrayList<String>();
		playerTokens.add("shoe");
		playerTokens.add("car");
		playerTokens.add("iron");
		playerTokens.add("hat");
		System.out.println(playerTokens.toString());
		game.setPlayerTokensInOrder(playerTokens);
		for(Player player : game.players){
			System.out.println(player.getName() + " chose " + player.getToken() + " token.");
		}
		Board myBoard = game.setup();
	}

}
