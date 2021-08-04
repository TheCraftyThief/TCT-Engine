package club.thecraftythief.engine.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.UUID;

@CommandAlias("spawn")
public class SpawnCommand extends BaseCommand {
    @Default
    public void onCommand(Player runner) {
        UUID uuid = runner.getUniqueId();

        WorldCreator creator = new WorldCreator(uuid.toString());
        creator.generator(new ChunkGenerator() {
                              @Override
                              public @NotNull ChunkData generateChunkData(@NotNull World world, @NotNull Random random, int x, int z, @NotNull BiomeGrid biome) {
                                  return createChunkData(world);
                              }

                              @Override
                              public Location getFixedSpawnLocation(@NotNull World world, @NotNull Random random) {
                                  return new Location(world, 0, 64, 0);
                              }
                          }
        );

        World world = Bukkit.createWorld(creator);
        world.getBlockAt(0, 65, 0).setType(Material.STONE);

        Location location = new Location(world, 0, 64, 0);

        runner.teleport(location);

        runner.sendMessage("In world " + world.getName());
    }
}
