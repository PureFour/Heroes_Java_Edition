package com.dan.utils;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class JsonParserTest {

	@Test
	void jsonGetTest() throws IOException {
		JsonParser jsonParser = new JsonParser();

		JsonParser.getItemsNames("boots")
			.forEach(System.out::println);

	}
}