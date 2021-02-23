package io.github.jeremyhu.rpg.listeners;

import io.github.jeremyhu.rpg.FishRPG;
import io.github.jeremyhu.rpg.item.Item;
import io.github.jeremyhu.rpg.item.Weapon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        if (!event.hasItem()) return;

        ItemStack itemStack = event.getItem();
        assert itemStack != null;

        if (!Item.isItem(itemStack)) return;
        if (event.getPlayer().hasCooldown(itemStack.getType())) return;

        Item item = FishRPG.getInstance().getManager().getItem(itemStack);
        item.getExecuteEvent().accept(event);

        if (item instanceof Weapon) {
            Player player = event.getPlayer();
            Weapon weapon = (Weapon) item;
            player.setCooldown(item.getMaterial(), weapon.getCoolDown());
        }
        event.setCancelled(true);
    }
}
