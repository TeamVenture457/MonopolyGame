package cs414.a4.monopoly.backEnd;

public class Utility extends Property{
	Dice dice;

	public Utility(String name, int cost, int rent, int mortgageValue){
		super(name, cost, rent, mortgageValue);
		dice = new Dice();
	}
	
	public boolean mortgage(){
		if(isMortgaged() == false) {
			setIsMortgaged(true);
			return true;
		}
		return false;
	}
	
	public void generateDescription(){
		String temp = "Utility\n";
		temp += getName() + "\n";
		temp += "Price: " + getCost() + "\n";
		temp += "Rent: " + getRent() + "\n";
		temp += "Mortgage: " + getMortgageValue();
		setDescription(temp);
	}

	@Override
	public int calculateRent(int owned) {
		int diceRoll = dice.rollDice();
		int rent = 0;
		switch(owned){
		case 1:
			rent = 4 * diceRoll;
			break;
		case 2:
			rent = 10 * diceRoll;
			break;
			default:
				System.out.println("Unexpected number of Utilities owned.");
		}
		// TODO Auto-generated method stub
		return rent;
	}

}
