package com.dan.utils;

import static com.dan.utils.FunctionHelper.getOption;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.dan.models.Inventory;
import com.dan.models.characters.Hero;
import com.dan.modelsAbstract.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.ls.LSOutput;

public class GameSaver {
	private static final String FILE_FOLDER = "database/savedGames/";

	public static void saveCharacter(Hero hero) {
		File folder = new File(FILE_FOLDER);
		folder.mkdirs();
		String fileName = hero.getName() + '(' + hero.getClass().getSimpleName() + "_lvl_" + hero.getLvl() + ')';
		File file = new File(FILE_FOLDER + '/' + fileName + ".data");

		try (OutputStream outputStream = new FileOutputStream(file);
			 ObjectOutput output = new ObjectOutputStream(outputStream)) {
			output.writeObject(hero);
			output.close();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Hero changeHeroMenu() {
		List<String> files = new ArrayList<>();
		try (Stream<Path> walk = Files.walk(Paths.get("database/savedGames"))) {
			files = walk.filter(Files::isRegularFile)
				.map(Path::toString)
				.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Choose hero!");
		for (int i = 0; i < files.size(); i++) {
			String fileName = files.get(i);
			String heroName = fileName.substring(20, fileName.length() - 5);
			System.out.println(String.format("(%d) ", i + 1) + heroName);
		}
		return loadCharacter(files.get(getOption(files.size()) - 1));
	}

	public static Hero loadCharacter(String fileName) {
//		System.out.println("enter json file name:_\b.data");
//		Scanner scanner = new Scanner(System.in);

		try (InputStream inputStream = new FileInputStream(fileName);
			 ObjectInput input = new ObjectInputStream(inputStream)) {
			return (Hero) input.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void saveCharacterAsJSON(Hero hero) { //TODO przerobic na inna biblioteke org.json
		File folder = new File(FILE_FOLDER);
		folder.mkdirs();
		File file = new File(FILE_FOLDER + hero.getName() + ".json");
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
	}
}
