package club.thecraftythief.engine.command;

import club.thecraftythief.engine.Engine;
import club.thecraftythief.engine.map.RoomMgr;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.entity.Player;

@CommandAlias("roomeditor")
public class EditorCommand extends BaseCommand {
    @Subcommand("new")
    public void onNew(Player player, String roomType) {
        //TODO: Room editor system
        //Engine.getRoomMgr().createNewRoom();
    }
}
