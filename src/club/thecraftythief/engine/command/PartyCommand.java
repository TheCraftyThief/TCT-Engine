package club.thecraftythief.engine.command;

import club.thecraftythief.engine.party.Party;
import club.thecraftythief.engine.party.PartyMgr;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@CommandAlias("party|p")
public class PartyCommand extends BaseCommand {
    private String helpLine(String commandName, String commandDescription) {
        return (ChatColor.LIGHT_PURPLE + "" + commandName + ChatColor.RESET + "" + ChatColor.WHITE + commandDescription);
    }

    @Default
    @HelpCommand
    @Description("Get a list of all party commands")
    public void onCommand(Player runner) {
        runner.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Party" + ChatColor.RESET + "" + ChatColor.WHITE + " commands");
        runner.sendMessage(helpLine("/p accept [player]", " to accept a party invite from a player"));
        runner.sendMessage(helpLine("/p invite [player]", " to invite a player"));
        runner.sendMessage(helpLine("/p kick [player]", " to kick a player from your party"));
        runner.sendMessage(helpLine("/p disband [player]", " to disband your current party"));
        runner.sendMessage(helpLine("/p leave", " to leave your current party"));
        runner.sendMessage(helpLine("/p info", " to get your current party info"));
    }

    @Subcommand("accept")
    @Description("Accept a party invite")
    public void onAccept(Player runner) {
        runner.sendMessage("");
    }

    @Subcommand("invite")
    @Description("Invite a player to your party")
    public void onInvite(Player runner, OnlinePlayer player) {
        PartyMgr partyMgr = PartyMgr.getInstance();

        Party party = partyMgr.getPlayerParty(runner);

        if (party == null) {
            if (partyMgr.getPlayerParty(runner.getPlayer()) != null) {
                runner.sendMessage("Youre already in a party..");
                return;
            }
            party = new Party(runner.getUniqueId());
            partyMgr.addParty(party);
        }

        partyMgr.addPartyInvite(party, player.getPlayer().getUniqueId());

        runner.sendMessage("Invited " + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + player.getPlayer().getName() + ChatColor.RESET + " to your party!");
        player.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + runner.getName() + ChatColor.RESET + " invited you to a party!");
    }

    @Subcommand("kick")
    @Description("Kick a player from your party")
    public void onKick(Player runner) {
        runner.sendMessage("");
    }

    @Subcommand("disband")
    @Description("Disband your party")
    public void onDisband(Player runner) {
        runner.sendMessage("");
    }

    @Subcommand("leave")
    @Description("Leave your current party")
    public void onLeave(Player runner) {
        runner.sendMessage("");
    }

    @Subcommand("info")
    @Description("Get info on your current party")
    public void onInfo(Player runner) {
        runner.sendMessage("");
    }
}