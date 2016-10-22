package cs414.a4.monopoly.backEnd;

public class Street extends Property{
	
	private int houses;
	private int hotel;
	private Colors color;
	
	public Street(String n, int c, int r, int m){
		super(n, c, r, m);
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

}
