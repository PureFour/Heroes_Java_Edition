package com.dan.models;

import static com.dan.models.Game.WIDTH;
import static com.dan.utils.FunctionHelper.getOption;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

import com.dan.models.characters.Hero;
import com.dan.models.items.Boots;
import com.dan.models.items.Helmets;
import com.dan.models.items.MeleeWeapons;
import com.dan.models.items.Potions;
import com.dan.models.items.Shields;
import com.dan.modelsAbstract.Item;
import com.dan.modelsAbstract.ItemStorage;
import lombok.Getter;

public class Shop implements ItemStorage {
	@Getter
	private HashMap<Byte, Item> storage;

	Shop() {
		this.storage = new HashMap<>();
	}

	public void menu(Hero hero) {
		boolean running = true;
		while (running) {
			System.out.println("=".repeat(WIDTH));
			System.out.println(" ".repeat(WIDTH / 2 - 9) + "Welcome in my shop!");
			System.out.println("=".repeat(WIDTH));
			System.out.println("What do you want? YOUR GOLD: " + hero.getGold());
			System.out.println("(1) BUY ITEMS");
			System.out.println("(2) SELL ITEMS");
			System.out.println("(3) REFRESH ITEMS");
			System.out.println("(4) QUIT");
			switch (getOption(4)) {
				case 4:
					running = false;
					break;
				case 1:
					show(hero);
					break;
				case 2:
					sell(hero);
					break;
				case 3:
					refreshItems(hero);
					break;
			}
		}
	}

	public void show(Hero hero) {
		System.out.println("(1)Melee weapons");
		System.out.println("(2)Ranged weapons");
		System.out.println("(3)Magic weapons");
		System.out.println("(4)Helmets");
		System.out.println("(5)Armor");
		System.out.println("(6)Boots");
		System.out.println("(7)Shields");
		System.out.println("(8)Potions");
		System.out.println("(9)Arrows");
		System.out.println("(10)GO BACK");
		switch (getOption(10)) {
			case 1:
				getItemsByType(MeleeWeapons.class);
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				getItemsByType(Helmets.class);
				break;
			case 5:
				break;
			case 6:
				getItemsByType(Boots.class);
				break;
			case 7:
				break;
			case 8:
				getItemsByType(Potions.class);
				break;
			case 9:
				break;
			case 10:
				return;
			default:
				break;
		}
		buy(hero);
	}

	private void getItemsByType(Class type) {
		System.out.println(type.getName() + " :");
		this.storage.values().stream()
			.filter(type::isInstance)
			.forEach(Item::show);
	}

	private void buy(Hero hero) {
		System.out.println("What do you want to buy? (Enter index number)");
		int index = getOption(); // zmienione z getOptions(options) ! ! !
		Item tmp = getItem(index);
		if (tmp != null) {
			System.out.println("Item " + tmp.getName());
			System.out.println("Item prize: " + tmp.getBuyValue());
			System.out.println("Your gold: " + hero.getGold());
			System.out.println("Do you wanna buy it? (Y/N)");
			if (getOption("Y", "N") == 0) {
				if (hero.getGold() < tmp.getBuyValue()) {
					System.out.println("DONT BE STUPIDO!\nYOU don't enough money...");
				} else if (hero.getInventory().isFull()) {
					System.out.println("DONT BE STUPIDO!\nYOU don't enough space in eq...");
				} else {
					hero.getInventory().addItem(tmp);
					hero.setGold((short) (hero.getGold() - tmp.getBuyValue()));
					removeItem(index);
				}
			}
		}
	}

//	private Item getItem(Hero hero) {
//		int index = getOption(this.storage.size());
//		Item tmp = getItem(index);
//		if (tmp != null) {
//			System.out.println("Item " + tmp.getName());
//			System.out.println("Item prize: " + tmp.getBuyValue());
//			return tmp;
//		} else {
//			return null;
//		}
//	}

	private void sell(Hero hero) {
		System.out.println("Hero backpack");
		if (hero.getInventory().isEmpty()) {
			System.out.println("Inventory is empty !");
			return;
		} else {
			hero.getInventory().show();
		}
		System.out.println("What do you want to sell? (Enter index number)");
		int index = getOption();
		Item item = hero.getInventory().getItem(index);
		if (item != null) {
			System.out.println("Do you wanna sell it? (Y/N)");
			System.out.println("Your gold: " + hero.getGold());
			if (getOption("Y", "N") == 0) {
				hero.getInventory().removeItem(item.getId());
				hero.setGold((short) (hero.getGold() + item.getSellValue()));
			}
		}
	}

	public void initialize(Hero hero) { //TODO zrobic generowanie itemow D:
		System.out.println("Shop is initializing...");
		try {
			addItems(new MeleeWeapons().init(0, hero));
			addItems(new Boots().init(this.storage.size()));
			addItems(new Shields().init(this.storage.size()));
			addItems(new Helmets().init(this.storage.size()));

			addItems(new Potions().init());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void refreshItems(Hero hero) {
		System.out.println("Do you wanna see new items? (Y/N)");
		System.out.println("It will be costs 50 G");
		System.out.println("Your gold: " + hero.getGold());
		if (getOption("Y", "N") == 0) {
			hero.setGold((short) (hero.getGold() - 50));
			initialize(hero);
		}
	}

	@Override
	public byte getMaxSize() {
		return 40;
	}

}
