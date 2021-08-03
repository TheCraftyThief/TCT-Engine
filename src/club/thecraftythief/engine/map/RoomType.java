package club.thecraftythief.engine.map;

import org.bukkit.Material;

public enum RoomType {
    GENERIC(Material.GLASS),
    STAIR_UP(Material.STONE),
    STAIR_RECEIVE(Material.COBBLESTONE);

    Material identifier;
    RoomType(Material identifier) {
        this.identifier = identifier;
    }
}