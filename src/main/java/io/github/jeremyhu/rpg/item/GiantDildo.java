package io.github.jeremyhu.rpg.item;

import io.github.jeremyhu.rpg.builder.GiantDildoBuilder;
import io.github.jeremyhu.rpg.events.ItemDamageEvent;
import org.bukkit.entity.Entity;

public class GiantDildo extends MeleeWeapon{
    public GiantDildo(GiantDildoBuilder builder) {
        super(builder);
        this.setExecuteEvent(event -> {
            if (!(event instanceof ItemDamageEvent)) return;
            ItemDamageEvent damageEvent = (ItemDamageEvent) event;
            if (damageEvent.getEntity() == null) return;
            Entity entity = damageEvent.getEntity();
            damageEvent.setDamage(0);
            entity.remove();
        });
    }
}
