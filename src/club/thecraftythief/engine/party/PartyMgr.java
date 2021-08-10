package club.thecraftythief.engine.party;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class PartyMgr {
    private static PartyMgr instance;
    private final List<Party> partyList;
    private final HashMap<Party, UUID> partyInvites;

    /**
     * Create a new PartyMgr
     */
    public PartyMgr() {
        instance = this;
        this.partyList = new ArrayList<>();
        this.partyInvites = new HashMap<>();
    }

    /**
     * Gets the PartyMgr instance
     *
     * @return PartyMgr the PartyMgr instance
     */
    public static PartyMgr getInstance() {
        return instance;
    }

    /**
     * Adds a party to the list of parties
     *
     * @param party The party to add
     */
    public void addParty(Party party) {
        this.partyList.add(party);
    }

    /**
     * Invites a player  by UUID to the party
     *
     * @param party The party to invite the player to
     * @param uuid  The player to invite to the party
     */
    public void addPartyInvite(Party party, UUID uuid) {
        this.partyInvites.put(party, uuid);
    }

    /**
     * Gets a list of all parties
     *
     * @return List<Party> List of parties
     */
    public List<Party> getParties() {
        return this.partyList;
    }

    /**
     * Gets a players current party or returns null
     *
     * @param player The player to get the party of
     * @return Party The players party or null
     */
    public Party getPlayerParty(Player player) {
        for (Party party : this.getParties()) {
            if (party.getOwner() == player.getUniqueId()) {
                return party;
            }
        }
        return null;
    }
}
