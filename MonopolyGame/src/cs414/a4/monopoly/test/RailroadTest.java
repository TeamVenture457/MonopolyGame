package cs414.a4.monopoly.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a4.monopoly.backEnd.*;

public class RailroadTest {
	
	Railroad rail;
	Bank bank;

	@Before
	public void setUp() throws Exception{
		bank = new Bank();
	}

	@After
	public void tearDown() throws Exception{
	}
	
	@Test
	public void testDescription(){
		rail = new Railroad("Test", 300, 25, 150, bank);
		String expectedDesc = "Railroad\n" + "Test\n" + "Price: 300\n" + "Rent: 25\n" + "Mortgage: 150";
		assertEquals(rail.getDescription(), expectedDesc);
	}

}
