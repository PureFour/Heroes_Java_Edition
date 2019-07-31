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
	MeleeWeapons sword2;

	@BeforeEach
	void setUp() {
		warrior = new Warrior("dummy");
		boots = new Boots(1, "boots", 10, 6);
		sword = new MeleeWeapons(1, "sword", 10, 8);
		sword2 = new MeleeWeapons(2, "sword2", 15, 14);
	}

	@Test
	void equipTest() {
		int ad = warrior.getAd();
		int dex = warrior.getDexterity();
		warrior.equip(sword);
		warrior.equip(boots);
		Assertions.assertEquals(warrior.getAd(), sword.getAd() + ad);
		Assertions.assertEquals(warrior.getDexterity(), boots.getDexterity() + dex);
	}

	@Test
	void equipTest2() {
		warrior.equip(sword);
		Assertions.assertEquals(warrior.getAd(), 20); //12+8=20 AD
		warrior.equip(sword2);
		Assertions.assertEquals(warrior.getAd(), 26); //12+14=26 AD
	}
}