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
import com.dan.models.items.Potions;
import com.dan.modelsAbstract.Item;

public class Game implements Runnable {
	public static final int WIDTH = 77;

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
			} catch (InterruptedException | IOException | CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
	}

	private void autoSave() {
		saveCharacter(this.hero);
	}

	private void mainMenu() throws InterruptedException, IOException, CloneNotSupportedException { //TODO poprawic formatowanie !!!
		System.out.println("*".repeat(WIDTH));
		System.out.println("\n" + " ".repeat(32) + "-MAIN MENU-");
		System.out.println("*".repeat(WIDTH));

		System.out.println("Name: " + this.hero.getName());
		System.out.println("Lvl: " + this.hero.getLvl());
		System.out.println("Exp: " + this.hero.getExp() + "/" + this.hero.getExpToNextLvl() + " ".repeat(35) + "(1) FIGHT");
		System.out.println("GOLD: " + this.hero.getGold() + " ".repeat(42) + "(2) Shop");
		System.out.println("HP: " + this.hero.getHp() + "/" + this.hero.getMaxHp() + " ".repeat(33) + "(3) Inventory");
		System.out.println("MANA: " + this.hero.getMana() + "/" + this.hero.getMaxMana() + " ".repeat(35) + "(4) Upgrade stuff");
		System.out.println("AD: " + this.hero.getAd() + " ".repeat(38) + (hero.getExp() > hero.getExpToNextLvl() ? "(5) LvlUp!(You can add skill points!)" : ""));
		System.out.println("AP: " + this.hero.getAp() + " ".repeat(39) + "(6) Save Game");
		System.out.println("DEF: " + this.hero.getDef() + " ".repeat(37) + "(7) Load Game");
		System.out.println("Strength: " + this.hero.getStrength() + " ".repeat(32) + "(8) Exit Game");
		System.out.println("Vitality: " + this.hero.getVitality());
		System.out.println("Dexterity: " + this.hero.getDexterity());
		System.out.println("Intelligence: " + this.hero.getIntelligence());
		System.out.println("Luck: " + this.hero.getLuck());
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
		System.out.println("WELCOME TO THE GAME !");
		System.out.println("=".repeat(WIDTH));
		System.out.println("\n\n" + " ".repeat(30) + "HEROES\n\n");
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

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("Choose Hero Class!\n(1) Warrior\n(2) Archer\n(3) Mage");
		System.out.println("My choice:_\b");
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
	} //to usunac!

	private void fight() { //TODO poprawic formatowanie !!!
		short HP = enemy.getHp();
		System.out.println("FIGHT!\n");
		byte i = 1;
		while (hero.getHp() > 0 && enemy.getHp() > 0) {
			System.out.println(" ".repeat(40) + "ROUND " + i + '!');
			System.out.println("=".repeat(WIDTH));
			System.out.println(" ".repeat(30) + "Hero HP= " + hero.getHp() + " |||| ");
			System.out.println("Enemy HP= " + enemy.getHp());
			System.out.println("=".repeat(WIDTH));
			enemy.attack(hero);
			System.out.println("+".repeat(WIDTH));
			System.out.println("Your actions:\n(1) Attack Enemy!\n(2) Drink Potions!\n" + (hero.getHp() < 25 ? "(3) RUN AWAY!" : ""));
			switch (getOption(3)) {
				case 1:
					hero.attack(enemy);
					clearScreen();
					break;
				case 2: //TODO zaimplementowac ! //zrobic uzywanie poza walka lub usunac ta mozliwosc!
					System.out.println("Choose potion...");
					System.out.println("(1)Red Potion [+ 25 HP]\n (2)Blue Potion [+ 25 MANA]\n");
					switch (getOption(2)) {
						case 1:
							Potions potion;
							if ((potion = (Potions) hero.getInventory().getItemWithTheSameType(Potions.class)) != null) {
								System.out.println("Drinking RED potion...");
								if (potion.getHp() + hero.getHp() > hero.getMaxHp()) {
									hero.setHp(hero.getMaxHp());
								} else {
									hero.setHp((short) (hero.getHp() + potion.getHp()));
								}
							}
							System.out.println("You don't have Red potions !");
							break;
						case 2:
//							if(hero.removeItem(hero.searchItem("Blue-potion")) == 1)
//							{
//								std::cout << "\"Drinking Blue potion...\"\n";
//								if(25 + hero.getMANA() > hero.getMaxMANA()) hero.setMANA(hero.getMaxMANA());
//								hero.setMANA(hero.getMANA() + 25);
//							}
//							else std::cout << "You don't have Blue potions!!!";
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
				System.out.println(" ".repeat(35) + "-GAME OVER-\n");
				this.running = false;
			}
		}
//		enemy.setHP(HP); //resetting enemy hp... ??
	}

	private void showInventory(Hero hero) {
		System.out.println("HERO INVENTORY");
		hero.getInventory().show();
		System.out.println("HERO EQUIPMENT");
		hero.getEquipment().show();

		System.out.println("(1) Equip Item!");
		System.out.println("(2) Take off Item!");
		System.out.println("(3) Exit!");

		switch (getOption(3)) {
			case 1:
				System.out.println("What do you want to equip? (Enter index number)");
				Item item;
				if ((item = hero.getInventory().getItem(getOption())) != null) {
					hero.equip(item);
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
