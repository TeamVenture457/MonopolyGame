package cs414.a4.monopoly.backEnd;

public class Utility extends Property{

	public Utility(String name, int cost, int rent, int mortgageValue){
		super(name, cost, rent, mortgageValue);
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

}
