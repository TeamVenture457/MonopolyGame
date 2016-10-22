package cs414.a4.monopoly.backEnd;

public class Street extends Property{
	
	private int numHouses;
	private boolean hotel;
	private Colors color;
	private int houseCost;
	private int hotelCost;
	
	public Street(String name, int cost, int rent, int mortgageValue, Owner bank){
		super(name, cost, rent, mortgageValue, bank);
		numHouses = 0;
		hotel = false;
	}
	
	public int getHouseCost(){
		return houseCost;
	}
	
	public void setHouseCost(int houseCost){
		this.houseCost = houseCost;
	}
	
	public int getHotelCost(){
		return hotelCost;
	}
	
	public void setHotelCost(int hotelCost){
		this.hotelCost = hotelCost;
	}
	
	
	public int getNumHouses(){
		return numHouses;
	}
	
	public Colors getColor(){
		return color;
	}
	
	public void setColor(Colors color){
		this.color = color;
	}
	
	public boolean hasHotel(){
		return hotel;
	}
	
	public boolean mortgage(){
		if(numHouses == 0 && hotel == false){
			setIsMortgaged(true);
		}
		return false;
	}
	
	public boolean placeHouse(){
		if(numHouses < 4){
			numHouses++;
			return true;
		}
		return false;
	}
	
	public boolean placeHotel(){
		if(hotel == false && numHouses == 4){
			numHouses = 0;
			hotel = true;
			return true;
		}
		return false;
	}
	
	public boolean removeHouse(){
		if(numHouses > 0){
			numHouses--;
			return true;
		}
		return false;
	}
	
	public boolean removeHotel(){
		if(hotel == true){
			hotel = false;
			numHouses = 4;
			return true;
		}
		return false;
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
