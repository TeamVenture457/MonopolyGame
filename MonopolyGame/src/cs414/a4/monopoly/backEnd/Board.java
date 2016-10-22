package cs414.a4.monopoly.backEnd;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Board {

	final int NUMBEROFBOARDSPACES = 41;
	Space[] boardSpaces;
	Player [] players;
	Bank bank;
	Document monopolySpacesDoc;
	String xmlFilename;
	
	public Board(Player [] players) {
		xmlFilename = "monopolySpaces.xml";
		this.players = players;
		bank = new Bank();
		if(this.getXMLDoc()){
			boardSpaces = new Space[NUMBEROFBOARDSPACES];
			this.fillBoardSpaces();
		}
		else{
			System.out.println("Problem setting up board.");
		}
		// TODO Auto-generated constructor stub
	}

	private Boolean getXMLDoc() {
		try {
			File xmlFile = new File("MonopolyGame/src/resources/" + xmlFilename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			monopolySpacesDoc = dBuilder.parse(xmlFile);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private void fillBoardSpaces() {
		monopolySpacesDoc.getDocumentElement().normalize();
		NodeList nList = monopolySpacesDoc.getElementsByTagName("space");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			Space thisSpace = null;

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				String name = eElement.getAttribute("name");
				String type = eElement.getElementsByTagName("type").item(0).getTextContent();
				SpaceType thisSpaceType = getSpaceType(type, name);
				String position = eElement.getElementsByTagName("position").item(0).getTextContent();
				int rank = Integer.parseInt(position);

				if (thisSpaceType.equals(SpaceType.PROPERTY)) {
					String costString = eElement.getElementsByTagName("price").item(0).getTextContent();
					int cost = Integer.parseInt(costString);
					String rentString = eElement.getElementsByTagName("rent").item(0).getTextContent();
					int rent = Integer.parseInt(rentString);
					String mortgageValueString = eElement.getElementsByTagName("mortgagevalue").item(0).getTextContent();
					int mortgageValue = Integer.parseInt(mortgageValueString); 
					
					
					switch (type) {
					case "street":
						String rent1HouseString = eElement.getElementsByTagName("rent1house").item(0).getTextContent();
						int rent1House = Integer.parseInt(rent1HouseString);
						String rent2HouseString = eElement.getElementsByTagName("rent2houses").item(0).getTextContent();
						int rent2House = Integer.parseInt(rent2HouseString);
						String rent3HouseString = eElement.getElementsByTagName("rent3houses").item(0).getTextContent();
						int rent3House = Integer.parseInt(rent3HouseString);
						String rent4HouseString = eElement.getElementsByTagName("rent4houses").item(0).getTextContent();
						int rent4House = Integer.parseInt(rent4HouseString);
						String rentHotelString = eElement.getElementsByTagName("renthotel").item(0).getTextContent();
						int rentHotel = Integer.parseInt(rentHotelString);
						Colors thisColor = getColor(eElement.getElementsByTagName("color").item(0).getTextContent());
						String houseCostString = eElement.getElementsByTagName("housecost").item(0).getTextContent();
						int houseCost = Integer.parseInt(houseCostString);						
						String hotelCostString = eElement.getElementsByTagName("hotelcost").item(0).getTextContent();
						int hotelCost = Integer.parseInt(hotelCostString);
						Street thisStreet = new Street(name, cost, rent, mortgageValue, bank);
						thisStreet.setColor(thisColor);
						thisStreet.setHouseRents(rent1House, rent2House, rent3House, rent4House, rentHotel);
						thisStreet.setHouseCost(houseCost);
						thisStreet.setHotelCost(hotelCost);
						thisSpace = new Space(name, thisSpaceType, rank, thisStreet);
						break;
					case "railroad":
						Railroad thisRailroad = new Railroad(name, cost, rent, mortgageValue, bank);
						thisSpace = new Space(name, thisSpaceType, rank, thisRailroad);
						break;
					case "utility":
						Utility thisUtility = new Utility(name, cost, rent, mortgageValue, bank);
						thisSpace = new Space(name, thisSpaceType, rank, thisUtility);
						break;
					default:
						System.out.println("Unexpected property type " + type + " given by space " + name + ".");
					}
				} else {
					thisSpace = new Space(name, thisSpaceType, rank, null);
				}

			}
			if (thisSpace instanceof Space) {
				boardSpaces[thisSpace.getRank()] = thisSpace;
				System.out.println(boardSpaces[thisSpace.getRank()].getSpaceDescription());
			}
		}
	}

	private SpaceType getSpaceType(String type, String spaceName) {
		SpaceType thisSpaceType = null;
		switch (type) {
		case "street":
		case "railroad":
		case "utility":
			thisSpaceType = SpaceType.PROPERTY;
			break;
		case "other":
			switch (spaceName) {
			case "Go":
				thisSpaceType = SpaceType.GO;
				break;
			case "Community Chest":
				thisSpaceType = SpaceType.COMMUNITYCHEST;
				break;
			case "Income Tax":
				thisSpaceType = SpaceType.INCOMETAX;
				break;
			case "Luxury Tax":
				thisSpaceType = SpaceType.LUXURYTAX;
				break;
			case "Chance":
				thisSpaceType = SpaceType.CHANCE;
				break;
			case "In Jail":
				thisSpaceType = SpaceType.INJAIL;
				break;
			case "Just Visiting":
				thisSpaceType = SpaceType.JUSTVISITING;
				break;
			case "Free Parking":
				thisSpaceType = SpaceType.FREEPARKING;
				break;
			case "Go To Jail":
				thisSpaceType = SpaceType.GOTOJAIL;
				break;
			default:
				System.out.println("Unexpected name " + spaceName + " from space with type " + type + ".");
			}
			break;
		default:
			System.out.println("Unexpected space type " + type + " from space named " + spaceName + ".");
		}
		return thisSpaceType;
	}
	
	public Colors getColor(String color){
		Colors thisColor = null;
		switch(color){
		case "red":
			thisColor = Colors.RED;
			break;
		case "blue":
			thisColor = Colors.BLUE;
			break;
		case "brown":
			thisColor = Colors.BROWN;
			break;
		case "sky":
			thisColor = Colors.SKY;
			break;
		case "purple":
			thisColor = Colors.PURPLE;
			break;
		case "orange":
			thisColor = Colors.ORANGE;
			break;
		case "green":
			thisColor = Colors.GREEN;
			break;
		case "yellow":
			thisColor = Colors.YELLOW;
			break;
			default:
				System.out.println("Unexpected street color " + color + ".\n");
		}
		return thisColor;
	}
	
	public static void main(String[] args) {
		Player [] players = new Player[1];
		players[0] = new Player();
		Board myBoard = new Board(players);
	}
}
