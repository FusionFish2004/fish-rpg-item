package io.github.jeremyhu.rpg.item;

import io.github.jeremyhu.rpg.builder.ItemBuilder;
import io.github.jeremyhu.rpg.builder.WeaponBuilder;
import io.github.jeremyhu.rpg.keys.DataKey;
import org.bukkit.persistence.PersistentDataType;

import static io.github.jeremyhu.rpg.builder.WeaponBuilder.*;

public abstract class Weapon extends Item{

    private final double damage;
    private final int cooldown;

    protected Weapon(WeaponBuilder builder) {
        super(builder);
        this.damage = builder.getDamage();
        this.cooldown = builder.getCooldown();
        this.addDataKey(new DataKey(WEAPON_DAMAGE, PersistentDataType.DOUBLE, damage));
        this.addDataKey(new DataKey(WEAPON_COOLDOWN, PersistentDataType.INTEGER, cooldown));
    }

    public double getDamage() {
        return damage;
    }

    public int getCoolDown() {
        return cooldown;
    }
}
