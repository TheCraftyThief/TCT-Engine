package club.thecraftythief.engine.map;

import org.bukkit.Location;

public class Room {
    private IRoomType roomType;
    private Location origin;

    //No access modifier so its constructor is only accessible in this package
    Room(IRoomType roomType, Location origin) {
        this.roomType = roomType;
        this.origin = origin;


    }

    public IRoomType getRoomType() {
        return roomType;
    }

    public Location getOrigin() {
        return origin;
    }

    public void cloneTo(Location target) {

    }
}
