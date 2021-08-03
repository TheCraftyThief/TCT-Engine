package club.thecraftythief.engine.map;

import org.bukkit.Location;

public class Room {

    RoomType type;
    private Location roomLoc;

    public Room(Location roomLoc) {
        this.roomLoc = roomLoc;
    }
}