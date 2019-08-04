package com.dan.models.characters;

import static com.dan.utils.FunctionHelper.randomizeAttribute;

import com.dan.modelsAbstract.Fight;

public class Warrior extends Hero implements Fight<Enemy> {

	public Warrior(String name) {
		super(name);

		this.hp = this.maxHp = 100;
		this.mana = this.maxMana = 0;
		this.ad = 12;
		this.ap = 0;
		this.def = 10;

		this.strength = 6;        // 1 = 2 AD
		this.vitality = 6;        // 1 = 25 HP
		this.dexterity = 4;     // 1 = 3 DEF
		this.intelligence = 0;  // 1 = 25 MANA
		this.luck = 2;          // 1 = 10% CriticalChance

		System.out.println("Warrior constructor works here...");
	}

	@Override
	public void attack(Enemy enemy) {
		byte damage, defence, attack;
		short HP = enemy.getHp();

		defence = randomizeAttribute(enemy.getDef());
		attack = randomizeAttribute(this.ad);
		System.out.println(this.name + " (Warrior) is performing attack!");
//		System.out.println(enemy.getName() + " DEF = " + defence);
		if (attack > defence) {
			damage = (byte) (attack - defence);
			if (isCritical(luck)) {
				damage *= 2;
				System.out.println("Critical HIT!: " + damage);
			} else {
				System.out.println("Your DMG= " + damage);
			}
			if (damage < HP) {
				enemy.setHp((short) (HP - damage));
			} else {
				enemy.setHp((short) 0);
			}
		} else {
			System.out.println("You missed!");
		}
	}
}
