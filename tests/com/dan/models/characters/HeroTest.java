package com.dan.models.characters;

import com.dan.models.items.MeleeWeapons;
import com.dan.modelsAbstract.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HeroTest {

	Warrior warrior;
	Item item;

	@BeforeEach
	void setUp() {
		warrior = new Warrior("dummy");
		item = new MeleeWeapons(1, "sword", 10, 5);
	}

	@Test
	void equipTest() {
		warrior.equip(item, true);
	}
}