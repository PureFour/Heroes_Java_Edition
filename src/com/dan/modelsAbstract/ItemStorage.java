package com.dan.modelsAbstract;

import static com.dan.models.Game.WIDTH;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public interface ItemStorage {

	HashMap<Byte, Item> storage = new HashMap<>();
	byte maxSize = 5;

	default boolean isFull() {
		return getStorage().size() == getMaxSize();
	}

	default boolean isEmpty() {
		return getStorage().isEmpty();
	}

	HashMap<Byte, Item> getStorage();

	byte getMaxSize();

	default Item getItem(int index) {
		if (getStorage().containsKey((byte) index)) {
			return getStorage().get((byte) index);
		} else {
			System.out.println("Item doesn't exist!");
		}
		return null;
	}

	default void addItem(Item item) {
		if (isFull()) {
			System.out.println("Inventory is Full!");
		} else {
			getStorage().put(item.getId(), item);
			System.out.println("default add ITEM!");
		}
	}

	default void addItems(List<Item> items) {
		if (isFull()) {
			System.out.println("Inventory is Full!");
		} else {
			items.forEach(item -> getStorage().put(item.getId(), item));
			System.out.println("default addItems!");
		}
	}

	default void removeItem(int index) {
		if (getStorage().containsKey((byte) index)) {
			getStorage().remove((byte) index);
		} else {
			System.out.println("Item doesn't exist!");
		}
	}

//	default void removeItem(Item item) {
//		if (item != null) {
//			getStorage().remove(item.getId());
//		} else {
//			System.out.println("Item doesn't exist!");
//		}
//	}

	default void show() {
		if (getStorage().isEmpty()) {
			System.out.println("Inventory is empty!");
			return;
		}
		getStorage().forEach(
			(aByte, item) -> item.show()
		);
	}

	default boolean hasItemType(Item item) {
		return getStorage().values().stream()
			.anyMatch(type -> type.getClass().getTypeName().equals(item.getClass().getTypeName()));
	}

	default Item getItemWithTheSameType(Class itemType) {
		for (Item it : getStorage().values()) {
			if (itemType.isInstance(it)) {
				return it;
			}
		}
		return null;
	}
}
