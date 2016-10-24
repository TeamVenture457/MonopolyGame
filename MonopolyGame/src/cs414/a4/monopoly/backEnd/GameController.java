package cs414.a4.monopoly.backEnd;

import java.util.ArrayList;
import java.util.List;
import cs414.a4.monopoly.frontEnd.*;

public class GameController {

	private Game game;
	private Board board;
	private List<Player> players;
	private Bank bank;
	
	public GameController(List<String> playerNames) {
		game = new Game(playerNames);
		board = game.setup();
		players = game.getPlayers();
		bank = Bank.getInstance();
	}
	
	public void printPlayerInfo(){
		for(Player player : players){
			System.out.println("Player: " + player.getName() + "\nToken: " + 
		player.getToken() + "\nPosition: " + 
					board.getBoardSpaces()[player.getLocation()].getName() +
					"\nProperties: " + player.propertiesOwned.size() + "\n");
		}
	}
	
	public void setPlayerTokens(List<String> tokens){
		game.setPlayerTokensInOrder(tokens);
	}
	
	public List<String> getPlayerNames(){
		return game.getPlayerNamesInOrder();
	}
	
	public static void main(String [] args){
		//Create view and get names from view
		ViewController view = new ViewController();
		List<String> playerNames = view.getPlayerNames();
		
		GameController gameController = new GameController(playerNames);
		playerNames = gameController.getPlayerNames();
		
		//send player names back to view for players to choose tokens in order
		List<String> playerTokens = view.getPlayerTokens(playerNames);
		gameController.setPlayerTokens(playerTokens);
		
		gameController.printPlayerInfo();
		
	}

}
