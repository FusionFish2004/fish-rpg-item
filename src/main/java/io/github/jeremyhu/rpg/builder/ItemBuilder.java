package io.github.jeremyhu.rpg.builder;

import io.github.jeremyhu.rpg.events.ItemExecuteEvent;
import io.github.jeremyhu.rpg.item.Item;
import io.github.jeremyhu.rpg.manager.ItemManager;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public abstract class ItemBuilder {

    private Material material = Material.BLAZE_ROD;
    private String name;
    private int model;
    private UUID uuid = UUID.randomUUID();
    private final String type;
    private Consumer<ItemExecuteEvent> executeEvent;
    private List<String> lore = new ArrayList<>();

    public ItemBuilder(String type) {
        this.type = type;
    }

    public ItemBuilder material(Material material) {
        this.material = material;
        return this;
    }

    public ItemBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder model(int model) {
        this.model = model;
        return this;
    }

    public ItemBuilder executeEvent(Consumer<ItemExecuteEvent> executeEvent) {
        this.executeEvent = executeEvent;
        return this;
    }

    public ItemBuilder uuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public ItemBuilder lore(List<String> lore) {
        this.lore = lore;
        return this;
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

    public abstract Item build();

    public Consumer<ItemExecuteEvent> getExecuteEvent() {
        return executeEvent;
    }

    public static ItemBuilder getBuilder(String type) {
        String classPath = "io.github.jeremyhu.rpg.builder." + type + "Builder";
        try {
            Class<?> cls = Class.forName(classPath);
            return (ItemBuilder) cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ItemBuilder fromItem(ItemStack itemStack) {
        uuid(ItemManager.getUUID(itemStack));
        name(itemStack.getItemMeta().getDisplayName());
        material(itemStack.getType());
        model(itemStack.getItemMeta().getCustomModelData());
        lore(itemStack.getLore());

        PersistentDataContainer container = itemStack.getItemMeta().getPersistentDataContainer();
        getPersistentData(container);
        return this;
    }

    public abstract void getPersistentData(PersistentDataContainer container);
}
