package com.dan.modelsAbstract;

import java.io.Serializable;

import lombok.Getter;

@Getter
public abstract class Item implements Serializable {
	private static final long serialVersionUID = -123L;

	private byte id = 0;
	private short buyValue;
	private short sellValue;
	private String name = "unknown";

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
				"\nItem price: " + getBuyValue();
	}

}
