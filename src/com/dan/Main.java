package com.dan;

import com.dan.models.Game;

public class Main {
//	public static Random random = new Random(); //moze nie trzeba ? >>

	public static void main(String[] args) {
		final Game game = new Game();
		System.out.println("Game started!");
		game.run();
	}
}
