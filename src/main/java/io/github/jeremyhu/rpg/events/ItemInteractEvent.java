package io.github.jeremyhu.rpg.events;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemInteractEvent extends ItemExecuteEvent{

    public ItemInteractEvent(PlayerInteractEvent event) {
        super(event);
    }

    @Override
    public Player getPlayer() {
        return ((PlayerInteractEvent) getEvent()) .getPlayer();
    }
}
