package club.thecraftythief.engine.map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.util.ArrayList;
import java.util.List;

public class RoomMgr {

    private List<Room> roomList;

    public RoomMgr() {
        roomList = new ArrayList<>();
    }

    public void registerRoom(Room room) {
        roomList.add(room);
    }

    public Room createNewRoom(IRoomType roomType) {

        //Create world
        int nextSlot = roomList.size();
        World roomWorld = Bukkit.createWorld(new WorldCreator("RoomEditorWorld").generator(new VoidGen()));

        //Create room object
        Location origin = new Location(roomWorld, 0, 70, 0);
        Room newRoom = new Room(roomType, origin);

        return newRoom;
    }
}
