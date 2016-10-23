package cs414.a4.monopoly.backEnd;

public class Player extends Owner{
	
	protected String name;
	private String token;
	private Space location;
	private boolean isInJail;

	public Player(String name) {
		this.name = name;
	}

	@Override
	public void buyHouse(Street street){
		Bank.getInstance().buyHouse();
	}

	@Override
	public void buyHotel(Property property) {

	}

	@Override
	public void morgage(Property property) {

	}

	@Override
	public Property buyProperty(Property property) {
		return null;
	}

	@Override
	public Property sellProperty(Property property) {
		return null;
	}

	@Override
	public void sellHouse(Property property) {

	}

	@Override
	public void sellHotels(Property property) {

	}

}
