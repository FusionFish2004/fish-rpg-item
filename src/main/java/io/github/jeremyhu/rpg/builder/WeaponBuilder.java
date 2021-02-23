package io.github.jeremyhu.rpg.builder;

import io.github.jeremyhu.rpg.FishRPG;
import org.bukkit.NamespacedKey;

public abstract class WeaponBuilder extends ItemBuilder {

    public static final NamespacedKey WEAPON_DAMAGE = new NamespacedKey(FishRPG.getInstance(), "weapon_damage");
    public static final NamespacedKey WEAPON_COOLDOWN = new NamespacedKey(FishRPG.getInstance(), "weapon_cooldown");

    private double damage;
    private int cooldown;

    public WeaponBuilder(String type) {
        super(type);
    }

    public double getDamage() {
        return damage;
    }

    public int getCooldown() {
        return cooldown;
    }

    public WeaponBuilder damage(double damage) {
        this.damage = damage;
        return this;
    }

    public WeaponBuilder cooldown(int cooldown) {
        this.cooldown = cooldown;
        return this;
    }
}
