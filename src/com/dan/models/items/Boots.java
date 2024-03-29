package com.dan.models.items;

import static com.dan.models.Game.WIDTH;
import static com.dan.utils.FunctionHelper.getRandomNumber;
import static com.dan.utils.JsonParser.getItemsNames;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.dan.modelsAbstract.Item;
import lombok.Builder;
import lombok.Getter;

public class Boots extends Item {

	@Getter
	private byte dexterity;

	public Boots() {
	}

	@Builder
	public Boots(int id, String name, int buyValue, int dex) {
		super(id, name, 0, buyValue);
		this.dexterity = (byte) dex;
	}

	@Override
	public void show() {
		System.out.println(this);
		System.out.format("%-50s%n", " ".repeat(50) + "DEX +" + this.dexterity);
		System.out.println("=".repeat(WIDTH) + '\n');
	}

	public List<Item> init(int offset) throws IOException {
		ArrayList<Item> list = new ArrayList<>();
		List<String> bootsNames = getItemsNames("boots");
		IntStream.range(1, bootsNames.size())
			.forEach(i -> list.add(new BootsBuilder()
				.id(i + offset)
				.name(bootsNames.get(i))
				.buyValue(3 * i)
				.dex(1 + getRandomNumber((byte) i))
				.build()
			));
		setBonus(list, "boots");
		return list;
	}
}
