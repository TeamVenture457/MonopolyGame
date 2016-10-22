package cs414.a4.monopoly.backEnd;

public class Railroad extends Property{

	public Railroad(String n, int c, int r, int m){
		super(n, c, r, m);
	}
	
	public void mortgage(){
		setIsMortgaged(true);
	}

}
