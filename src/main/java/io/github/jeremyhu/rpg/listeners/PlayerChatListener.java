package io.github.jeremyhu.rpg.listeners;

import io.github.jeremyhu.rpg.builder.LaserGunBuilder;
import io.github.jeremyhu.rpg.item.LaserGun;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        LaserGun laserGun = (LaserGun) new LaserGunBuilder()
                .range(20)
                .damage(20)
                .cooldown(5)
                .build();

        player.getInventory().addItem(laserGun.getItemStack());
        player.sendMessage(String.valueOf(laserGun.getDamage()));
    }
}
