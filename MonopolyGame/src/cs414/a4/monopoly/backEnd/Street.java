package cs414.a4.monopoly.backEnd;

public class Street extends Property{
	
	private int numHouses;
	private boolean hotel;
	private Colors color;
	private int rent1House;
	private int rent2House;
	private int rent3House;
	private int rent4House;
	private int rentHotel;
	private int houseCost;
	private int hotelCost;

	public Street(String name, int cost, int rent, int mortgageValue){
		super(name, cost, rent, mortgageValue);
		numHouses = 0;
		hotel = false;
	}
	
	public void setHouseRents(int rent1House, int rent2House, int rent3House, int rent4House, int rentHotel){
		this.rent1House = rent1House;
		this.rent2House = rent2House;
		this.rent3House = rent3House;
		this.rent4House = rent4House;
		this.rentHotel = rentHotel;
	}
	
	public int getRent1House(){
		return rent1House;
	}
	
	public int getRent2House(){
		return rent2House;
	}
	
	public int getRent3House(){
		return rent3House;
	}
	
	public int getRent4House(){
		return rent4House;
	}
	
	public int getRentHotel(){
		return rentHotel;
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
		if(numHouses == 0 && hotel == false && isMortgaged() == false){
			setIsMortgaged(true);
			return true;
		}
		return false;
	}
	
	public boolean placeHouse(){
		if(numHouses < 4 && hotel == false){
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
