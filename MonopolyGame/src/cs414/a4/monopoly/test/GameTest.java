package cs414.a4.monopoly.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a4.monopoly.backEnd.*;

public class GameTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetPlayerTokensInOrder() {
		List<String> playerNames = new ArrayList<String>();
		playerNames.add("Joe");
		playerNames.add("Alex");
		playerNames.add("Sarah");
		playerNames.add("Ralph");
		Game game = new Game(playerNames);
		playerNames = game.getPlayerNamesInOrder();
		List<String> playerTokens = new ArrayList<String>();
		playerTokens.add("shoe");
		playerTokens.add("car");
		playerTokens.add("iron");
		playerTokens.add("hat");
		game.setPlayerTokensInOrder(playerTokens);
		String player = game.getPlayerNamesInOrder().get(1);
		String expectedToken = playerTokens.get(1);
		String actualToken = game.getPlayers().get(1).getToken();
		assertEquals(expectedToken, actualToken);
	}

}
