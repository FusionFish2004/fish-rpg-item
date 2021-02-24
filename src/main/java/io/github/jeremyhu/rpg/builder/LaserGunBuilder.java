package io.github.jeremyhu.rpg.builder;

import io.github.jeremyhu.rpg.item.LaserGun;
import org.bukkit.Material;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public class LaserGunBuilder extends RangedWeaponBuilder {

    public LaserGunBuilder() {
        super("LaserGun");
        super.name("§d§l激光枪");
        super.material(Material.PRISMARINE_SHARD);
        super.lore(Arrays.asList(
                "激光枪",
                "biu biu"
        ));
    }

    @Override
    public LaserGun build() {
        return new LaserGun(this);
    }

    @Override
    public void getPersistentData(PersistentDataContainer container) {
        double range = container.get(WEAPON_RANGE, PersistentDataType.DOUBLE);
        double damage = container.get(WEAPON_DAMAGE, PersistentDataType.DOUBLE);
        int cooldown = container.get(WEAPON_COOLDOWN, PersistentDataType.INTEGER);
        range(range);
        damage(damage);
        cooldown(cooldown);
    }
}
