package cs414.a4.monopoly.backEnd;

import java.util.ArrayList;
import java.util.List;

public abstract class Owner {
    SystemMessagesToPlayer systemMessages;
	protected List<Property> propertiesOwned;

	public Owner() {
		this.propertiesOwned = new ArrayList<Property>();
	}

    public List<Property> getPropertiesOwned(){
        return this.propertiesOwned;
    }

	public abstract void buyHouse(Property property);
    public abstract void buyHotel(Property property);
    public abstract void morgage(Property property);
    public abstract Property buyProperty(Property property);
    public abstract Property sellProperty(Property property);
    public abstract void sellHouse(Property property);
    public abstract void sellHotels(Property property);


}
