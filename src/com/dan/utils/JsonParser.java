package com.dan.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParser { //TODO zmienic nazwy jsonow !
	private final static String ITEMS_JPATH = "database/items/";

	public static List<String> getItemsNames(String itemName) throws IOException {
		String content = new String(Files.readAllBytes(Paths.get(ITEMS_JPATH + itemName + ".json")));
		JSONObject object = new JSONObject(content);
		JSONArray array = object.getJSONArray("names");
		List<String> names = new ArrayList<>();
		IntStream.range(0, array.length())
			.forEach(i -> names.add(array.get(i).toString()));
		return names;
	}
}
