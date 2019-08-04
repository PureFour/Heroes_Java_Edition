package com.dan.utils;

import static com.dan.utils.FunctionHelper.getOption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dan.models.characters.Hero;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GameSaver {
	private static final String FILE_FOLDER = "src/com/dan/resources/savedGames";

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

		try (Stream<Path> walk = Files.walk(Paths.get(FILE_FOLDER))) {
			files = walk.filter(Files::isRegularFile)
				.map(Path::toString)
				.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Choose hero!");
		for (int i = 0; i < files.size(); i++) {
			String fileName = files.get(i);
			String heroName = fileName.substring(33, fileName.length() - 5);
			System.out.println(String.format("(%d) ", i + 1) + heroName);
		}
		return loadCharacter(files.get(getOption(files.size()) - 1));
	}

	public static Hero loadCharacter(String fileName) {

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
