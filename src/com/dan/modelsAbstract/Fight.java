package com.dan.modelsAbstract;

import static java.lang.Math.random;

public interface Fight<T> { //TODO zmienic nazwe ...
	default void attack(T enemy) {
		System.out.println("AttackMethod-Interface");
	}

	default boolean isCritical(byte luck) {
		return random() % 100 < 10 * luck;
	}
}
