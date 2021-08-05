package club.thecraftythief.engine.map;

public class GenericRoomType implements IRoomType {
    @Override
    public String getTypeName() {
        return "generic";
    }

    public boolean canSpawn(Room[] neighbors) {
        return true;
    }
}
