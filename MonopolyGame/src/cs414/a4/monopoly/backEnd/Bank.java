package cs414.a4.monopoly.backEnd;

public class Bank extends Owner{

	private int inventoryOfHouses;
	private int inventoryOfHotels;
	boolean hotelsInStock;
	boolean housesInStock;

	private static Bank instance = new Bank();

	private Bank() {
		this.inventoryOfHotels = 12;
		this.inventoryOfHouses = 32;
	}

	public static Bank getInstance(){
		return instance;
	}

	public void addHouseToBank() {
		this.inventoryOfHouses++;
		this.houseInventoryCheck();
	}

	public boolean removeHouseFromBank(){
		houseInventoryCheck();
		if(housesInStock) {
			this.inventoryOfHouses--;
			houseInventoryCheck();
			return true;
		}else{
			SystemMessagesToPlayer.getSystemMessage(1);
			return false;
		}
	}

	public void addHotelToBank() {
			this.inventoryOfHotels++;
			hotelInventoryCheck();
	}

	public boolean removeHotelFromBank() {
		hotelInventoryCheck();
		if(hotelsInStock) {
			this.inventoryOfHotels--;
			hotelInventoryCheck();
		}else{
			SystemMessagesToPlayer.getSystemMessage(1);
			return false;
		}
		return true;
	}

	public void addProperty(Property property){
		this.propertiesOwned.add(property);
	}

	public Property removeProperty(Property property) {
		if(this.propertiesOwned.contains(property)){
			this.propertiesOwned.remove(property);
			return property;
		}else {
			SystemMessagesToPlayer.getSystemMessage(3);
		} return null;
	}

	public boolean mortgage(Property property) {
		boolean mortgageSuccessfull;
		mortgageSuccessfull = property.mortgage();
		if(mortgageSuccessfull==false){
			SystemMessagesToPlayer.getSystemMessage(5);
			return mortgageSuccessfull;
		}
		return mortgageSuccessfull;
	}

	public int getInventoryOfHotels() {
		return inventoryOfHotels;
	}

	public int getInventoryOfHouses() {
		return inventoryOfHouses;
	}

	private void hotelInventoryCheck(){
		if(this.getInventoryOfHotels()==0){
			this.hotelsInStock=false;
		}else{
			this.hotelsInStock=true;
		}
	}

	private void houseInventoryCheck(){
		if(this.getInventoryOfHouses()==0){
			this.housesInStock=false;
		}else{
			this.housesInStock=true;
		}
	}

}