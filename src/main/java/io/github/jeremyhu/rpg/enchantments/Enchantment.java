package io.github.jeremyhu.rpg.enchantments;

import io.github.jeremyhu.rpg.catagory.Catagory;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.function.Consumer;

public abstract class Enchantment<T extends Catagory> {

    private final Consumer<PlayerInteractEvent> executeEvent;

    protected Enchantment(Consumer<PlayerInteractEvent> executeEvent) {
        this.executeEvent = executeEvent;
    }

    public Consumer<PlayerInteractEvent> getExecuteEvent() {
        return executeEvent;
    }
}
