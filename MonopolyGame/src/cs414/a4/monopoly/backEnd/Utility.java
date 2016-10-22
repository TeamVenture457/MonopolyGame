package cs414.a4.monopoly.backEnd;

public class Utility extends Property{

	public Utility(String n, int c, int r, int m, Owner bank){
		super(n, c, r, m, bank);
	}
	
	public boolean mortgage(){
		setIsMortgaged(true);
		return true;
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
