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

		//End Game if only 1 player left
		if(players.size() == 1){
			view.endGame(players.get(0).getName());
			//More functionality? Exit game? exit system?  handled by the view? Not sure here.
		}
	}

	private void finishTurn(Player player) {
		boolean turnFinished = false;
		while(!turnFinished){
			// get player choices for rest of turn
			String response = view.getPlayerFinishTurnChoice(player.getName());
			switch (response) {
			case "sell property":
				sellAProperty(player);
				break;
			case "mortgage":
				mortgageAProperty(player);
				break;
			case "buy house":
				buyAHouse(player);
				break;
			case "sell house":
				sellAHouse(player);
				break;
			case "buy hotel":
				buyAHotel(player);
				break;
			case "sell hotel":
				sellAHotel(player);
				break;
			case "finish turn":
				turnFinished = true;
			case "quit game":
				// remove player from game
				break;
			default:
				System.out.println("Unexpected way to pay rent.");
			}
		}
		// until player chooses to end turn keep going
	}

	private void buyAHotel(Player player) {
		//get list streets you have all colors
		List<Street> streetsWithAllColors = getStreetsWithAllColors(player);
		//get list street names that can buy a hotel
		List<String> streetsThatCanBuyAHotel = getStreetsThatCanBuyAHotel(streetsWithAllColors);
		//if list is empty tell player they cannot buy a hotel
		if(streetsThatCanBuyAHotel.isEmpty()){

		}
		//else get player choice to buy hotel
		else{
			String streetName = view.getPlayerBuyHotelChoice(player.getName(), streetsThatCanBuyAHotel);
			Street streetToBuyHotel = (Street) board.getPropertyByName(streetName);
			//check if player has enough money
			if(player.getMoney() < streetToBuyHotel.getHotelCost()){
				view.tellPlayerCannotAffordHouse(player.getName(), streetToBuyHotel.getHotelCost());
			}
			//buy hotel
			else{
				player.buyHotel(streetToBuyHotel);
				view.updatePlayerMoney(player.getMoney());
				// tell view house bought
				view.tellPlayerHotelBought(player.getName(), streetToBuyHotel.getName());
			}
		}
	}

	private List<String> getStreetsThatCanBuyAHotel(List<Street> streetsWithAllColors) {
		List<String> streetsThatCanBuyAHotel = new ArrayList<String>();
		while(!streetsWithAllColors.isEmpty()){
			Colors thisColor = null;
			List<Street> streetsOfThisColor = new ArrayList<Street>();
			for(Street street : streetsWithAllColors){
				if(streetsWithAllColors.indexOf(street) == 0){
					thisColor = street.getColor();
				}
				if(street.getColor().equals(thisColor)){
					streetsOfThisColor.add(street);
				}
			}
			boolean allStreetsHaveMinBuildings = true;
			for(Street street : streetsOfThisColor){
				streetsWithAllColors.remove(street);
				if(street.getNumberOfBuildings() < 4){
					allStreetsHaveMinBuildings = false;
				}
			}
			if(allStreetsHaveMinBuildings){
				for(Street street : streetsOfThisColor){
					if(street.getNumHouses() == 4){
						streetsThatCanBuyAHotel.add(street.getName());
					}
				}
			}
		}
		return streetsThatCanBuyAHotel;
	}

	private void buyAHouse(Player player) {
		//get list streets you have all colors
		List<Street> streetsWithAllColors = getStreetsWithAllColors(player);
		//get list street names that can buy a house
		List<String> streetsThatCanBuyAHouse = getStreetsThatCanBuyAHouse(streetsWithAllColors);
		//if list is empty tell player they cannot buy a house
		if(streetsThatCanBuyAHouse.isEmpty()){
			view.tellPlayerNoStreetsToBuyAHouse(player.getName());
		}
		//else get player street choice to buy house
		else{
			String streetName = view.getPlayerBuyHouseChoice(player.getName(), streetsThatCanBuyAHouse);
			Street streetToBuyHouse = (Street) board.getPropertyByName(streetName);
			if(player.getMoney() < streetToBuyHouse.getHouseCost()){
				view.tellPlayerCannotAffordHouse(player.getName(), streetToBuyHouse.getHouseCost());
			}
			// buy house
			else {
				player.buyHouse(streetToBuyHouse);
				view.updatePlayerMoney(player.getMoney());
				// tell view house bought
				view.tellPlayerHouseBought(player.getName(), streetToBuyHouse.getName());
			}
		}
	}

	private List<String> getStreetsThatCanBuyAHouse(List<Street> streetsWithAllColors) {
		List<String> streetsThatCanBuyAHouse = new ArrayList<String>();
		while(!streetsWithAllColors.isEmpty()){
			Colors thisColor = null;
			List<Street> streetsOfThisColor = new ArrayList<Street>();
			for(Street street : streetsWithAllColors){
				if(streetsWithAllColors.indexOf(street) == 0){
					thisColor = street.getColor();
				}
				if(street.getColor().equals(thisColor)){
					streetsOfThisColor.add(street);
				}
			}
			int minHouses = 4;
			for(Street street : streetsOfThisColor){
				streetsWithAllColors.remove(street);
				if(street.getNumHouses() < minHouses && !street.hasHotel()){
					minHouses = street.getNumHouses();
				}
			}
			if(minHouses < 4){
				for(Street street : streetsOfThisColor){
					if(street.getNumHouses() == minHouses){
						streetsThatCanBuyAHouse.add(street.getName());
					}
				}
			}
		}
		return streetsThatCanBuyAHouse;
	}

	private List<Street> getStreetsWithAllColors(Player player) {
		List<Street> streetsPlayerOwns = new ArrayList<Street>();
		for(Property property : player.propertiesOwned){
			if(property instanceof Street){
				streetsPlayerOwns.add((Street) property);
			}
		}
		List<Street> streetsWithAllColors = new ArrayList<Street>();
		while (!streetsPlayerOwns.isEmpty()) {
			Colors thisColor = null;
			List<Street> streetsOfThisColor = new ArrayList<Street>();
			for (Street streetOwned : streetsPlayerOwns) {
				if (streetsPlayerOwns.indexOf(streetOwned) == 0) {
					thisColor = streetOwned.getColor();
				}
				if(thisColor.equals(streetOwned.getColor())){
					streetsOfThisColor.add(streetOwned);
				}
			}
			List<Street> streetsExpectedOfThisColor = board.getStreetsOfColor(thisColor);
			if(streetsExpectedOfThisColor.size() == streetsOfThisColor.size()){
				streetsWithAllColors.addAll(streetsOfThisColor);
			}
		}
		return streetsWithAllColors;
	}

	/**
	 * this method is used to begin the turn and allows the player to move
	 * if in jail, player chooses to roll or pay
	 * if 3rd turn rolling and no doubles then they have to pay.
	 * if not in jail move player as normal
	 * after player is moved, check space for action
	 **/
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
				if (player.getMoney() > 50) {
					player.removeMoney(50);
					view.updatePlayerMoney(player.getMoney());
					player.takeOutofJail();
					view.updatePlayerLocation(player.getLocation());
					deed = board.movePlayer(player, diceRoll);
					view.updatePlayerLocation(player.getLocation());
					view.updatePlayerMoney(player.getMoney());
				}
				else{
					//remove player from game
				}
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

		//player landed on a property
		if (deed instanceof Property) {
			if (deed.getOwner().equals(bank)) {
				String choice = view.getPlayerBuyChoice(deed.getName());
				if (choice.equals("yes")) {
					if (player.getMoney() > deed.getCost()) {
						player.addProperty(deed, deed.getCost());
						bank.removeProperty(deed);
					} else {
						view.playerCannotBuy("Not enough money.");
						Player winningPlayer = auctionProperty(deed, nextPlayer);
						if (!winningPlayer.equals(null)) {
							if (winningPlayer.equals(player)) {
								view.updatePlayerMoney(player.getMoney());
							}
						}
					}
				} else {
					Player winningPlayer = auctionProperty(deed, nextPlayer);
					if (!winningPlayer.equals(null)) {
						if (winningPlayer.equals(player)) {
							view.updatePlayerMoney(player.getMoney());
						}
					}
				}
			}
			// if deed is owned by another player
			else if (!deed.getOwner().equals(player) && !deed.isMortgaged()) {
				// calculate rent
				Owner owner = deed.getOwner();
				int numOwned = 0;
				if (deed instanceof Street) {
					if (((Street) deed).hasHotel()) {
						numOwned = 5;
					} else {
						numOwned = ((Street) deed).getNumHouses();
					}
				} else {
					numOwned = board.propertiesOwnedOfType(deed);
				}

				int rent = deed.calculateRent(numOwned);

				// see if player can pay rent
				if (player.getMoney() > rent) {
					player.payRent(((Player) owner), rent);
					view.updatePlayerMoney(player.getMoney());
				}
				// if they can't, see if they can sell or mortgage to get it
				else {
					getFunds(player, rent);
					if (player.getMoney() > rent) {
						player.payRent((Player) owner, rent);
						view.updatePlayerMoney(player.getMoney());
					}
					else{
						//remove player from game
						removePlayerFromGame(player);

					}
				}
			}
		} else {
			int spaceLoc = player.getLocation();
			int tax = 0;
			Space space = board.getBoardSpaces()[spaceLoc];
			switch (space.getName()) {
			case "Income Tax":
				tax = 200;
				payTax(player, tax);
				break;
			case "Luxury Tax":
				tax = 100;
				payTax(player, tax);
				break;
			case "Go To Jail":
				player.putInJail();
				view.updatePlayerLocation(spaceLoc);
				break;
			default:
				// do nothing
				break;
			}
		}

		if (playerTakesAnotherTurn && players.contains(player)) {
			nextPlayer = player;
		}
		return nextPlayer;
	}

	private void getFunds(Player player, int amount) {
		while (player.propertiesOwned.size() > 0 && player.getMoney() < amount) {
			// get list of player deed names
			// player can choose to sell or mortgage a property
			// sell buildings on a property
			// they can also choose to quit the game
			String response = view.askPlayerHowToGetFunds(amount);
			switch (response) {
			case "sell property":
				sellAProperty(player);
				break;
			case "mortgage":
				mortgageAProperty(player);
				break;
			case "sell house":
				sellAHouse(player);
				break;
			case "sell hotel":
				sellAHotel(player);
				break;
			case "quit game":
				//remove player from game
				removePlayerFromGame(player);

				break;
			default:
				System.out.println("Unexpected way to pay rent.");
			}
		}
		if(player.getMoney() < amount){
			//remove player from game
			removePlayerFromGame(player);
		}
	}

	private void sellAHotel(Player player) {
		// get list of street names with hotels
		List<String> streetsWithHotels = getStreetsWithHotels(player);
		if(streetsWithHotels.isEmpty()){
			view.tellPlayerNoStreetsHaveHotels(player.getName());
		}
		else{
			// get property that player wants to sell hotel
			String streetName = view.getPlayerSellHotelChoice(player.getName(), streetsWithHotels);
			Street street = (Street) board.getPropertyByName(streetName);
			// sell hotel off property
			player.sellHotels(street);
			view.updatePlayerMoney(player.getMoney());
		}
	}

	private List<String> getStreetsWithHotels(Player player) {
		List<String> streetsWithHotels = new ArrayList<String>();
		for(Property property : player.propertiesOwned){
			if(property instanceof Street){
				Street street = (Street) property;
				if(street.hasHotel()){
					streetsWithHotels.add(street.getName());
				}
			}
		}
		return streetsWithHotels;
	}

	private void sellAHouse(Player player) {
		// get list of properties with houses
		List<Street> streetsWithHouses = getStreetsWithHouses(player);
		if (streetsWithHouses.isEmpty()) {
			view.tellPlayerNoStreetsHaveHouses(player.getName());
		} else {
			// get list of properties with houses that can be sold
			List<String> streetsThatCanSellAHouse = getStreetsThatCanSellAHouse(streetsWithHouses);
			// get property that player wants to sell house
			String streetToSellHouse = view.getPlayerSellHouseChoice(player.getName(), streetsThatCanSellAHouse);
			Street streetSellingHouse = (Street) board.getPropertyByName(streetToSellHouse);
			// sell house off property
			player.sellHouse(streetSellingHouse);
			view.updatePlayerMoney(player.getMoney());
		}
	}

	private List<String> getStreetsThatCanSellAHouse(List<Street> streetsWithHouses) {

		List<String> streetsThatCanSellAHouse = new ArrayList<String>();

		while (!streetsWithHouses.isEmpty()) {
			List<Street> streetsOfCurrentColor = new ArrayList<Street>();
			Colors currentColor = null;
			for (Street currentStreet : streetsWithHouses) {
				if (streetsWithHouses.indexOf(currentStreet) == 0) {
					currentColor = currentStreet.getColor();
				}
				if (currentStreet.getColor().equals(currentColor)) {
					streetsOfCurrentColor.add(currentStreet);
				}
			}
			int mostHouses = 0;
			for (Street currentColorStreet : streetsOfCurrentColor) {
				streetsWithHouses.remove(currentColorStreet);
				if (currentColorStreet.getNumHouses() > mostHouses) {
					mostHouses = currentColorStreet.getNumberOfBuildings();
				}
			}
			for (Street currentColorStreet : streetsOfCurrentColor) {
				if (currentColorStreet.getNumberOfBuildings() == mostHouses) {
					streetsThatCanSellAHouse.add(currentColorStreet.getName());
				}
			}
		}
		return streetsThatCanSellAHouse;
	}

	private List<Street> getStreetsWithHouses(Player player) {
		List<Street> streetsWithHouses = new ArrayList<Street>();
		List<Street> streetsOwned = getStreetProperties(player.propertiesOwned);
		for (Street streetOwned : streetsOwned) {
			if (streetOwned.getNumHouses() > 0) {
				streetsWithHouses.add(streetOwned);
			}
		}
		return streetsWithHouses;
	}

	private List<Street> getStreetProperties(List<Property> propertiesOwned) {
		List<Street> streets = new ArrayList<Street>();
		for (Property property : propertiesOwned) {
			if (property instanceof Street) {
				streets.add((Street) property);
			}
		}
		return streets;
	}

	// this method assumes the player has properties
	private void mortgageAProperty(Player player) {
		// get list of mortgage-able properties
		List<String> propertiesCanMortgage = getPropertiesCanMortgage(player);
		if (propertiesCanMortgage.size() > 0) {
			String propertyToMortgage = view.getPropertyToMortgage(player.getName(), propertiesCanMortgage);
			Property mortgageProperty = board.getPropertyByName(propertyToMortgage);
			if (mortgageProperty instanceof Street) {
				Street street = (Street) mortgageProperty;
				if (street.hasHotel() || street.getNumHouses() > 0) {
					String choice = view.getPlayerSellPropertyWithBuildingChoice(propertyToMortgage);
					if (choice.equals("yes")) {
						// get list of all streets of street color
						List<Street> colorStreets = getListOfOwnedStreetsByColor(player, street);
						// remove all buildings from all streets of this street
						// color
						for (Street thisStreet : colorStreets) {
							player.sellHotels(thisStreet);
							while (thisStreet.getNumHouses() > 0) {
								player.sellHouse(thisStreet);
							}
						}
						player.morgage(street);
						view.updatePlayerMoney(player.getMoney());
						// mortgage this street
					} else {
						view.tellPlayerAllPropertiesMortgaged();
						return;
					}
				} else {
					// no buildings so just mortgage street
					player.morgage(street);
					view.updatePlayerMoney(player.getMoney());
				}
			} else {
				// property is railroad or utility
				// so just mortgage it
				player.morgage(mortgageProperty);
				view.updatePlayerMoney(player.getMoney());
			}
		}
	}

	private List<Street> getListOfOwnedStreetsByColor(Player player, Street street) {
		List<Street> colorStreets = new ArrayList<Street>();
		for (Property property : player.propertiesOwned) {
			if (property instanceof Street) {
				Street thisStreet = (Street) property;
				if (thisStreet.getColor().equals(street.getColor())) {
					colorStreets.add(thisStreet);
				}
			}
		}
		return colorStreets;
	}

	private List<String> getStreetsThatCanSellBuildings(List<Street> streetsWithBuildings) {
		List<String> streetsThatCanSellBuildings = new ArrayList<String>();

		while (!streetsWithBuildings.isEmpty()) {
			List<Street> streetsOfCurrentColor = new ArrayList<Street>();
			Colors currentColor = null;
			for (Street currentStreet : streetsWithBuildings) {
				if (streetsWithBuildings.indexOf(currentStreet) == 0) {
					currentColor = currentStreet.getColor();
				}
				if (currentStreet.getColor().equals(currentColor)) {
					streetsOfCurrentColor.add(currentStreet);
				}
			}
			int mostBuildings = 0;
			for (Street currentColorStreet : streetsOfCurrentColor) {
				streetsWithBuildings.remove(currentColorStreet);
				if (currentColorStreet.getNumberOfBuildings() > mostBuildings) {
					mostBuildings = currentColorStreet.getNumberOfBuildings();
				}
			}
			for (Street currentColorStreet : streetsOfCurrentColor) {
				if (currentColorStreet.getNumberOfBuildings() == mostBuildings) {
					streetsThatCanSellBuildings.add(currentColorStreet.getName());
				}
			}
		}
		return streetsThatCanSellBuildings;
	}

	private List<String> getPropertiesCanMortgage(Player player) {
		List<String> propertiesCanMortgage = new ArrayList<String>();
		for (Property property : player.propertiesOwned) {
			if (!property.isMortgaged()) {
				propertiesCanMortgage.add(property.getName());
			}
		}
		return propertiesCanMortgage;
	}

	// this method assumes a player has properties
	private void sellAProperty(Player player) {
		// ask player what property they want to sell
		// to who
		// for how much
		// details = {property, buyer, price}
		List<String> playerDeeds = new ArrayList<String>();
		for (Property deed : player.propertiesOwned) {
			playerDeeds.add(deed.getName());
		}
		List<String> otherPlayerNames = getOtherPlayerNames(player);
		String[] details = view.askPlayerWhatPropertyToWhoHowMuch(player.getName(), playerDeeds, otherPlayerNames);
		if (view.askPlayerIfTheyWantToBuy(player.getName(), details)) {
			Player buyer = board.getPlayerByName(details[1]);
			Property deed = board.getPropertyByName(details[0]);
			int price = Integer.parseInt(details[2]);
			if(buyer.getMoney() < price){
				view.tellPlayerBuyerCannotBuy(player.getName(), buyer.getName(), price);
			} else {
				buyer.addProperty(deed, price);
				player.removeProperty(deed, price);
				view.updatePlayerMoney(player.getMoney());
			}
		} else {
			view.tellPlayerNoSale(player.getName(), details);
		}
	}

	private List<String> getOtherPlayerNames(Player player) {
		List<String> otherPlayerNames = new ArrayList<String>();
		for (Player currentPlayer : players) {
			if (!currentPlayer.equals(player)) {
				otherPlayerNames.add(currentPlayer.getName());
			}
		}
		return otherPlayerNames;
	}

	private void payTax(Player player, int tax) {
		if (player.getMoney() > tax) {
			player.removeMoney(tax);
		} else {
			getFunds(player, tax);
			if (player.getMoney() > tax) {
				player.removeMoney(tax);
			}
			else{
				//remove player
				removePlayerFromGame(player);
			}
		}
	}

	// returns player with highest bid or null if no player bids
	public Player auctionProperty(Property deed, Player player) {
		int highestBid = 0;
		String nameOfPlayerWithHighBid = "No One";
		Player playerWithHighestBid = null;
		List<Player> playersInAuction = new ArrayList<Player>();
		for (Player p : players) {
			playersInAuction.add(p);
		}
		while (!playersInAuction.isEmpty()) {
			// get player bid from view if player has enough money to bid
			// either the bid is higher than the highest bid
			// or it returns as equal because the player didn't want to bid
			// if player doesn't bid they are removed from auction
			// player with highest bid after all players are removed from action
			// wins the auction
			int playerBid = highestBid;
			if (player.getMoney() > highestBid) {
				playerBid = view.getPlayerBid(player.getName(), player.getMoney(), nameOfPlayerWithHighBid, highestBid);
			}
			if (playerBid > highestBid) {
				highestBid = playerBid;
				playerWithHighestBid = player;
				nameOfPlayerWithHighBid = player.getName();
				player = board.getNextPlayer(playersInAuction, player);
			} else {
				player = board.getNextPlayer(playersInAuction, player);
				playersInAuction.remove(player);
			}
		}
		if (!playerWithHighestBid.equals(null)) {
			view.tellPlayerTheyWonBid(playerWithHighestBid.getName(), playerWithHighestBid.getMoney(), deed.getName(),
					highestBid);
			playerWithHighestBid.addProperty(deed, highestBid);
			bank.removeProperty(deed);
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

	private void removePlayerFromGame(Player player){
		players.remove(player);
		for(Property deed : player.propertiesOwned){
			if(deed instanceof Street){
				while(((Street) deed).getNumHouses() > 0){
					((Street) deed).removeHouse();
				}
				while(((Street) deed).hasHotel()){
					((Street) deed).removeHotel();
				}
			}
			player.removeProperty(deed);
			bank.addProperty(deed);
		}
	}

	public static void main(String[] args) {

		GameController gameController = new GameController();

		gameController.printPlayerInfo();

	}

}
