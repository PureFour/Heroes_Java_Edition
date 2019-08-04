package com.dan.models;

import static com.dan.utils.FunctionHelper.clearScreen;
import static com.dan.utils.FunctionHelper.getOption;
import static com.dan.utils.FunctionHelper.randomizeAttribute;
import static com.dan.utils.GameSaver.changeHeroMenu;
import static com.dan.utils.GameSaver.loadCharacter;
import static com.dan.utils.GameSaver.saveCharacter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dan.models.characters.Archer;
import com.dan.models.characters.Enemy;
import com.dan.models.characters.Hero;
import com.dan.models.characters.Mage;
import com.dan.models.characters.Warrior;
import com.dan.models.items.Boots;
import com.dan.models.items.Potions;
import com.dan.modelsAbstract.Item;

public class Game implements Runnable {
	public static final int WIDTH = 100;

	private Hero hero;
	private Enemy enemy = new Enemy();
	private Shop shop = new Shop();
	private boolean running;

	public Game() {
		startMessage();
		this.hero.setGold((short) 1000);
		this.shop.initialize(this.hero);
		this.running = true;
	}

	@Override
	public void run() {
		System.out.println("Game is running!");
		while (this.running) {
			try {
				autoSave();
				mainMenu();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
	}

	private void autoSave() {
		saveCharacter(this.hero);
	}

	private void mainMenu() throws CloneNotSupportedException {
		System.out.println("*".repeat(WIDTH));
		System.out.println(" ".repeat(WIDTH / 2 - 5) + "-MAIN MENU-");
		System.out.println("*".repeat(WIDTH));
		int var = 20; //mozna nazwe zmienic ? !
		System.out.format("%-49s %c%n", " ".repeat(var) + "Name: " + this.hero.getName(), '|');
		System.out.format("%-49s %c%n", " ".repeat(var) + "Lvl: " + this.hero.getLvl(), '|');
		System.out.format("%-49s %c %-20s%n", " ".repeat(var) + "Exp: " + this.hero.getExp() + '/' + this.hero.getExpToNextLvl(), '|', " ".repeat(var) + "(1) FIGHT");
		System.out.format("%-49s %c %-20s%n", " ".repeat(var) + "GOLD: " + this.hero.getGold(), '|', " ".repeat(var) + "(2) Shop");
		System.out.format("%-49s %c %-20s%n", " ".repeat(var) + "HP: " + this.hero.getHp() + "/" + this.hero.getMaxHp(), '|', " ".repeat(var) + "(3) Inventory");
		System.out.format("%-49s %c %-20s%n", " ".repeat(var) + "MANA: " + this.hero.getMana() + "/" + this.hero.getMaxMana(), '|', " ".repeat(var) + "(4) Upgrade stuff");
		System.out.format("%-49s %c %-20s%n", " ".repeat(var) + "AD: " + this.hero.getAd(), '|', " ".repeat(var) + (hero.getExp() > hero.getExpToNextLvl() ? "(5) LvlUp!(Add Points)" : ""));
		System.out.format("%-49s %c %-20s%n", " ".repeat(var) + "AP: " + this.hero.getAp(), '|', " ".repeat(var) + "(6) Save Game");
		System.out.format("%-49s %c %-20s%n", " ".repeat(var) + "DEF: " + this.hero.getDef(), '|', " ".repeat(var) + "(7) Load Game");

		System.out.format("%-49s %c %-20s%n", " ".repeat(var) + "Strength: " + this.hero.getStrength(), '|', " ".repeat(var) + "(8) Exit Game");
		System.out.format("%-49s %c%n", " ".repeat(var) + "Vitality: " + this.hero.getVitality(), '|');
		System.out.format("%-49s %c%n", " ".repeat(var) + "Dexterity: " + this.hero.getDexterity(), '|');
		System.out.format("%-49s %c%n", " ".repeat(var) + "Intelligence: " + this.hero.getIntelligence(), '|');
		System.out.format("%-49s %c%n", " ".repeat(var) + "Luck: " + this.hero.getLuck(), '|');
		System.out.println("=".repeat(WIDTH));
		switch (getOption(8)) {
			case 1:
				enemy = enemy.spawnEnemy();
				fight();
				clearScreen();
				break;
			case 2:
				shop.menu(hero);
				clearScreen();
				break;
			case 3:
				showInventory(hero);
				clearScreen();
				break;
			case 4:
				break;
			case 5:
				hero.levelUp();
				clearScreen();
				break;
			case 6:
				saveCharacter(hero);
				break;
			case 7:
				this.hero = changeHeroMenu();
				break;
			case 8:
				this.running = false;
				break;
			default:
				break;
		}
	}

	private void startMessage() {
		System.out.println("=".repeat(WIDTH));
		System.out.println("\n\n" + " ".repeat(WIDTH / 2 - 3) + "HEROES\n\n");
		System.out.println("=".repeat(WIDTH));

		System.out.println("(1) New Game");
		System.out.println("(2) Load Game");
		System.out.println("(3) EXIT");

		switch (getOption(3)) {
			case 3:
				running = false;
				break;
			case 1:
				this.hero = initHero(getName());
				break;
			case 2:
				this.hero = changeHeroMenu();
				break;
		}
	}

	private Hero initHero(String name) {

		System.out.println(">".repeat(WIDTH / 2));
		System.out.println("Choose Hero Class!\n(1) Warrior\n(2) Archer\n(3) Mage");
		System.out.print("My choice:_\b");
		switch (getOption(3)) {
			case 1:
				return new Warrior(name);
			case 2:
				return new Archer(name);
			case 3:
				return new Mage(name);
		}
		return null;
	}

	private String getName() {
		System.out.print("This is the story about: ");
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	private void fight() { //TODO poprawic formatowanie !!!
		System.out.println("FIGHT!\n");
		System.out.println("Enemy: " + enemy.getName() + " lvl " + enemy.getLvl());
		System.out.println("DMG: " + enemy.getAd() + " DEF: " + enemy.getDef() + " HP " + enemy.getHp());
		System.out.println("Your actions:\n(1) Attack Enemy!\n(2) Drink Potions!\n" + (hero.getHp() < 25 ? "(3) RUN AWAY!" : "")); //3 option ?!
		byte i = 1;
		while (hero.getHp() > 0 && enemy.getHp() > 0) { //TODO TU DOKONCZYC!!!!
			System.out.println("=".repeat(WIDTH));
			System.out.format("%-46s %8s %-20s%n", " ".repeat(23) + "Hero HP= " + hero.getHp(), "ROUND " + i, " ".repeat(15) + "Enemy HP= " + enemy.getHp());
			System.out.println("=".repeat(WIDTH));
			switch (getOption(3)) {
				case 1: // ??
					hero.attack(enemy);
					enemy.attack(hero); //  ???
					clearScreen();
					break;
				case 2:
					System.out.println("Choose potion...");
					System.out.println("(1)Red Potion [+ 25 HP]\n (2)Blue Potion [+ 25 MANA]\n");
					Potions potion;
					switch (getOption(2)) {
						case 1:
							if ((potion = (Potions) hero.getInventory().getItemWithTheSameType(Potions.class)) != null && potion.getHp() > 0) {
								usePotion(potion);
							} else {
								System.out.println("You don't have Red potions !");
							}
							break;
						case 2:
							if ((potion = (Potions) hero.getInventory().getItemWithTheSameType(Potions.class)) != null && potion.getMana() > 0) {
								usePotion(potion);
							} else {
								System.out.println("You don't have Blue potions !");
							}
							break;
						default:
							break;
					}
				case 3:
					if (hero.getHp() < 25) {
						System.out.println("You ran away...");
						return;
					}
					continue;
				default:
					break;
			}
			i++;
			System.out.println();
			if (enemy.getHp() == 0) {
				System.out.println("Enemy defeated!");
				short reward = randomizeAttribute((byte) enemy.getExpBounty());
				hero.setExp((short) (hero.getExp() + enemy.getExpBounty()));
				hero.setGold((short) (hero.getGold() + reward));
				System.out.println("Gold earned: " + reward + '!');
				System.out.println("Exp earned: " + enemy.getExpBounty() + '!');
			}
			if (hero.getHp() == 0) {
				System.out.println("You have been defeated!\n");
				System.out.println("X".repeat(WIDTH) + '\n');
				System.out.println(" ".repeat(WIDTH / 2 - 5) + "-GAME OVER-\n");
				System.out.println("X".repeat(WIDTH));
				this.running = false;
			}
		}
	}

	private void usePotion(Potions potion) {
		System.out.println("Drinking potion...");
		if (potion.getHp() > 0) {
			if (potion.getHp() + hero.getHp() > hero.getMaxHp()) {
				hero.setHp(hero.getMaxHp());
			} else {
				hero.setHp((short) (hero.getHp() + potion.getHp()));
			}
		} else {
			if (potion.getMana() + hero.getMana() > hero.getMaxMana()) {
				hero.setMana(hero.getMaxMana());
			} else {
				hero.setMana((short) (hero.getMana() + potion.getMana()));
			}
		}
		hero.getInventory().removeItem(potion.getId());
	}

	private void showInventory(Hero hero) {
		System.out.println("+".repeat(WIDTH));
		System.out.println(" ".repeat(WIDTH / 2 - 7) + "HERO INVENTORY");
		System.out.println("+".repeat(WIDTH));
		hero.getInventory().show();
		System.out.println("-".repeat(WIDTH));
		System.out.println(" ".repeat(WIDTH / 2 - 7) + "HERO EQUIPMENT");
		System.out.println("-".repeat(WIDTH));
		hero.getEquipment().show();

		System.out.println("(1) Equip/Use Item!");
		System.out.println("(2) Take off Item!");
		System.out.println("(3) Exit!");

		switch (getOption(3)) {
			case 1:
				System.out.println("What do you want to equip/use? (Enter index number)");
				Item item;
				if ((item = hero.getInventory().getItem(getOption())) != null) {
					if (item.getClass().getSimpleName().equals("Potions")) {
						usePotion(((Potions) item));
					} else {
						hero.equip(item);
					}
				}
				break;
			case 2:
				System.out.println("What do you want to take off? (Enter index number)");
				if ((item = hero.getEquipment().getItem(getOption())) != null) {
					hero.unEquip(item);
				}
			case 3:
				break;
		}
	}
}
