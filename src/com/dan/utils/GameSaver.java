package com.dan.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.Scanner;

import com.dan.models.characters.Hero;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class GameSaver {
	private static final String FILE_FOLDER = "database/savedGames";

	public static void saveCharacter(Hero hero) {
		File folder = new File(FILE_FOLDER);
		folder.mkdirs();
		File file = new File(FILE_FOLDER + '/' + hero.getName() + ".data");

		try (OutputStream outputStream = new FileOutputStream(file);
			 ObjectOutput output = new ObjectOutputStream(outputStream)) {
			output.writeObject(hero);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Hero loadCharacter() {
		System.out.println("enter json file name:_\b.data");
		Scanner scanner = new Scanner(System.in);

		try (InputStream inputStream = new FileInputStream(FILE_FOLDER + '/' + scanner.nextLine() + ".data");
			 ObjectInput input = new ObjectInputStream(inputStream)) {
			return (Hero) input.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void saveCharacterAsJSON(Hero hero) {
		File folder = new File(FILE_FOLDER);
		folder.mkdirs();
		File file = new File(FILE_FOLDER + '/' + hero.getName() + ".json");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			OutputStream outputStream = new FileOutputStream(file);
			Writer writer = new OutputStreamWriter(outputStream);
			gson.toJson(hero, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadCharacterAsJSON(Hero hero, String filename) throws IOException {
		System.out.println("enter json file name:_\b.json");
		Scanner scanner = new Scanner(System.in);
//		FileInputStream fileInputStream = new FileInputStream(FILE_FOLDER + '/' + scanner.nextLine() + ".json");
		FileInputStream fileInputStream = new FileInputStream(FILE_FOLDER + '/' + filename + ".json");
		Gson gson = new GsonBuilder().create();
		Reader in = new InputStreamReader(fileInputStream);
		JsonReader jsonReader = new JsonReader(in);
		Type heroType = new TypeToken<Hero>() {
		}.getType();
		hero = gson.fromJson(jsonReader, heroType);
	}
}
