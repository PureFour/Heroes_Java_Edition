package com.dan.models.characters;

import static com.dan.utils.FunctionHelper.getOption;
import static java.lang.Math.pow;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import com.dan.models.Inventory;
import com.dan.modelsAbstract.Fight;
import com.dan.modelsAbstract.Item;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Hero implements Fight<Enemy>, Serializable {
	private static final long SERIAL_VERSION_UID = 1L;
	//stats
	byte x;
	byte y;
	@Setter
	short gold;
	//basic stats
	String name;
	@Setter
	short hp;
	short maxHp;
	byte mana;
	byte maxMana;
	byte ad;
	byte ap; //magic DMG
	byte def;
	byte lvl;
	@Setter
	short exp;
	short expToNextLvl;
	//
	byte strength;
	byte vitality;
	byte dexterity;
	byte intelligence;
	byte luck;

	Inventory inventory = new Inventory(5); //hero backpack
	Inventory equipment = new Inventory(5); //hero equipment

	@JsonCreator
	Hero() {
	}

	@JsonCreator
	Hero(String name) {
		this.name = name;
		this.x = 0;
		this.y = 0;
		this.gold = 0;
		this.lvl = 1;
		this.exp = 0;
		this.expToNextLvl =
			(short) ((50 / 3) * (pow(this.lvl, 3.0)) - (6.0 * pow(this.lvl, 3.0)) + (17.0 * this.lvl) - 11.0); //LVL^UP FORMULA
	}

	//methods
	public void levelUp() {
		if (this.exp < this.expToNextLvl) {
			System.out.println("You don't have enough experience!");
		} else {
			while (this.exp > this.expToNextLvl) {
				this.lvl += 1;
				this.exp -= this.expToNextLvl;
				this.expToNextLvl =
					(short) ((50 / 3) * (pow(this.lvl, 3.0)) - (6.0 * pow(this.lvl, 3.0)) + (17.0 * this.lvl) - 11.0);

				System.out.println("You can add points to stats!");
				System.out.println("(1)Strength: " + this.strength + "[1 = 2 AD]");
				System.out.println("(2)Vitality: " + this.vitality + "[1 = 25 HP]");
				System.out.println("(3)Dexterity: " + this.dexterity + "[1 = 3 DEF]");
				System.out.println("(4)Intelligence: " + this.intelligence + "[1 = 25 MANA]");
				System.out.println("-".repeat(77));
				switch (getOption(4)) {
					case 1:
						this.strength += 1;
						this.ad += 2;
						break;
					case 2:
						this.vitality += 1;
						this.maxHp += 25;
						break;
					case 3:
						this.dexterity += 1;
						this.def += 3;
					case 4:
						this.intelligence += 1;
						this.maxMana += 25;
						break;
					default:
						break;
				}
			}
			System.out.println("LVL UP!\nYOUR LV IS " + this.getLvl() + " NOW!");
		}
	}

	public void equip(Item item, boolean equipping) { //TODO ogarnac!
		if (equipping) {
			System.out.println("Equipping an item ...");
			if (this.equipment.hasItemType(item)) {

			} else {
				this.equipment.addItem(item);
				this.inventory.removeItem(item.getId());
				//stats UP
				Class<?> itemClass = item.getClass();
				Class<?> heroClass = Hero.class;
				Field[] itemFields = itemClass.getDeclaredFields();
				List.of(itemFields).forEach(itemField -> {
						try {
							itemField.setAccessible(true);
							Field heroAd = heroClass.getDeclaredField(itemField.getName());
							heroAd.setAccessible(true);
							Byte value = (byte) (itemField.getByte(item) + heroAd.getByte(this));
							heroAd.set(this, value);
						} catch (NoSuchFieldException | IllegalAccessException s) {
							s.printStackTrace();
						}
					}
				);
				System.out.println("Hero AD: " + this.ad);
				System.out.println("Hero DEX: " + this.dexterity);
			}
		} else {
			System.out.println("Taking off an item ...");
		}
	}
}
