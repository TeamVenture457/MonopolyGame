package cs414.a4.monopoly.backEnd;

public class Street extends Property{
	
	private int houses;
	private int hotel;
	private Colors color;
	
	public Street(String name, int cost, int rent, int mortgageValue, Owner bank){
		super(name, cost, rent, mortgageValue, bank);
	}
	
	public int numHouses(){
		return houses;
	}
	
	public boolean hasHotel(){
		return false;
	}
	
	public boolean mortgage(){
		//Not yet implemented
		return false;
	}
	
	public void placeHouse(){
		
	}
	
	public void placeHotel(){
		
	}
	
	public void generateDescription(){
		String temp = "Street\n";
		temp += getName() + "\n";
		temp += "Price: " + getCost() + "\n";
		temp += "Rent: " + getRent() + "\n";
		temp += "Mortgage: " + getMortgageValue();
		setDescription(temp);
	}

}
