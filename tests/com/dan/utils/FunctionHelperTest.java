package com.dan.utils;

import com.dan.models.characters.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FunctionHelperTest {

	Warrior warrior;

	@BeforeEach
	void setUp() {
		warrior = new Warrior("dummy");
	}

	@Test
	void reflexMethodTest() {

	}

	@Test
	void clearTermTest() {
		System.out.println("HellO!!");
		System.out.println("I hope you will able to see it!");
		FunctionHelper.clearScreen();
	}
}