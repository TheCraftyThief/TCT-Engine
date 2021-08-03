package club.thecraftythief.engine.map;

import org.bukkit.Material;

public abstract class RoomType {
    public Material identifier;

    public RoomType(Material identifier) { this.identifier = identifier; }

    public abstract void canSpawn();
}