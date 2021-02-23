package io.github.jeremyhu.rpg.item;

import io.github.jeremyhu.rpg.builder.LaserGunBuilder;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import static io.github.jeremyhu.rpg.builder.RangedWeaponBuilder.WEAPON_RANGE;
import static io.github.jeremyhu.rpg.builder.WeaponBuilder.WEAPON_DAMAGE;

public class LaserGun extends Item implements RangedWeapon {

    private final double range;
    private final double damage;
    private final int cooldown;

    public LaserGun(LaserGunBuilder builder) {
        super(builder);
        this.range = builder.getRange();
        this.damage = builder.getDamage();
        this.cooldown = builder.getCooldown();

        this.setExecuteEvent(event -> {
            Player player = event.getPlayer();
            World world = player.getWorld();
            Location start = player.getEyeLocation();
            Vector direction = player.getLocation().getDirection();
            RayTraceResult rayTraceResult = world.rayTrace(
                    start,
                    direction,
                    range,
                    FluidCollisionMode.NEVER,
                    true,
                    0,
                    entity -> !entity.equals(player));

            direction.multiply(0.5);
            double distance;

            if (rayTraceResult == null) {
                distance = range;
            } else {
                Location hitPos = rayTraceResult.getHitPosition().toLocation(player.getWorld());
                distance = start.distance(hitPos);

                if (rayTraceResult.getHitEntity() != null) {
                    Entity entity = rayTraceResult.getHitEntity();
                    if (entity instanceof LivingEntity) {
                        LivingEntity livingEntity = (LivingEntity) entity;
                        livingEntity.damage(damage, player);
                    } else {
                        entity.remove();
                    }
                }
            }

            for (int i = 0; i * 0.5 < distance; i++) {
                world.spawnParticle(Particle.END_ROD,start,1,0,0,0,0.001);
                start.add(direction);
            }

        });
    }

    @Override
    public void setPersistentData(PersistentDataContainer container) {
        container.set(WEAPON_RANGE, PersistentDataType.DOUBLE, range);
        container.set(WEAPON_DAMAGE, PersistentDataType.DOUBLE, damage);
    }

    @Override
    public double getRange() {
        return range;
    }

    @Override
    public double getDamage() {
        return damage;
    }

    @Override
    public int getCoolDown() {
        return cooldown;
    }
}
