package com.dan.models;

import com.dan.models.characters.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShopTest {
	Shop shop;
	Warrior hero;

	@BeforeEach
	void setUp() {
		shop = new Shop();
		hero = new Warrior("dummy");
		shop.initialize(hero);
	}

	@Test
	void showTest() {
		shop.show();
	}

}