package club.thecraftythief.engine.weutils;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.*;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.util.io.Closer;
import com.sk89q.worldedit.world.block.BlockState;
import com.sk89q.worldedit.world.block.BlockType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.io.*;

public class WEWrapper {
    private static final Location[] selection = new Location[2];

    public static boolean saveSchematic(Clipboard toSave, String fileName) {
        try {
            ClipboardWriter writer = BuiltInClipboardFormat.SPONGE_SCHEMATIC
                    .getWriter(new FileOutputStream(new File(fileName)));
            writer.write(toSave);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Clipboard loadSchematic(String fileName) throws Exception {
        File schematicFile = new File(fileName);
        if (!schematicFile.exists()) {
            throw new Exception("Cannot find schematic file \"" + fileName + "\"");
        }

        ClipboardFormat fileFormat = ClipboardFormats.findByFile(schematicFile);
        if (fileFormat == null) {
            throw new Exception("Unknown schematic file format!");
        }

        Closer closer = Closer.create();
        FileInputStream fis = closer.register(new FileInputStream(schematicFile));
        BufferedInputStream bis = closer.register(new BufferedInputStream(fis));

        ClipboardReader reader = fileFormat.getReader(bis);
        Clipboard clipboard = reader.read();

        return clipboard;
    }

    public static void paste(Clipboard toPaste, Location pasteLocation) {
        BukkitWorld world = new BukkitWorld(pasteLocation.getWorld());
        EditSession session = WorldEdit.getInstance().newEditSessionBuilder().world(world).build();

        Operation operation = new ClipboardHolder(toPaste)
                .createPaste(session)
                .to(BlockVector3.at(
                        pasteLocation.getBlockX(),
                        pasteLocation.getBlockY(),
                        pasteLocation.getBlockZ()))
                .build();
        try {
            Operations.complete(operation);
        } catch (WorldEditException e) {
            e.printStackTrace();
        }
    }

    public static void pos(int posNum, Location posLoc) {
        if (posNum >= 2) {
            try {
                throw new Exception("Invalid selection position \"" + posNum + "\"");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        selection[posNum] = posLoc;
    }

    //TODO: Test this & fix it if it dont work
    public static boolean set(Material toSet) {
        try {
            World world = selection[0].getWorld();

            BlockType type = BlockType.REGISTRY.get(toSet.name());
            BlockState state = type.getDefaultState();

            EditSession session = WorldEdit.getInstance().newEditSessionBuilder().world(new BukkitWorld(world)).build();
            session.setBlocks(new CuboidRegion(
                    BlockVector3.at(
                            selection[0].getBlockX(),
                            selection[0].getBlockY(),
                            selection[0].getBlockZ()
                    ),
                    BlockVector3.at(
                            selection[1].getBlockX(),
                            selection[1].getBlockY(),
                            selection[1].getBlockZ()
                    )
            ), state);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
