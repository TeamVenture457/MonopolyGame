package cs414.a4.monopoly.backEnd;

public class Utility extends Property{

	public Utility(String n, int c, int r, int m){
		super(n, c, r, m);
	}
	
	public boolean mortgage(){
		setIsMortgaged(true);
		return true;
	}

}
