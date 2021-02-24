package io.github.jeremyhu.rpg.keys;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class DataKey {

    private final NamespacedKey namespacedKey;
    private final PersistentDataType persistentDataType;
    private final Object value;

    public DataKey(NamespacedKey namespacedKey, PersistentDataType persistentDataType, Object value) {
        this.namespacedKey = namespacedKey;
        this.persistentDataType = persistentDataType;
        this.value = value;
    }

    public void apply(PersistentDataContainer container) {
        container.set(namespacedKey, persistentDataType, value);
    }
}
