package com.dan.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.dan.modelsAbstract.Item;
import org.json.JSONArray;
import org.json.JSONObject;

public final class FunctionHelper {

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
	}

	public static List<String> getRandomElements(JSONArray list,
		int totalItems) {
		List<String> newList = new ArrayList<>();
		for (int i = 0; i < totalItems; i++) {
			int randomIndex = new Random().nextInt(list.length());
			newList.add(list.get(randomIndex).toString());
			list.remove(randomIndex);
		}
		return newList;
	}

	public static JSONObject getRandomElement(JSONArray array) {
		return (JSONObject) array.get(new Random().nextInt(array.length()));
	}

	public static byte getOption(int options) {
		final Scanner scanner = new Scanner(System.in);
		byte input;
		do {
			while (!scanner.hasNextByte()) {
				System.out.println("That's not a number!");
				scanner.next();
			}
			input = scanner.nextByte();
		} while (input > options || input < 1);
		return input;
	}

	public static byte getOption() {
		final Scanner scanner = new Scanner(System.in);
		byte input;
		do {
			while (!scanner.hasNextByte()) {
				System.out.println("That's not a number!");
				scanner.next();
			}
			input = scanner.nextByte();
			return input;
		} while (true);
	}

	public static byte getOption(String... options) {
		final Scanner scanner = new Scanner(System.in);
		String input;
		while (true) {
			input = scanner.nextLine();
			for (int i = 0; i < options.length; i++) {
				if (input.equals(options[i])) {
					return (byte) i;
				}
			}
			System.out.println("Try again!");
		}
	}

	public static byte getRandomNumber(byte range) {
		return (byte) new Random().nextInt(range + 1); // +1 this is temporary solution !
	}

	public static byte randomizeAttribute(byte attribute) {
		return (byte) new Random().nextInt(attribute);
	}

	public static boolean getChance(float probability) { // 0-100 %    // nazwa metody ???
		double randomValue = Math.random() * 100;  //0.0 to 99.9
		return randomValue <= probability;
	}
}
