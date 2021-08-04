package club.thecraftythief.engine.map;

import org.bukkit.Location;

public class Room {

    RoomType type;
    private Location roomLoc;

    public Room(RoomType type, Location roomLoc) {
        this.type = type;
        this.roomLoc = roomLoc;
    }

    public Location getLocation() {
        return this.roomLoc;
    }

    public RoomType getType() {
        return this.type;
    }
}