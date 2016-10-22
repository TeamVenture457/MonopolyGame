package cs414.a4.monopoly.backEnd;

public class Railroad extends Property{

	public Railroad(String name, int cost, int rent, int mortgageValue, Owner bank){
		super(name, cost, rent, mortgageValue, bank);
	}
	
	public boolean mortgage(){
		setIsMortgaged(true);
		return true;
	}
	
	public void generateDescription(){
		String temp = "Railroad\n";
		temp += getName() + "\n";
		temp += "Price: " + getCost() + "\n";
		temp += "Rent: " + getRent() + "\n";
		temp += "Mortgage: " + getMortgageValue();
		setDescription(temp);
	}

}
