package io.github.jeremyhu.rpg.builder;

import io.github.jeremyhu.rpg.FishRPG;
import org.bukkit.NamespacedKey;

public abstract class RangedWeaponBuilder extends WeaponBuilder{

    public static final NamespacedKey WEAPON_RANGE = new NamespacedKey(FishRPG.getInstance(), "weapon_range");

    public RangedWeaponBuilder(String type) {
        super(type);
    }

    private double range;

    public double getRange() {
        return range;
    }

    public RangedWeaponBuilder range(double range) {
        this.range = range;
        return this;
    }
}
