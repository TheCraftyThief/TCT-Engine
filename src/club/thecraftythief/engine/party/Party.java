package club.thecraftythief.engine.party;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Party {
    private UUID owner;
    private List<UUID> members;

    public Party(UUID owner) {
        this.owner = owner;
        this.members = new ArrayList<UUID>();
    }

    public boolean addMember(UUID member) {
        return this.members.add(member);
    }

    public UUID getOwner() {
        return this.owner;
    }

    public List<UUID> getMembers() {
        return this.members;
    }
}
