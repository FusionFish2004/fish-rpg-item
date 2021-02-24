package io.github.jeremyhu.rpg.item;

import io.github.jeremyhu.rpg.builder.LaserGunBuilder;
import io.github.jeremyhu.rpg.events.ItemInteractEvent;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

public class LaserGun extends RangedWeapon {

    public LaserGun(LaserGunBuilder builder) {
        super(builder);

        this.setExecuteEvent(event -> {
            if (!(event instanceof ItemInteractEvent)) return;

            PlayerInteractEvent interactEvent = (PlayerInteractEvent)event.getEvent();
            Action action = interactEvent.getAction();

            if (action != Action.RIGHT_CLICK_AIR) return;

            Player player = event.getPlayer();
            World world = player.getWorld();
            Location start = player.getEyeLocation();
            Vector direction = player.getLocation().getDirection();
            RayTraceResult rayTraceResult = world.rayTrace(
                    start,
                    direction,
                    getRange(),
                    FluidCollisionMode.NEVER,
                    true,
                    0,
                    entity -> !entity.equals(player));

            direction.multiply(0.5);
            double distance;

            start = RangedWeapon.getHandLocation(player.getLocation());

            if (rayTraceResult == null) {
                distance = getRange();
            } else {
                Location hitPos = rayTraceResult.getHitPosition().toLocation(player.getWorld());
                distance = start.distance(hitPos);

                if (rayTraceResult.getHitEntity() != null) {
                    Entity entity = rayTraceResult.getHitEntity();
                    if (entity instanceof LivingEntity) {
                        LivingEntity livingEntity = (LivingEntity) entity;
                        livingEntity.damage(getDamage(), player);
                    } else {
                        entity.remove();
                    }
                }
            }

            for (int i = 0; i * 0.5 < distance; i++) {
                world.spawnParticle(Particle.END_ROD,start,1,0,0,0,0.001);
                start.add(direction);
            }

            interactEvent.setCancelled(true);
        });
    }

}
