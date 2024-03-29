package com.dan.models.characters;

import static com.dan.models.Game.WIDTH;
import static com.dan.utils.FunctionHelper.getOption;
import static com.dan.utils.FunctionHelper.getRandomNumber;
import static com.dan.utils.FunctionHelper.randomizeAttribute;

import com.dan.modelsAbstract.Fight;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Enemy implements Fight<Hero>, Cloneable {
	//static arrays with sample enemies
	public final static Enemy[] EASY_ENEMIES = { //public for tests only ...
		new Enemy("Dog", (short) 10, (byte) 50, (byte) 5, (byte) 1, (byte) 1, (short) 10, (short) 10),
		new Enemy("Swarm of wasp", (short) 200, (byte) 8, (byte) 6, (byte) 2, (byte) 1, (short) 15, (short) 15),
		new Enemy("Bat", (short) 10, (byte) 50, (byte) 5, (byte) 1, (byte) 3, (short) 20, (short) 15),
	};
	private final static Enemy[] MEDIUM_ENEMIES = {
		new Enemy("Orc", (short) 30, (byte) 10, (byte) 8, (byte) 3, (byte) 1, (short) 10, (short) 10),
		new Enemy("Great Knight", (short) 40, (byte) 12, (byte) 8, (byte) 2, (byte) 3, (short) 15, (short) 15),
		new Enemy("Paladin", (short) 50, (byte) 5, (byte) 11, (byte) 1, (byte) 5, (short) 20, (short) 15),
	};
	private final static Enemy[] HARD_ENEMIES = {
		new Enemy("Black Elf", (short) 10, (byte) 5, (byte) 5, (byte) 1, (byte) 1, (short) 10, (short) 10),
		new Enemy("Big Lizard", (short) 20, (byte) 8, (byte) 6, (byte) 2, (byte) 1, (short) 15, (short) 15),
		new Enemy("King of monkeys", (short) 10, (byte) 5, (byte) 5, (byte) 1, (byte) 3, (short) 20, (short) 15),
	};
	private final static Enemy[] BOSS_ENEMIES = {
		new Enemy("Freakin Dog!", (short) 10, (byte) 5, (byte) 5, (byte) 1, (byte) 1, (short) 10, (short) 10),
		new Enemy("BIG Black Wolf!", (short) 20, (byte) 8, (byte) 6, (byte) 2, (byte) 1, (short) 15, (short) 15),
		new Enemy("RED Bat!", (short) 10, (byte) 5, (byte) 5, (byte) 1, (byte) 3, (short) 20, (short) 15),
	};
	//basic stats
	private String name;
	@Setter
	private short hp;
	private byte ad;
	private byte def;
	private byte lvl;
	private byte luck;
	private short expBounty;
	private short goldBounty;

	public Enemy() {
	}

//	@Override
//	protected Object clone() throws CloneNotSupportedException {
//		return super.clone();
//	}

	private Enemy(String name, short hp, byte ad, byte def, byte lvl, byte luck, short expBounty, short goldBounty) {
		this.name = name;
		this.hp = hp;
		this.ad = ad;
		this.def = def;
		this.lvl = lvl;
		this.luck = luck;
		this.expBounty = expBounty;
		this.goldBounty = goldBounty;
	}

	public Enemy spawnEnemy() throws CloneNotSupportedException { //TODO trzeba zwracac kopie obiektow...
		System.out.println("Which lvl?");
		System.out.println("(1)Easy");
		System.out.println("(2)Medium");
		System.out.println("(3)Hard");
		System.out.println("(4)BOSS");
		int index = getRandomNumber((byte) 2);
		switch (getOption(4)) {
			case 1:
				return (Enemy) EASY_ENEMIES[index].clone();
			case 2:
				return (Enemy) MEDIUM_ENEMIES[index].clone();
			case 3:
				return (Enemy) HARD_ENEMIES[index].clone();
			case 4:
				return (Enemy) BOSS_ENEMIES[index].clone();
			default:
				break;
		}
		return null;
	}

	@Override
	public void attack(Hero hero) {
		byte damage, defence, attack;
		short HP = hero.getHp();
		int var = 60;
		defence = randomizeAttribute(hero.getDef());
		attack = randomizeAttribute(this.ad);
		System.out.println(" ".repeat(var) + this.name + " is attacking you!");
//		System.out.println(" ".repeat(var) + "My DEF = " + defence);
		if (attack > defence) {
			damage = (byte) (attack - defence);
			if (isCritical(luck)) {
				damage *= 2;
				System.out.println(" ".repeat(var) + "Critical HIT!: " + damage);
			} else {
				System.out.println(" ".repeat(var) + "Enemy DMG= " + damage);
			}
			if (damage < HP) {
				hero.setHp((byte) (HP - damage));
			} else {
				hero.setHp((byte) 0);
			}
		} else {
			System.out.println(" ".repeat(var) + name + " missed!");
		}
	}
}
