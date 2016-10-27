package cs414.a4.monopoly.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a4.monopoly.backEnd.*;

public class BankTest {

	Bank bank;
	
	@Before
	public void setUp() throws Exception{
		bank = Bank.getInstance();
	}

	@After
	public void tearDown() throws Exception{
		bank.resetBank();
	}

	@Test
	public void testRemoveHouseFullInventory(){
		int checkNumHouses = bank.getInventoryOfHouses();
		
		bank.removeHouseFromBank();
		
		assertEquals(bank.getInventoryOfHouses(), checkNumHouses-1);
		assertTrue(bank.getHasHouses());
	}
	
	@Test
	public void testRemoveHouseHalfInventory(){
		int startVal = bank.getInventoryOfHouses()/2;
		for(int i=0; i<startVal; i++){
			bank.removeHouseFromBank();
		}
		
		int checkNumHouses = bank.getInventoryOfHouses();
		
		bank.removeHouseFromBank();
		
		assertEquals(bank.getInventoryOfHouses(), checkNumHouses-1);
		assertTrue(bank.getHasHouses());
	}
	
	@Test
	public void testRemoveHouseEmptyInventory(){
		int startVal = bank.getInventoryOfHouses();
		for(int i=0; i<startVal; i++){
			bank.removeHouseFromBank();
		}
		
		int checkNumHouses = bank.getInventoryOfHouses();
		
		bank.removeHouseFromBank();
		
		assertEquals(bank.getInventoryOfHouses(), checkNumHouses);
		assertFalse(bank.getHasHouses());
	}
	
	@Test
	public void testRemoveHotelFullInventory(){
		int checkNumHotels = bank.getInventoryOfHotels();
		
		bank.removeHotelFromBank();
		
		assertEquals(bank.getInventoryOfHotels(), checkNumHotels-1);
		assertTrue(bank.getHasHotels());
	}
	
	@Test
	public void testRemoveHotelHalfInventory(){
		int startVal = bank.getInventoryOfHotels()/2;
		for(int i=0; i<startVal; i++){
			bank.removeHotelFromBank();
		}
		
		int checkNumHotels = bank.getInventoryOfHotels();
		
		bank.removeHotelFromBank();
		
		assertEquals(bank.getInventoryOfHotels(), checkNumHotels-1);
		assertTrue(bank.getHasHotels());
	}
	
	@Test
	public void testRemoveHotelEmptyInventory(){
		int startVal = bank.getInventoryOfHotels();
		for(int i=0; i<startVal; i++){
			bank.removeHotelFromBank();
		}
		
		int checkNumHotels = bank.getInventoryOfHotels();
		
		bank.removeHotelFromBank();
		
		
		assertEquals(bank.getInventoryOfHotels(), checkNumHotels);
		assertFalse(bank.getHasHotels());
	}
	
	@Test
	public void testAddHouseWhenInStock(){
		bank.removeHouseFromBank();
		int checkHouses = bank.getInventoryOfHouses();
		
		bank.addHouseToBank();
		assertEquals(bank.getInventoryOfHouses(), checkHouses+1);
	}
	
	@Test
	public void testAddHouseWhenOutOfStock(){
		int startVal = bank.getInventoryOfHouses();
		for(int i=0; i<startVal; i++){
			bank.removeHouseFromBank();
		}
		
		int checkNumHouses = bank.getInventoryOfHouses();
		
		bank.addHouseToBank();
		
		assertEquals(bank.getInventoryOfHouses(), checkNumHouses+1);
		assertTrue(bank.getHasHouses());
	}

}
