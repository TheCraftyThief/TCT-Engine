package club.thecraftythief.engine.data;

import club.thecraftythief.engine.Engine;
import org.bukkit.entity.Entity;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

public class DataStore {
    public static String read(Entity entity, String dataKey) {
        if (entity.hasMetadata(dataKey)) {
            List<MetadataValue> metadatas = entity.getMetadata(dataKey);
            for (MetadataValue value : metadatas) {
                String data = value.asString();
                return data;
            }
        } else {
            return null;
        }
        return null;
    }

    public static void store(Entity entity, String dataKey, String value) {
        entity.setMetadata(dataKey, new FixedMetadataValue(Engine.getInstance(), value));
    }

    public static void clear(Entity entity, String dataKey) {
        entity.removeMetadata(dataKey, Engine.getInstance());
    }
}
