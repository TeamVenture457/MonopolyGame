package cs414.a4.monopoly.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a4.monopoly.backEnd.*;

public class StreetTest {

	Street street;
	Bank bank;
	
	@Before
	public void setUp() throws Exception{
		bank = new Bank();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDescription(){
		street = new Street("Test", 300, 25, 150, bank);
		String expectedDesc = "Street\n" + "Test\n" + "Price: 300\n" + "Rent: 25\n" + "Mortgage: 150";
		assertEquals(street.getDescription(), expectedDesc);
	}
	
	@Test
	public void testPlaceHouseAble(){
		street = new Street("Test", 300, 25, 150, bank);
		
		boolean check = street.placeHouse();
		
		assertTrue(check);
		assertEquals(street.getNumHouses(), 1);
	}
	
	@Test
	public void testPlaceTooManyHouses(){
		street = new Street("Test", 300, 25, 150, bank);
		
		for(int i=0; i<4; i++){
			street.placeHouse();
		}
		boolean check = street.placeHouse();
		
		assertFalse(check);
		assertEquals(street.getNumHouses(), 4);
	}
	
	@Test
	public void testPlaceHouseOnHotel(){
		street = new Street("Test", 300, 25, 150, bank);
		
		for(int i=0; i<4; i++){
			street.placeHouse();
		}
		street.placeHotel();
		
		boolean check = street.placeHouse();
		
		assertFalse(check);
		assertEquals(street.getNumHouses(), 0);
	}
	
	@Test
	public void testPlaceHotelAble(){
		street = new Street("Test", 300, 25, 150, bank);
		
		for(int i=0; i<4; i++){
			street.placeHouse();
		}
		
		boolean check = street.placeHotel();
		
		assertTrue(check);
		assertTrue(street.hasHotel());
		assertEquals(street.getNumHouses(), 0);
	}
	
	@Test
	public void testPlaceHotelNoHouses(){
		street = new Street("Test", 300, 25, 150, bank);
		
		boolean check = street.placeHotel();
		
		assertFalse(check);
		assertFalse(street.hasHotel());
	}
	
	@Test
	public void testPlaceHotelSomeHouses(){
		street = new Street("Test", 300, 25, 150, bank);
		
		for(int i=0; i<3; i++){
			street.placeHouse();
		}
		
		boolean check = street.placeHotel();
		
		assertFalse(check);
		assertFalse(street.hasHotel());
	}
	
	@Test
	public void testPlaceHotelOnHotel(){
		street = new Street("Test", 300, 25, 150, bank);
		
		for(int i=0; i<4; i++){
			street.placeHouse();
		}
		street.placeHotel();
		
		boolean check = street.placeHotel();
		
		assertFalse(check);
	}
	
	@Test
	public void testRemoveHouseAble(){
		street = new Street("Test", 300, 25, 150, bank);
		street.placeHouse();
		
		boolean check = street.removeHouse();
		
		assertTrue(check);
		assertEquals(street.getNumHouses(), 0);
	}
	
	@Test
	public void testRemoveHouseFromSeveral(){
		street = new Street("Test", 300, 25, 150, bank);
		street.placeHouse();
		street.placeHouse();
		
		boolean check = street.removeHouse();
		
		assertTrue(check);
		assertEquals(street.getNumHouses(), 1);
	}
	
	@Test
	public void testRemoveHouseUnable(){
		street = new Street("Test", 300, 25, 150, bank);
		
		boolean check = street.removeHouse();
		
		assertFalse(check);
		assertEquals(street.getNumHouses(), 0);
	}
	
	@Test
	public void testRemoveHotelAble(){
		street = new Street("Test", 300, 25, 150, bank);
		
		for(int i=0; i<4; i++){
			street.placeHouse();
		}
		street.placeHotel();
		
		boolean check = street.removeHotel();
		
		assertTrue(check);
		assertFalse(street.hasHotel());
	}
	
	@Test
	public void testRemoveHotelUnable(){
		street = new Street("Test", 300, 25, 150, bank);
		
		street.placeHotel();
		
		boolean check = street.removeHotel();
		
		assertFalse(check);
		assertFalse(street.hasHotel());
	}
	
	@Test
	public void testMortgageTrue(){
		street = new Street("Test", 300, 25, 150, bank);
		
		boolean check = street.mortgage();
		
		assertTrue(check);
		assertTrue(street.isMortgaged());
	}
	
	@Test
	public void testMortgageFalse(){
		street = new Street("Test", 300, 25, 150, bank);
		street.placeHouse();
		
		boolean check = street.mortgage();
		
		assertFalse(check);
		assertFalse(street.isMortgaged());
	}

}
