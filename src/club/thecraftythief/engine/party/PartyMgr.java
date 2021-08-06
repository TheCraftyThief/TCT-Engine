package club.thecraftythief.engine.party;

import club.thecraftythief.engine.model.ModelMgr;

import java.util.ArrayList;
import java.util.List;

public class PartyMgr {
    private static PartyMgr instance;
    private List<Party> partyList;

    public PartyMgr() {
        instance = this;
        this.partyList = new ArrayList<>();
    }

    public static PartyMgr getInstance() {
        return instance;
    }

    public void addParty(Party party) {
        this.partyList.add(party);
    }

    public List<Party> getParties() { return this.partyList; }
}
