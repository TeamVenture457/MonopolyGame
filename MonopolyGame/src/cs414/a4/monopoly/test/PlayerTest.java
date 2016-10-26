package cs414.a4.monopoly.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a4.monopoly.backEnd.*;

public class PlayerTest {
	
	Player player;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBuyPropertyAble() {
		player = new Player("JoeBob");
		
		Street testStreet = new Street("testStreet", 500, 200, 250);
		player.addProperty(testStreet, testStreet.getCost());
		
		assertTrue(player.getProperties().contains(testStreet));
		assertEquals(player.getMoney(), 1000);
	}
	
	@Test
	public void testBuyPropertyCantAfford(){
		player = new Player("JoeBob");
		
		Street testStreet = new Street("testStreet", 500, 200, 250);
		player.removeMoney(1500);
		player.addProperty(testStreet, testStreet.getCost());
		
		assertFalse(player.getProperties().contains(testStreet));
		assertEquals(player.getMoney(), 0);
	}
	
	@Test
	public void testSellPropertyOwned(){
		player = new Player("JoeBob");
		
		Street testStreet = new Street("testStreet", 500, 200, 250);
		player.addProperty(testStreet);
		
		int moneyCheck = player.getMoney();
		
		player.removeProperty(testStreet, 500);
		
		assertFalse(player.getProperties().contains(testStreet));
		assertEquals(player.getMoney(), moneyCheck+500);
	}
	
	@Test
	public void testSellPropertyNotOwned(){
		player = new Player("JoeBob");
		
		Street testStreet = new Street("testStreet", 500, 200, 250);
		
		int moneyCheck = player.getMoney();
		player.removeProperty(testStreet, 500);
		
		assertFalse(player.getProperties().contains(testStreet));
		assertEquals(player.getMoney(), moneyCheck);
	}
	
	@Test
	public void testBuyHouseAble(){
		player = new Player("JoeBob");
		
		Street testStreet = new Street("testStreet", 500, 200, 250);
		testStreet.setHouseCost(100);
		testStreet.setHotelCost(200);
		player.addProperty(testStreet);
		
		int moneyCheck = player.getMoney();
		
		player.buyHouse(testStreet);
		assertEquals(testStreet.getNumHouses(), 1);
		assertEquals(player.getMoney(), moneyCheck-testStreet.getHouseCost());
	}
	
	@Test
	public void testBuyTooManyHouses(){
		player = new Player("JoeBob");
		
		Street testStreet = new Street("testStreet", 500, 200, 250);
		testStreet.setHouseCost(100);
		testStreet.setHotelCost(200);
		player.addProperty(testStreet);
		
		int moneyCheck = player.getMoney();
		
		for(int i=0; i<5; i++){
			player.buyHouse(testStreet);
		}
		
		assertEquals(testStreet.getNumHouses(), 4);
		assertEquals(player.getMoney(), moneyCheck-(testStreet.getHouseCost()*4));
	}
	
	@Test
	public void testBuyHotelAble(){
		player = new Player("JoeBob");
		
		Street testStreet = new Street("testStreet", 500, 200, 250);
		testStreet.setHouseCost(100);
		testStreet.setHotelCost(200);
		player.addProperty(testStreet);
		
		for(int i=0; i<4; i++){
			player.buyHouse(testStreet);
		}
		
		int moneyCheck = player.getMoney();
		
		player.buyHotel(testStreet);
		assertTrue(testStreet.hasHotel());
		assertEquals(player.getMoney(), moneyCheck-testStreet.getHotelCost());
	}
	
	@Test
	public void butHotelNotEnoughHouses(){
		player = new Player("JoeBob");
		
		Street testStreet = new Street("testStreet", 500, 200, 250);
		testStreet.setHouseCost(100);
		testStreet.setHotelCost(200);
		player.addProperty(testStreet);
		
		for(int i=0; i<3; i++){
			player.buyHouse(testStreet);
		}
		
		int moneyCheck = player.getMoney();
		
		player.buyHotel(testStreet);
		
		
		
		assertFalse(testStreet.hasHotel());
		assertEquals(testStreet.getNumHouses(), 3);
		assertEquals(player.getMoney(), moneyCheck);
	}
	
	@Test
	public void testBuyHotelHasHotel(){
		player = new Player("JoeBob");
		
		Street testStreet = new Street("testStreet", 500, 200, 250);
		testStreet.setHouseCost(100);
		testStreet.setHotelCost(200);
		player.addProperty(testStreet);
		
		for(int i=0; i<4; i++){
			player.buyHouse(testStreet);
		}
		
		player.buyHotel(testStreet);
		
		int moneyCheck = player.getMoney();
		
		player.buyHotel(testStreet);
		
		assertTrue(testStreet.hasHotel());
		assertEquals(player.getMoney(), moneyCheck);
	}
	
	@Test
	public void testBuyHouseHasHotel(){
		player = new Player("JoeBob");
		
		Street testStreet = new Street("testStreet", 500, 200, 250);
		testStreet.setHouseCost(100);
		testStreet.setHotelCost(200);
		player.addProperty(testStreet);
		
		for(int i=0; i<4; i++){
			player.buyHouse(testStreet);
		}
		
		player.buyHotel(testStreet);
		
		int moneyCheck = player.getMoney();
		
		player.buyHouse(testStreet);
		
		assertTrue(testStreet.hasHotel());
		assertEquals(testStreet.getNumHouses(), 0);
		assertEquals(player.getMoney(), moneyCheck);
	}

}
