package io.github.jeremyhu.rpg.item;

import io.github.jeremyhu.rpg.builder.MeleeWeaponBuilder;
import io.github.jeremyhu.rpg.catagory.Catagory;

public abstract class MeleeWeapon extends Weapon{

    protected MeleeWeapon(MeleeWeaponBuilder builder) {
        super(builder);
        setCatagory(Catagory.MELEE_WEAPON);
    }
}
