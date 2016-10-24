package cs414.a4.monopoly.test;

import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a4.monopoly.backEnd.*;

public class DiceTest {
	
	private Dice d1;
	private int die;
	private boolean result;
	
	@Before
	public void setUp() throws Exception {
		d1 = new Dice();
	}

	@After
	public void tearDown() throws Exception {
		d1 = null;
	}

	@Test
	public void diceValues1to6Test() {
		for(int i = 0; i < 100; i++){
			d1.rollDice();
			die = d1.getDie1();
			if(die < 1 || die > 6){
				result = false;
			}
			else{
				result = true;
			}
			assertTrue(result);
		}
	}
}
