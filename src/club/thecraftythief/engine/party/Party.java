package club.thecraftythief.engine.party;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Party {
    private UUID owner;
    private List<UUID> players;

    /**
     * Create a new empty party
     *
     * @param owner the owner of the party
     */
    public Party(UUID owner) {
        this.owner = owner;
        this.players = new ArrayList<UUID>();
    }

    /**
     * Add a player to the party
     *
     * @param player The player to add to the party
     */
    public void addPlayer(UUID player) {
        this.players.add(player);
    }

    /**
     * Get the owner of the current party
     *
     * @return UUID The owners UUID
     */
    public UUID getOwner() {
        return this.owner;
    }

    /**
     * Gets all players in the current party
     *
     * @return List<UUID> Get a list of all players in the party
     */
    public List<UUID> getPlayers() {
        return this.players;
    }
}
