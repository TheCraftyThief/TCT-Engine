package club.thecraftythief.engine.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import java.util.UUID;

@CommandAlias("spawn")
public class SpawnCommand extends BaseCommand {
    @Default
    public void onCommand(Player runner) {
        UUID uuid = runner.getUniqueId();

        WorldCreator creator = new WorldCreator(uuid.toString());

        World world = runner.getServer().createWorld(creator);
        // TODO: Generate a void world
        Location location = new Location(world, 0, 0, 0);

        runner.teleport(location);

        runner.sendMessage("In world " + world.getName());
    }
}
