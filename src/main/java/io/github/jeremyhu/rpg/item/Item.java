package io.github.jeremyhu.rpg.item;

import io.github.jeremyhu.rpg.FishRPG;
import io.github.jeremyhu.rpg.builder.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public abstract class Item {

    public static final NamespacedKey ITEM_UUID = new NamespacedKey(FishRPG.getInstance(), "item_uuid");
    public static final NamespacedKey ITEM_TYPE = new NamespacedKey(FishRPG.getInstance(), "item_type");

    private final Material material;
    private final String name;
    private final int model;
    private final UUID uuid;
    private final String type;
    private final List<String> lore;

    public void setExecuteEvent(Consumer<PlayerInteractEvent> executeEvent) {
        this.executeEvent = executeEvent;
    }

    private Consumer<PlayerInteractEvent> executeEvent;

    protected Item(ItemBuilder builder) {
        this.material = builder.getMaterial();
        this.name = builder.getName();
        this.model = builder.getModel();
        this.type = builder.getType();
        this.executeEvent = builder.getExecuteEvent();
        this.uuid = builder.getUuid();
        this.lore = builder.getLore();
    }

    public Material getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }

    public int getModel() {
        return model;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getType() {
        return type;
    }

    public List<String> getLore() {
        return lore;
    }

    public Consumer<PlayerInteractEvent> getExecuteEvent() {
        return executeEvent;
    }

    public ItemStack getItemStack() {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setCustomModelData(model);
        itemMeta.setLore(lore);

        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        container.set(ITEM_UUID, PersistentDataType.STRING, uuid.toString());
        container.set(ITEM_TYPE, PersistentDataType.STRING, type);
        setPersistentData(container);

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public abstract void setPersistentData(PersistentDataContainer container);

    public static boolean isItem(ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        return container.has(ITEM_UUID, PersistentDataType.STRING);
    }
}
