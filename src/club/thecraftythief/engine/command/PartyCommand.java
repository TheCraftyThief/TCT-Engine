package club.thecraftythief.engine.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@CommandAlias("party|p")
public class PartyCommand extends BaseCommand {
    private String helpLine(String commandName, String commandDescription){
        return (ChatColor.AQUA+commandName+ChatColor.GRAY+ChatColor.ITALIC+commandDescription);
    }
    @Default
    public void onCommand(Player runner) {
        runner.sendMessage(helpLine("/p accept [player]", " - Accept a party invite from a player"));
        runner.sendMessage(helpLine("/p invite [player]", " - Invites player "));
        //runner.sendMessage(ChatColor.AQUA+"/p invite [player]"+ChatColor.GRAY+ChatColor.ITALIC+);
    }
    @Subcommand("list")
    public void onlist(Player runner) {
        runner.sendMessage("");
    }
}