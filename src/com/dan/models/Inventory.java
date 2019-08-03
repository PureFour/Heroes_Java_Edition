package com.dan.models;

import java.io.Serializable;
import java.util.HashMap;

import com.dan.modelsAbstract.Item;
import com.dan.modelsAbstract.ItemStorage;
import lombok.Getter;

public class Inventory implements ItemStorage, Serializable {
	private static final long serialVersionUID = -1892561327013038124L;

	@Getter
	private HashMap<Byte, Item> storage;
	private byte maxSize;

	public Inventory() {
	}

	public Inventory(int maxSize) {
		this.storage = new HashMap<>(maxSize); //ciekawe czy potem mozna powiekszyc
		this.maxSize = (byte) maxSize;
	}

	@Override
	public byte getMaxSize() {
		return this.maxSize;
	}
}
