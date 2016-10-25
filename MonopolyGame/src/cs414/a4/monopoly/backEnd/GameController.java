package cs414.a4.monopoly.backEnd;

import java.util.ArrayList;
import java.util.List;
import cs414.a4.monopoly.frontEnd.*;

public class GameController {

	private ViewController view;
	private Game game;
	private Board board;
	private List<Player> players;
	private Bank bank;
	
	public GameController() {
		view = new ViewController();
		game = new Game(view.getPlayerNames());
		board = game.setup();
		players = game.getPlayers();
		setPlayerTokens(view.getPlayerTokens(getPlayerNames()));
		bank = Bank.getInstance();
	}
	
	public void playMonopolyGame(){
		Player currentPlayer = players.get(0);
		while(players.size() > 1){
			currentPlayer = takeTurn(currentPlayer);
		}
	}
	
	private Player takeTurn(Player currentPlayer) {
		
		return null;
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
		
		GameController gameController = new GameController();
		
		gameController.printPlayerInfo();
		
	}

}
