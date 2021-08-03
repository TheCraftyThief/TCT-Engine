package club.thecraftythief.engine.map.rooms;

import club.thecraftythief.engine.map.RoomType;
import org.bukkit.Material;

public class GenericRoom extends RoomType {
    public GenericRoom() {
        super(Material.GLASS);
    }

    @Override
    public void canSpawn() {}
}
