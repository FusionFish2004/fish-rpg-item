package io.github.jeremyhu.rpg.listeners;

import io.github.jeremyhu.rpg.builder.GiantDildoBuilder;
import io.github.jeremyhu.rpg.builder.LaserGunBuilder;
import io.github.jeremyhu.rpg.item.GiantDildo;
import io.github.jeremyhu.rpg.item.LaserGun;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        GiantDildo dildo = new GiantDildoBuilder()
                .build();

        player.getInventory().addItem(dildo.getItemStack());

    }
}
