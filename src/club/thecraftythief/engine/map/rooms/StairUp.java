package club.thecraftythief.engine.map.rooms;

import club.thecraftythief.engine.map.RoomType;
import org.bukkit.Material;

public class StairUp extends RoomType {
    public StairUp() {
        super(Material.STONE);
    }

    @Override
    public void canSpawn() {}
}
