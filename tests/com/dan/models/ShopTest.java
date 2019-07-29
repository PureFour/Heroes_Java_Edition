package com.dan.models;

import static org.junit.jupiter.api.Assertions.*;

import com.dan.models.characters.Hero;
import com.dan.models.characters.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShopTest {
	Shop shop;
	Warrior hero;

	@BeforeEach
	void setUp() {
		shop = new Shop();
		shop.initialize();
		hero = new Warrior("dummy");
	}

	@Test
	void showTest() {
		shop.show();
	}

}