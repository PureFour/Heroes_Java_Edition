package com.dan.modelsAbstract;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Item implements Serializable {
	private static final long serialVersionUID = -123L;

	private byte id = 0;
	private String name = "unknown";
	private short buyValue;
	private short sellValue;
	@Setter
	private Bonus bonus;

	@Getter
	public static class Bonus {
		private String name;
		private String field;
		private byte value;

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
