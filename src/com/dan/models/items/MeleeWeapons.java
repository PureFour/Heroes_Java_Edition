package com.dan.models.items;

import static com.dan.utils.FunctionHelper.getChance;
import static com.dan.utils.FunctionHelper.getRandomNumber;
import static com.dan.utils.JsonParser.getItemsBonus;
import static com.dan.utils.JsonParser.getItemsNames;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.dan.models.characters.Hero;
import com.dan.modelsAbstract.Item;
import lombok.Builder;
import lombok.Getter;

public class MeleeWeapons extends Item {
	//stats
	@Getter
	byte ad;

	@Builder
	public MeleeWeapons(int id, String name, int buyValue, int ad) {
		super(id, name, buyValue);
		this.ad = (byte) ad;
	}

	public MeleeWeapons() {
	}

	public void show() {
		System.out.println(this);
		System.out.println("AD " + this.ad);
	}

	public List<Item> init(int offset, Hero hero) throws IOException {
		ArrayList<Item> list = new ArrayList<>();
		List<String> swordNames = getItemsNames("swords");
		IntStream.range(offset, 9)
			.forEach(i ->
				list.add(new MeleeWeaponsBuilder()
					.id(i)
					.name(swordNames.get(i))
					.buyValue(4 * i + 5 * hero.getLvl())
					.ad(i + 5 + getRandomNumber((byte) i) + 3 * hero.getLvl())
					.build()
				));
		list.stream()
			.filter(item -> getChance(item.getBuyValue()))
			.forEach(item -> {
				try {
					item.setBonus(getItemsBonus("swords"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

		return list;
	}
}
