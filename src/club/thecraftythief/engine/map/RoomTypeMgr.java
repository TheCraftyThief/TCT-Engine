package club.thecraftythief.engine.map;

import java.util.ArrayList;
import java.util.List;

public class RoomTypeMgr {

    private List<IRoomType> roomTypeList;

    public RoomTypeMgr() {
        roomTypeList = new ArrayList<>();

        registerRoomType(new GenericRoomType());
    }

    public void registerRoomType(IRoomType type) {
        roomTypeList.add(type);
    }

    public IRoomType getRoomType(String roomType) {
        for(IRoomType room : roomTypeList) {
            if(room.getTypeName().equals(roomType)) {
                return room;
            }
        }
        return null;
    }
}
