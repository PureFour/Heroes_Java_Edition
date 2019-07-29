package com.dan.models.items;

import static com.dan.utils.FunctionHelper.getRandomNumber;
import static com.dan.utils.JsonParser.getItemsNames;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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
	}

	public List<Item> init(int offset) {
		ArrayList<Item> list = new ArrayList<>();
		try {
			List<String> swordNames = getItemsNames("swords");
			IntStream.range(1 + offset, swordNames.size())
//				.forEach(i -> list.add(new MeleeWeapons(i, swordNames.get(i), i + getRandomNumber((byte) i))));
				.forEach(i -> list.add(new MeleeWeaponsBuilder()
					.id(i)
					.name(swordNames.get(i))
					.buyValue(5 * i)
					.ad(getRandomNumber((byte) i) + 2)
					.build()
				));

		} catch (IOException e) {
			e.printStackTrace();
		}
//		list.forEach(System.out::println);
		return list;
	}
}
