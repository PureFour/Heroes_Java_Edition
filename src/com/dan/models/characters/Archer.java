package com.dan.models.characters;

import com.dan.modelsAbstract.Fight;

public class Archer extends Hero implements Fight<Enemy> {

	public Archer(String name) {
		super(name);

		this.hp = this.maxHp = 50;
		this.mana = this.maxMana = 0;
		this.ad = 20;
		this.ap = 0;
		this.def = 5;

		this.strength = 2;        // 1 = 2 AD
		this.vitality = 3;        // 1 = 25 HP
		this.dexterity = 5;     // 1 = 3 DEF
		this.intelligence = 0;  // 1 = 25 MANA
		this.luck = 4;          // 1 = 10% CriticalChance

		System.out.println("Archer constructor works here...");
	}

	@Override
	public void attack(Enemy enemy) {

	}
}
