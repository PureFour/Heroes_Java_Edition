package com.dan.models.characters;

import com.dan.models.items.Boots;
import com.dan.models.items.MeleeWeapons;
import com.dan.models.items.Shields;
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
	Shields shield;

	// basic stats
//
//		this.hp = this.maxHp = 100;
//		this.mana = this.maxMana = 0;
//		this.ad = 12;
//		this.ap = 0;
//		this.def = 10;
//
//		this.strength = 6;        // 1 = 2 AD
//		this.vitality = 6;        // 1 = 25 HP
//		this.dexterity = 4;     // 1 = 3 DEF
//		this.intelligence = 0;  // 1 = 25 MANA
//		this.luck = 2;          // 1 = 10% CriticalChance
	@BeforeEach
	void setUp() {
		warrior = new Warrior("dummy");
		boots = new Boots(1, "boots", 10, 6);
		sword = new MeleeWeapons(2, "sword", 10, 8);
		sword2 = new MeleeWeapons(3, "sword2", 15, 14);
		shield = new Shields(4, "shield", 15, 14, 50);
		shield.setBonus(new Item.Bonus("sucker", "strength", (byte) 5));
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
		warrior.equip(shield);
		Assertions.assertEquals(warrior.getDef(), 24); //10+14=24 DEF
	}

	@Test
	void equipBonusTest() {
		warrior.equip(shield);
		Assertions.assertEquals(warrior.getStrength(), 11); //bonus 5
	}
}