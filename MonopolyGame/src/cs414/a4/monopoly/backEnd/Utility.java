package cs414.a4.monopoly.backEnd;

public class Utility extends Property{

	public Utility(String n, int c, int r, int m, Owner bank){
		super(n, c, r, m, bank);
	}
	
	public boolean mortgage(){
		setIsMortgaged(true);
		return true;
	}

}
