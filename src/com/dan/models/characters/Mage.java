package com.dan.models.characters;

import com.dan.modelsAbstract.Fight;

public class Mage extends Hero implements Fight<Enemy> {

	public Mage(String name) {
		super(name);

		this.hp = this.maxHp = 60;
		this.mana = this.maxMana = 100;
		this.ad = 2;
		this.ap = 5;
		this.def = 3;

		this.strength = 2;        // 1 = 2 AD
		this.vitality = 3;        // 1 = 25 HP
		this.dexterity = 5;     // 1 = 3 DEF
		this.intelligence = 6;  // 1 = 25 MANA
		this.luck = 0;          // 1 = 10% CriticalChance

		System.out.println("Mage constructor works here...");
	}
}
