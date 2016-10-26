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

	public void loadPlayerInfo(String name, int location, int money, boolean isInJail) {
		// TODO Auto-generated method stub
		
	}

	public String getLeaveJailChoice() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDiceRoll(int diceRoll, boolean rolledDouble, int die1, int die2) {
		// TODO Auto-generated method stub
		
	}

	public void updatePlayerMoney(int money) {
		// TODO Auto-generated method stub
		
	}

	public void updatePlayerLocation(int location) {
		// TODO Auto-generated method stub
		
	}

	public String getPlayerBuyChoice(String playerName) {
		// TODO Auto-generated method stub
		return null;
	}

	public void playerCannotBuy(String string) {
		// TODO Auto-generated method stub
		
	}

	public int getPlayerBid(String name, int money, String nameOfPlayerWithHighBid, int highestBid) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void tellPlayerTheyWonBid(String playerName, int playerMoney, String deedName, int highestBid) {
		// TODO Auto-generated method stub
		
	}

	public String askPlayerToSellToPayRent(int rent) {
		// TODO Auto-generated method stub
		return null;
	}

	public String askPlayerHowToPayBill(int tax) {
		// TODO Auto-generated method stub
		return null;
	}

	//return an array of strings 
	// details = {property, buyer, price}
	public String[] askPlayerWhatPropertyToWhoHowMuch(String name, List<String> playerDeeds, List<String> otherPlayerNames) {
		String [] details = new String[3];
		// TODO Auto-generated method stub
		return null;
	}

	//details = {property, buyer, price}
	//playerName is selling party
	public boolean askPlayerIfTheyWantToBuy(String playerName, String[] details) {
		// TODO Auto-generated method stub
		return false;
	}

	//details = {property, buyer, price}
	public void tellPlayerNoSale(String playerName, String[] details) {
		// TODO Auto-generated method stub
		
	}

	//get players choice of property to mortgage from list of property names
	public String getPropertyToMortgage(String name, List<String> propertiesCanMortgage) {
		// TODO Auto-generated method stub
		return null;
	}

	public void tellPlayerAllPropertiesMortgaged() {
		// TODO Auto-generated method stub
		
	}

	public String getPlayerSellPropertyWithBuildingChoice(String propertyToMortgage) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
