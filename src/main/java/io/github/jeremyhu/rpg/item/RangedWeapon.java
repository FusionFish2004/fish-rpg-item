package io.github.jeremyhu.rpg.item;

import io.github.jeremyhu.rpg.builder.RangedWeaponBuilder;
import io.github.jeremyhu.rpg.catagory.Catagory;
import io.github.jeremyhu.rpg.keys.DataKey;
import org.bukkit.Location;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import static io.github.jeremyhu.rpg.builder.RangedWeaponBuilder.WEAPON_RANGE;

public abstract class RangedWeapon extends Weapon {

    private final double range;
    private static final Vector VECTOR_UP = new Vector(0,1,0);

    protected RangedWeapon(RangedWeaponBuilder builder) {
        super(builder);
        this.range = builder.getRange();
        this.addDataKey(new DataKey(WEAPON_RANGE, PersistentDataType.DOUBLE, range));
        setCatagory(Catagory.RANGED_WEAPON);
    }

    public double getRange() {
        return range;
    }

    public static Location getHandLocation(Location location) {
        Location loc = location.clone();
        Vector direction = location.getDirection()
                .clone()
                .setY(0)
                .normalize();
        Vector vec = direction.getCrossProduct(VECTOR_UP)
                .normalize()
                .multiply(0.5);
        loc.add(new Vector(0,1.4,0));
        loc.add(vec);
        return loc;
    }
}
