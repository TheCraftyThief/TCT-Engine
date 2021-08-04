package club.thecraftythief.engine.map.rooms;

import club.thecraftythief.engine.map.RoomType;
import org.bukkit.Material;

public class StairReceive extends RoomType {
    public StairReceive() {
        super(Material.COBBLESTONE);
    }

    @Override
    public void canSpawn() {
    }
}
