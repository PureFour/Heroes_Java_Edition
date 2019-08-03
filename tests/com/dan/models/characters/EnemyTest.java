package com.dan.models.characters;

import static com.dan.models.characters.Enemy.EASY_ENEMIES;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EnemyTest {
	Enemy enemy;

	@BeforeEach
	void setUp() {
	}

	@Test
	void spawnTest() throws CloneNotSupportedException {
		{
			enemy = EASY_ENEMIES[0];
			Assertions.assertNotNull(enemy);
			enemy = EASY_ENEMIES[0];
			Assertions.assertNotNull(enemy);
		}
	}
}