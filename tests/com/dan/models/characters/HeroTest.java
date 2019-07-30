package com.dan.models.characters;

import com.dan.models.items.Boots;
import com.dan.models.items.MeleeWeapons;
import com.dan.modelsAbstract.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class
HeroTest {

	Warrior warrior;
	Boots boots;
	MeleeWeapons sword;

	@BeforeEach
	void setUp() {
		warrior = new Warrior("dummy");
		boots = new Boots(1, "boots", 10, 6);
		sword = new MeleeWeapons(1, "sword", 10, 8);
	}

	@Test
	void equipTest() {
		int ad = warrior.getAd();
		int dex = warrior.getDexterity();
		warrior.equip(boots, true);
		warrior.equip(sword, true);
		Assertions.assertEquals(warrior.getAd(), sword.getAd() + ad);
		Assertions.assertEquals(warrior.getDexterity(), boots.getDexterity() + dex);
	}
}