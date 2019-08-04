package com.dan.utils;

import static com.dan.utils.GameSaver.changeHeroMenu;

import java.io.IOException;

import com.dan.models.characters.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameSaverTest {
	Warrior warrior;
	Warrior warrior2;

	@BeforeEach
	void setUp() {
		warrior = new Warrior("dummy");
		warrior.setGold((short) 0);
		warrior.setHp((short) 0);
		warrior.setName("danek");
		warrior2 = new Warrior("cwel");
		warrior2.setGold((short) 10);
	}

	@Test
	void saveTest() {
		GameSaver.saveCharacter(warrior);
	}

	@Test
	void loadTest() throws IOException {
//		warrior2 = (Warrior) GameSaver.loadCharacter("daniel");
//		System.out.println(warrior2.getName());
	}

	@Test
	void changeHeroMenuTest() {
		changeHeroMenu();
	}
}


