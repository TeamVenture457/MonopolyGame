package cs414.a4.monopoly.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a4.monopoly.backEnd.*;

public class GameTest {
	
	Game game;
	Board board;
	List<String> playerNames;

	@Before
	public void setUp() throws Exception {
		playerNames = new ArrayList<String>();
		playerNames.add("Joe");
		playerNames.add("Alex");
		playerNames.add("Sarah");
		playerNames.add("Ralph");
		game = new Game(playerNames);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetup() {
		board = game.setup();
		Space [] boardSpaces = board.getBoardSpaces();
		String actualSpace0Name = boardSpaces[0].getName();
		String expectedSpace0Name = "Go";
		assertEquals(expectedSpace0Name, actualSpace0Name);
		String actualPlayer0Name = board.getPlayers().get(0).getName();
		String expectedPlayer0Name = game.getPlayers().get(0).getName();
		assertEquals(expectedPlayer0Name, actualPlayer0Name);
	}
	
	@Test
	public void testSetPlayerTokensInOrder() {
		
		playerNames = game.getPlayerNamesInOrder();
		List<String> playerTokens = new ArrayList<String>();
		playerTokens.add("shoe");
		playerTokens.add("car");
		playerTokens.add("iron");
		playerTokens.add("hat");
		game.setPlayerTokensInOrder(playerTokens);
		String expectedToken = playerTokens.get(1);
		String actualToken = game.getPlayers().get(1).getToken();
		assertEquals(expectedToken, actualToken);
	}

}
