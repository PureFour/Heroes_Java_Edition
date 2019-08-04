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

@Getter
public class Helmets extends Item {

	private byte def;
	private short vitality;

	public Helmets() {
	}

	@Builder
	public Helmets(int id, String name, int buyValue, int def, int vitality) {
		super(id, name, 0, buyValue);
		this.def = (byte) def;
		this.vitality = (short) vitality;
	}

	@Override
	public void show() {
		System.out.println(this);
		System.out.format("%-50s%n", " ".repeat(50) + "DEF +" + this.def);
		System.out.format("%-50s%n", " ".repeat(50) + "VIT +" + this.vitality);
		System.out.println("=".repeat(WIDTH) + '\n');
	}

	public List<Item> init(int offset) throws IOException {
		ArrayList<Item> list = new ArrayList<>();
		List<String> helmetsNames = getItemsNames("helmets");
		IntStream.range(1, helmetsNames.size())
			.forEach(i -> list.add(new HelmetsBuilder()
				.id(i + offset)
				.name(helmetsNames.get(i))
				.buyValue(3 * i)
				.def(1 + getRandomNumber((byte) i))
				.vitality(2 + getRandomNumber((byte) i))
				.build()
			));
		setBonus(list, "helmets");
		return list;
	}
}
