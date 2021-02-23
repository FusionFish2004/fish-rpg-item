package io.github.jeremyhu.rpg.manager;

import io.github.jeremyhu.rpg.builder.ItemBuilder;
import io.github.jeremyhu.rpg.item.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.UUID;

import static io.github.jeremyhu.rpg.item.Item.*;
import static io.github.jeremyhu.rpg.item.Item.isItem;

public class ItemManager extends HashMap<UUID, Item> {
    public Item getItem(ItemStack itemStack) {

        if (!isItem(itemStack)) return null;

        UUID uuid = getUUID(itemStack);
        if (containsKey(uuid)) {
            return get(uuid);
        } else {
            ItemBuilder builder = ItemBuilder.getBuilder(getType(itemStack));

            assert builder != null;
            Item item = builder.fromItem(itemStack)
                    .build();
            this.put(uuid, item);
            return item;
        }
    }

    public static UUID getUUID(ItemStack itemStack) {
        PersistentDataContainer container = itemStack.getItemMeta().getPersistentDataContainer();
        return UUID.fromString(container.get(ITEM_UUID, PersistentDataType.STRING));
    }

    public static String getType(ItemStack itemStack) {
        PersistentDataContainer container = itemStack.getItemMeta().getPersistentDataContainer();
        return container.get(ITEM_TYPE, PersistentDataType.STRING);
    }
}
