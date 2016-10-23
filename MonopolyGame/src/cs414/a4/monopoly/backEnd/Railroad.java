package cs414.a4.monopoly.backEnd;

public class Railroad extends Property{

	public Railroad(String name, int cost, int rent, int mortgageValue){
		super(name, cost, rent, mortgageValue);
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
