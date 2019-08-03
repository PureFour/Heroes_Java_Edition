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

@Getter
public class Shields extends Item {

	private byte def;
	private short maxHp;

	public Shields() {
	}

	@Builder
	public Shields(int id, String name, int buyValue, int def, int maxHp) {
		super(id, name, buyValue);
		this.def = (byte) def;
		this.maxHp = (short) maxHp;
	}

	@Override
	public void show() {
		System.out.println(this);
		System.out.println("DEF " + this.def);
		System.out.println("MAX HP " + this.maxHp);
	}

	public List<Item> init(int offset) throws IOException {
		ArrayList<Item> list = new ArrayList<>();
		List<String> shieldsNames = getItemsNames("shields");
		IntStream.range(1, shieldsNames.size())
			.forEach(i -> list.add(new ShieldsBuilder()
				.id(i + offset)
				.name(shieldsNames.get(i))
				.buyValue(3 * i)
				.def(1 + getRandomNumber((byte) i))
				.maxHp(15 * getRandomNumber((byte) i) + 5)
				.build()
			));
		setBonus(list, "shields");
		return list;
	}
}
