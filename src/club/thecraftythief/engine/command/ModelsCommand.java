package club.thecraftythief.engine.command;

import club.thecraftythief.engine.Engine;
import club.thecraftythief.engine.model.ModelData;
import club.thecraftythief.engine.model.ModelMgr;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import com.github.lory24.mcuitils.McGUI;
import com.github.lory24.mcuitils.api.GUIButtonEvents;
import com.github.lory24.mcuitils.api.GUItem;
import com.github.lory24.mcuitils.utils.GuiLines;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@CommandAlias("model|models")
public class ModelsCommand extends BaseCommand {
    @Default
    @CommandPermission("tct.engine.model.give")
    public void onCommand(Player runner) {
        McGUI gui = new McGUI("Models", GuiLines.SIX_LINES, Engine.getInstance());

        //9*6 because theres 9 total slots in one line and 6 total lines
        for (int i = 0; i < 9 * 6; i++) {
            ItemStack iStack = new ItemStack(Material.STICK);
            ItemMeta meta = iStack.getItemMeta();
            meta.setCustomModelData(i);
            iStack.setItemMeta(meta);
            GUItem item = new GUItem(Material.STICK) {
                @Override
                public ItemStack buildToItemStack() {
                    return iStack;
                }
            };
            gui.createButton(item, i, new GUIButtonEvents(() -> {
                //Left click actions
                runner.getInventory().addItem(iStack);
            }));
        }

        gui.openInventoryTo(runner);
    }

    @Subcommand("spawn")
    @CommandPermission("tct.engine.model.spawn")
    public void onSpawn(Player runner, String modelName) {

        ModelMgr modelMgr = ModelMgr.getInstance();
        ModelData model = modelMgr.getModel(modelName);
        if(model == null) {
            runner.sendMessage("Couldn't find model \""+modelName+"\"");
            return;
        }

        Location targetLoc = runner.getLocation();
        World world = targetLoc.getWorld();
        targetLoc.add(model.getSpawnOffset());
        ArmorStand stand = (ArmorStand)world.spawnEntity(targetLoc, EntityType.ARMOR_STAND);
        stand.setInvisible(true);
        stand.setGravity(false);
        stand.setItem(EquipmentSlot.HEAD, model.getItemStack());
    }
}
