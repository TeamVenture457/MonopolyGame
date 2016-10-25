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
	private Dice dice;

	public GameController() {
		view = new ViewController();
		game = new Game(view.getPlayerNames());
		board = game.setup();
		players = game.getPlayers();
		setPlayerTokens(view.getPlayerTokens(getPlayerNames()));
		bank = Bank.getInstance();
		dice = new Dice();
	}

	public void playMonopolyGame() {
		Player currentPlayer = players.get(0);
		Player nextPlayer;
		while (players.size() > 1) {
			nextPlayer = movePlayer(currentPlayer);
			finishTurn(currentPlayer);
			currentPlayer = nextPlayer;
		}
	}

	private void finishTurn(Player currentPlayer) {
		// TODO Auto-generated method stub

	}

	private Player movePlayer(Player player) {
		Property deed = null;
		boolean playerTakesAnotherTurn = false;
		Player nextPlayer = board.getNextPlayer(player);
		// give view player name and player location on board
		view.loadPlayerInfo(player.getName(), player.getLocation(), player.getMoney(), player.getIsInJail());
		int diceRoll = dice.rollDice();
		boolean rolledDouble = dice.rolledDouble();
		view.setDiceRoll(diceRoll, rolledDouble, dice.getDie1(), dice.getDie2());
		if (player.getIsInJail()) {
			player.turnsInJail++;
			if (player.turnsInJail < 3) {
				// get leave jail choice from view, expecting "pay" or "roll"
				String choice = view.getLeaveJailChoice();
				if (choice.equals("pay")) {
					player.removeMoney(50);
					view.updatePlayerMoney(player.getMoney());
					player.takeOutofJail();
					view.updatePlayerLocation(player.getLocation());
					deed = board.movePlayer(player, diceRoll);
					view.updatePlayerLocation(player.getLocation());
					view.updatePlayerMoney(player.getMoney());
					player.turnsInJail = 0;
				} else {
					if (rolledDouble) {
						player.takeOutofJail();
						view.updatePlayerLocation(player.getLocation());
						deed = board.movePlayer(player, diceRoll);
						view.updatePlayerLocation(player.getLocation());
						view.updatePlayerMoney(player.getMoney());
						player.turnsInJail = 0;
					} else {
						player.turnsInJail++;
					}
				}
			} else {
				player.removeMoney(50);
				view.updatePlayerMoney(player.getMoney());
				player.takeOutofJail();
				view.updatePlayerLocation(player.getLocation());
				deed = board.movePlayer(player, diceRoll);
				view.updatePlayerLocation(player.getLocation());
				view.updatePlayerMoney(player.getMoney());
			}
			player.consecutiveTurns = 0;
		}
		// player isn't in jail
		else {
			if (rolledDouble) {
				player.consecutiveTurns++;
				if (player.consecutiveTurns == 3) {
					player.putInJail();
					player.consecutiveTurns = 0;
				} else {
					playerTakesAnotherTurn = true;
					deed = board.movePlayer(player, diceRoll);
				}
			} else {
				deed = board.movePlayer(player, diceRoll);
				player.consecutiveTurns = 0;
			}
			view.updatePlayerLocation(player.getLocation());
			view.updatePlayerMoney(player.getMoney());
		}

		if (deed instanceof Property) {
			if (deed.getOwner().equals(bank)) {
				String choice = view.getPlayerBuyChoice(deed.getName());
				if (choice.equals("yes")) {
					if (player.getMoney() > deed.getCost()) {
						board.sellPropertyFromBankToPlayer(deed, player);
					}
					else{
						view.playerCannotBuy("Not enough money.");
						Player winningPlayer = auctionProperty(deed, nextPlayer);
						if(!winningPlayer.equals(null)){
							if(winningPlayer.equals(player)){
								view.updatePlayerMoney(player.getMoney());
							}
						}
					}
				}
				else{
					Player winningPlayer = auctionProperty(deed, nextPlayer);
					if(!winningPlayer.equals(null)){
						if(winningPlayer.equals(player)){
							view.updatePlayerMoney(player.getMoney());
						}
					}
				}
			}
			//if deed is owned by another player
			else if(!deed.getOwner().equals(player)){
				//calculate rent
				Owner owner = deed.getOwner();
				int numOwned = 0;
				if(deed instanceof Street){
					if(((Street) deed).hasHotel()){
						numOwned = 5;
					}
					else{
						numOwned = ((Street) deed).getNumHouses();
					}
				}
				else{
					numOwned = board.propertiesOwnedOfType(deed);
				}

				int rent = deed.calculateRent(numOwned);
				
				//see if player can pay rent
				
				//if they can, pay it
				//if they can't, see if they can sell or mortgage to get it
				//if they can't, remove them from the game
			}
		} else {
			//find out what space player is on and take action accordingly
			//if Go, Free Parking, Just Visiting, Community Chest, or Chance
			//then do nothing (they already got $200 from landing on go)
			//if Income or Luxury tax then try to pay
			//if Go To Jail then put player in Jail
			
		}

		if(playerTakesAnotherTurn){
			nextPlayer = player;
		}
		return nextPlayer;
	}

	//returns player with highest bid or null if no player bids
	public Player auctionProperty(Property deed, Player player) {
		int highestBid = 0;
		String nameOfPlayerWithHighBid = "No One";
		Player playerWithHighestBid = null;
		List<Player> playersInAuction = new ArrayList<Player>();
		for(Player p : players){
			playersInAuction.add(p);
		}
		while(!playersInAuction.isEmpty()){
			//get player bid from view if player has enough money to bid
			//either the bid is higher than the highest bid
			//or it returns as equal because the player didn't want to bid
			//if player doesn't bid they are removed from auction
			//player with highest bid after all players are removed from action wins the auction
			int playerBid = highestBid;
			if(player.getMoney() > highestBid){
				playerBid = view.getPlayerBid(player.getName(), player.getMoney(), nameOfPlayerWithHighBid,
						highestBid);
			}
			if(playerBid > highestBid){
				highestBid = playerBid;
				playerWithHighestBid = player;
				nameOfPlayerWithHighBid = player.getName();
				player = board.getNextPlayer(playersInAuction, player);
			}
			else{
				player = board.getNextPlayer(playersInAuction, player);
				playersInAuction.remove(player);
			}
		}
		if (!playerWithHighestBid.equals(null)) {
			view.tellPlayerTheyWonBid(playerWithHighestBid.getName(), playerWithHighestBid.getMoney(), deed.getName(), highestBid);
			board.auctionPropertyFromBankToPlayer(playerWithHighestBid, deed, highestBid);
		}
		return playerWithHighestBid;
	}
	
	public void printPlayerInfo() {
		for (Player player : players) {
			System.out.println("Player: " + player.getName() + "\nToken: " + player.getToken() + "\nPosition: "
					+ board.getBoardSpaces()[player.getLocation()].getName() + "\nProperties: "
					+ player.propertiesOwned.size() + "\n");
		}
	}

	public void setPlayerTokens(List<String> tokens) {
		game.setPlayerTokensInOrder(tokens);
	}

	public List<String> getPlayerNames() {
		return game.getPlayerNamesInOrder();
	}

	public static void main(String[] args) {

		GameController gameController = new GameController();

		gameController.printPlayerInfo();

	}

}
