package club.thecraftythief.engine.map;

public interface IRoomType {
    String getTypeName();

    //The array will always have 6 elements. A null element means no room has spawned there yet.
    //0-Up
    //1-Down
    //2-North
    //3-South
    //4-East
    //5-West
    boolean canSpawn(Room[] neighbors);
}
