package com.dan.models;

import com.dan.models.characters.Warrior;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest {
	Game game;
	Warrior warrior;
	@BeforeEach
	void setUp() {
		game = new Game();
		warrior = new Warrior("dummy");
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void showInvTest() {
	}
}