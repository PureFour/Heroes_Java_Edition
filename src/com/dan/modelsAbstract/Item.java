package com.dan.modelsAbstract;

import static com.dan.utils.FunctionHelper.getChance;
import static com.dan.utils.JsonParser.getItemsBonus;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Item implements Serializable {
	protected static final long serialVersionUID = -123L;

	private byte id = 0;
	private String name = "unknown";
	private short buyValue;
	private short sellValue;
	@Setter
	private Bonus bonus;

	@Getter
	public static class Bonus implements Serializable {
		private String name;
		private String field;
		private byte value;

		public Bonus() {
		}

		@Builder
		public Bonus(String name, String field, byte value) {
			this.name = name;
			this.field = field;
			this.value = value;
		}

		@Override
		public String toString() {
			return name + " (" + field + " + " + value + ')';
		}
	}

	protected void setBonus(ArrayList<Item> list, String itemsName) {
		list.stream()
			.filter(item -> getChance(item.getBuyValue()))
			.forEach(item -> {
				try {
					item.setBonus(getItemsBonus(itemsName));
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
	}
	public Item() {
	}

	public Item(int id, String name, int buyValue) {
		this.id = (byte) id;
		this.name = name;
		this.buyValue = (short) buyValue;
		this.sellValue = (short) (buyValue / 2);
	}

	public abstract void show();

	@Override
	public String toString() {
		return
			"=".repeat(50) + '\n' +
				"Item id: " + getId() +
				"\nItem name: " + getName() +
				(bonus != null ? "\nBONUS: " + getBonus() : "") +
				"\nItem price: " + getBuyValue();
	}
}
