package com.dan.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.dan.modelsAbstract.Item;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParser { //TODO zmienic nazwy jsonow !
	private final static String ITEMS_JPATH = "database/items/";

	public static List<String> getItemsNames(String itemName) throws IOException {
		String content = new String(Files.readAllBytes(Paths.get(ITEMS_JPATH + itemName + ".json")));
		JSONObject object = new JSONObject(content);
		JSONArray array = object.getJSONArray("names");
		return FunctionHelper.getRandomElements(array, 10);
	}

	public static Item.Bonus getItemsBonus(String itemName) throws IOException {
		String content = new String(Files.readAllBytes(Paths.get(ITEMS_JPATH + itemName + ".json")));
		JSONObject object = new JSONObject(content);
		JSONArray array = object.getJSONArray("bonuses");
		JSONObject randomBonus = FunctionHelper.getRandomElement(array);
		return Item.Bonus.builder()
			.name(randomBonus.get("name").toString())
			.value((byte) randomBonus.getInt("value"))
			.field(randomBonus.getString("field"))
			.build();
	}
}
