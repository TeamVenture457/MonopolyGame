package cs414.a4.monopoly.backEnd;

public class Space {
	
	private String name;
	private SpaceType type;
	private int rank;
	private Property deed;

	public Space(String name, SpaceType type, int rank, Property deed) {
		this.name = name;
		this.type = type;
		this.rank = rank;
		this.deed = deed;
	}
	
	public Property getDeed(){
		return deed;
	}
	
	public int getRank(){
		return rank;
	}
	
	public String getName(){
		return name;
	}
	
	public SpaceType getType(){
		return type;
	}
	
	public String getSpaceDescription(){
		String spaceDescription = "Name: " + name + "\nSpace Type: " + type.toString() + "\nRank: " + rank + "\nDeed Type: ";
		if(deed instanceof Property){
			spaceDescription += deed.getDescription();
		}
		else {
			spaceDescription += "null";
		}
		spaceDescription += "\n";
		return spaceDescription;
	}
}
