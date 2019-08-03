package com.dan.models.items;

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
		super(id, name, buyValue);
		this.hp = (byte) hp;
		this.mana = (short) mana;
	}

	@Override
	public void show() {
		System.out.println(this);
		System.out.println("HP " + this.hp);
		System.out.println("MANA " + this.mana);
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
