package com.dan.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.Scanner;

import com.dan.models.characters.Hero;

public final class FunctionHelper {

	public static byte getOption(int options) {
		final Scanner scanner = new Scanner(System.in);
		byte input;
		do {
			while (!scanner.hasNextByte()) {
				System.out.println("That's not a number!");
				scanner.next();
			}
			System.out.println("Try again!");
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
			System.out.println("Try again!");
			input = scanner.nextByte();
			return input;
		} while (true);
	}

	public static byte getOption(String... options) {
		final Scanner scanner = new Scanner(System.in);
		String input;
		byte choice;
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
		return (byte) new Random().nextInt(range);
	}

	public static byte randomizeAttribute(byte attribute) {
		return (byte) new Random().nextInt(attribute);
	}

	public static Object runGetter(Field field, Hero o) {
		// MZ: Find the correct method
		for (Method method : o.getClass().getMethods()) {
			if ((method.getName().startsWith("get")) && (method.getName().length() == (field.getName().length() + 3))) {
				if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase())) {
					// MZ: Method found, run it
					try {
						return method.invoke(o);
					} catch (IllegalAccessException | InvocationTargetException e) {
						System.out.println("Could not determine method: " + method.getName());
					}

				}
			}
		}

		return null;
	}

}
