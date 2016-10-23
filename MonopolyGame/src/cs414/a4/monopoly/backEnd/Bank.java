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

	@Override
	public void buyHotel(Property property) {
		this.inventoryOfHotels++;
		this.hotelInventoryCheck();
	}

	@Override
	public void buyHouse(Property property) {
		this.inventoryOfHouses++;
		this.houseInventoryCheck();
	}

	@Override
	public Property buyProperty(Property property) {
		this.propertiesOwned.add(property);
		property.setOwner(this);
		return property;
	}

	@Override
	public void sellHotels(Property property) {
		if(hotelsInStock) {
			this.inventoryOfHotels--;
			hotelInventoryCheck();
		}else{
			SystemMessagesToPlayer.getSystemMessage(1);
		}
	}

	@Override
	public void sellHouse(Property property) {
		if(housesInStock) {
			this.inventoryOfHouses--;
			houseInventoryCheck();
		}else{
			SystemMessagesToPlayer.getSystemMessage(2);
		}
	}

	@Override
	public Property sellProperty(Property property) {
		if(this.propertiesOwned.contains(property)){
			this.propertiesOwned.remove(property);
			return property;
		}
		SystemMessagesToPlayer.getSystemMessage(3);
		return null;
	}

	@Override
	public void morgage(Property property) {

	}

	public int getInventoryHotels() {
		return inventoryOfHotels;
	}

	public int getInventoryHouses() {
		return inventoryOfHouses;
	}

	private void hotelInventoryCheck(){
		if(this.getInventoryHotels()==0){
			this.hotelsInStock=false;
		}else{
			this.hotelsInStock=true;
		}
	}

	private void houseInventoryCheck(){
		if(this.getInventoryHouses()==0){
			this.housesInStock=false;
		}else{
			this.housesInStock=true;
		}
	}

	public void addProperty(Property property){
		this.propertiesOwned.add(property);
	}


}