package io.github.jeremyhu.rpg.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ItemDamageEvent extends ItemExecuteEvent{

    public ItemDamageEvent(EntityDamageByEntityEvent event) {
        super(event);
    }

    @Override
    public Player getPlayer() {
        return (Player) ((EntityDamageByEntityEvent) getEvent()).getDamager();
    }

    public Entity getEntity() {
        return ((EntityDamageByEntityEvent) getEvent()).getEntity();
    }

    public void setDamage(double damage) {
        if (getEvent() instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent entityEvent = (EntityDamageByEntityEvent) getEvent();
            entityEvent.setDamage(damage);
        }
    }
}
