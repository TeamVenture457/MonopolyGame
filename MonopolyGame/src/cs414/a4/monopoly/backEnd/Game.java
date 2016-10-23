package cs414.a4.monopoly.backEnd;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Game {
	
	List<Player> players;
	Board board;
	List<String> tokens;

	public Game(String [] playerNames) {
		players = new ArrayList<Player>();
		for(String name : playerNames){
			players.add(new Player(name));
		}
		Collections.shuffle(players);
	}
	
	public static void main (String [] args){
		String [] playerNames = {"Joe", "Alex", "Sarah", "Ralph"};
		Game game = new Game(playerNames);
	}

}
