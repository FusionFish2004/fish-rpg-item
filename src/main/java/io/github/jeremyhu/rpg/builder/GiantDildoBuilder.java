package io.github.jeremyhu.rpg.builder;

import io.github.jeremyhu.rpg.item.GiantDildo;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.Arrays;

public class GiantDildoBuilder extends MeleeWeaponBuilder {

    public GiantDildoBuilder() {
        super("GiantDildo");
        super.name("§d§l牛子");
        super.lore(Arrays.asList(
                "又粗又长",
                "热乎乎的"
        ));
    }

    @Override
    public GiantDildo build() {
        return new GiantDildo(this);
    }

    @Override
    public void getPersistentData(PersistentDataContainer container) {

    }
}
