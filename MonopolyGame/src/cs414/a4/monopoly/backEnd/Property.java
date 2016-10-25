package cs414.a4.monopoly.backEnd;

public abstract class Property {

	private String name;
	private int cost;
	private int rent;
	private int mortgageValue;
	private boolean isMortgaged;
	private String description;
	
	Owner myOwner;
	Bank bank;
	
	public Property(String name, int cost, int rent, int mortgageValue) {
		this.name = name;
		this.cost = cost;
		this.rent = rent;
		this.mortgageValue = mortgageValue;
		isMortgaged = false;
        bank = Bank.getInstance();
		myOwner = bank;
		generateDescription();
	}
	
	public String getName(){
		return name;
	}
	
	public int getCost(){
		return cost;
	}
	
	public int getRent(){
		return rent;
	}
	
	public int getMortgageValue(){
		return mortgageValue;
	}
	
	public boolean isMortgaged(){
		return isMortgaged;
	}

	public void setIsMortgaged(boolean m){
		isMortgaged = m;
	}
	
	public Owner getOwner(){
		return myOwner;
	}
	
	public void setOwner(Owner newOwner){
		myOwner = newOwner;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String desc){
		description = desc;
	}
	
	public void unmortgage(){
		isMortgaged = false;
	}
	
	public abstract int calculateRent(int owned);
	public abstract boolean mortgage();
	public abstract void generateDescription();

}
