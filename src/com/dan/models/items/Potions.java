package com.dan.models.items;

import static com.dan.models.Game.WIDTH;

import java.util.ArrayList;
import java.util.List;

import com.dan.modelsAbstract.Item;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Potions extends Item {

	private short hp;
	private short mana;

	public Potions() {
	}

	@Builder
	public Potions(int id, String name, int buyValue, int hp, int mana) {
		super(id, name, 0, buyValue);
		this.hp = (byte) hp;
		this.mana = (short) mana;
	}

	@Override
	public void show() {
		System.out.println(this);
		if (this.hp > 0) {
			System.out.format("%-50s%n", " ".repeat(50) + "HP +" + this.hp);
		} else {
			System.out.format("%-50s%n", " ".repeat(50) + "MANA +" + this.mana);
		}
		System.out.println("=".repeat(WIDTH) + '\n');
	}

	public List<Item> init() {
		ArrayList<Item> list = new ArrayList<>(List.of(new PotionsBuilder()
				.id(50)
				.name("Red Potion")
				.buyValue(50)
				.hp(50)
				.build(),
			new PotionsBuilder()
				.id(51)
				.name("Blue Potion")
				.buyValue(50)
				.mana(50)
				.build()));
		return list;
	}
}
