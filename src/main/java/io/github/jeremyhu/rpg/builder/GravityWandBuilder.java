package io.github.jeremyhu.rpg.builder;

import io.github.jeremyhu.rpg.item.GravityWand;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.Arrays;

public class GravityWandBuilder extends ItemBuilder{

    public GravityWandBuilder() {
        super("GravityWand");
        super.name("§d§l重力法杖");
        super.lore(Arrays.asList(
                "重力法杖",
                "可以使你一蹦三尺高"
        ));
    }

    @Override
    public GravityWand build() {
        return new GravityWand(this);
    }

    @Override
    public void getPersistentData(PersistentDataContainer container) {

    }
}
