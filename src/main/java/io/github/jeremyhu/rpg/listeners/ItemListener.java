package io.github.jeremyhu.rpg.listeners;

import io.github.jeremyhu.rpg.events.ItemDamageEvent;
import io.github.jeremyhu.rpg.events.ItemExecuteEvent;
import io.github.jeremyhu.rpg.events.ItemInteractEvent;
import io.github.jeremyhu.rpg.item.Item;
import io.github.jeremyhu.rpg.item.Weapon;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
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

        ItemExecuteEvent itemExecuteEvent = new ItemInteractEvent(event);

        Item item = itemExecuteEvent.getItem();
        item.getExecuteEvent().accept(itemExecuteEvent);

        if (item instanceof Weapon) {
            Player player = event.getPlayer();
            Weapon weapon = (Weapon) item;
            player.setCooldown(item.getMaterial(), weapon.getCoolDown());
        }

    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player)) return;
        Player player = (Player) event.getDamager();

        ItemStack itemStack = player.getInventory().getItemInMainHand();

        if (itemStack.getType() == Material.AIR) return;
        if (!Item.isItem(itemStack)) return;
        if (player.hasCooldown(itemStack.getType())) return;

        ItemExecuteEvent itemExecuteEvent = new ItemDamageEvent(event);

        Item item = itemExecuteEvent.getItem();
        item.getExecuteEvent().accept(itemExecuteEvent);

    }
}
