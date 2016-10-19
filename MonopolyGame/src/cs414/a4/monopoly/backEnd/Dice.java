package cs414.a4.monopoly.backEnd;

import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;

public class Dice {
	private Random r;
	private Calendar calendar;
	long seconds;

	public Dice() {
		calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		seconds = calendar.getTimeInMillis() / 1000L;
		r = new Random(seconds);
	}
	
	public int rollDie(){
		int die = r.nextInt(6)+1;
		return die;
	}
}
