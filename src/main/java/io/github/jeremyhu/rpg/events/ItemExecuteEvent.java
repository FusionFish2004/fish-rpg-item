package io.github.jeremyhu.rpg.events;

import io.github.jeremyhu.rpg.FishRPG;
import io.github.jeremyhu.rpg.item.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

public abstract class ItemExecuteEvent{

    private Player player;
    private final ItemStack itemStack;
    private final Item item;
    private final Event event;

    public Event getEvent() {
        return event;
    }

    public ItemExecuteEvent(Event event) {
        this.event = event;
        this.player = getPlayer();
        this.itemStack = player.getInventory().getItemInMainHand();
        this.item = FishRPG.getInstance().getManager().getItem(itemStack);
    }

    public abstract Player getPlayer();

    public ItemStack getItemStack() {
        return itemStack;
    }

    public Item getItem() {
        return item;
    }

}
