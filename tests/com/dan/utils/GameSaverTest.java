package com.dan.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import com.dan.models.characters.Hero;
import com.dan.models.characters.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameSaverTest {
	Warrior warrior;
	Hero hero;

	@BeforeEach
	void setUp() {
		warrior = new Warrior("dummy");
	}

	@Test
	void saveTest() {
		GameSaver.saveCharacterAsJSON(warrior);
	}

	@Test
	void loadTest() throws IOException {
		GameSaver.loadCharacterAsJSON(hero, "daniel");
		System.out.println("Hero name -> " + hero.getName());
	}
}


