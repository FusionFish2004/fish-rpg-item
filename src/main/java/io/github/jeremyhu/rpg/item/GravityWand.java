package io.github.jeremyhu.rpg.item;

import io.github.jeremyhu.rpg.builder.GravityWandBuilder;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class GravityWand extends Item{

    public GravityWand(GravityWandBuilder builder) {
        super(builder);
        this.setExecuteEvent(event -> {
            Player player = event.getPlayer();
            player.setVelocity(new Vector(0,10,0));
        });
    }
}
