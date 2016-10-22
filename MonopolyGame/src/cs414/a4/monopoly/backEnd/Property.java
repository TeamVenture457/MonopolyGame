package cs414.a4.monopoly.backEnd;

public abstract class Property {

	private String name;
	private int cost;
	private int rent;
	private int mortgageValue;
	private boolean isMortgaged;
	private String description;
	
	Owner myOwner;
	
	public Property(String n, int c, int r, int m, Owner bank) {
		name = n;
		cost = c;
		rent = r;
		mortgageValue = m;
		isMortgaged = false;
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
	
	public abstract boolean mortgage();
	public abstract void generateDescription();

}
