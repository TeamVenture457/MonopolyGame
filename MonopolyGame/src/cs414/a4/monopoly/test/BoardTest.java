package cs414.a4.monopoly.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a4.monopoly.backEnd.*;

public class BoardTest {

	Player [] testPlayers;
	@Before
	public void setUp() throws Exception {
		testPlayers = new Player[4];
		testPlayers[0] = new Player();
		testPlayers[1] = new Player();
		testPlayers[2] = new Player();
		testPlayers[3] = new Player();
		Board testBoard = new Board(testPlayers);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetXMLDocGoodFilename() {
		
	}
	
	@Test
	public void testGetXMLDocBadFilename() {
		fail("Not yet implemented");
	}

}
